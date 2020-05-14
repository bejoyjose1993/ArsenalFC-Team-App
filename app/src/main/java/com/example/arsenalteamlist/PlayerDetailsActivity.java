package com.example.arsenalteamlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import org.jsoup.Jsoup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerDetailsActivity extends AppCompatActivity {
    ImageView detailedPlayerImage, detailedFlagCountry;
    TextView detailedFirstName, detailedLastName, detailedCountry;
    TextView birth,playerPosition,history,signedDate;
    private ProgressBar spinner;
    ImageButton urlData;
    Context context;
    String arsenalPlayerDetail,arsenalInjuryList;
    PlayerListVO selectedPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        detailedPlayerImage = findViewById(R.id.imageViewDetailed);
        detailedFlagCountry = findViewById(R.id.flagImageDetailed);
        detailedFirstName = findViewById(R.id.first_nameDetailed);
        detailedLastName = findViewById(R.id.last_nameDetailed);
        detailedCountry = findViewById(R.id.countryDetailed);
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        urlData = findViewById(R.id.getwebData);
        birth = findViewById(R.id.birth);
        playerPosition = findViewById(R.id.position);
        history = findViewById(R.id.history);
        signedDate = findViewById(R.id.signedDate);

        final Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();
        selectedPlayer = (PlayerListVO) bundle.getSerializable("clickedItem");

        detailedPlayerImage.setImageResource(selectedPlayer.getPlayerImage());
        detailedFirstName.setText(selectedPlayer.getPlayerFirstName());
        detailedLastName.setText(selectedPlayer.getPlayerLastName());
        detailedCountry.setText(selectedPlayer.getPlayerCountry());
        detailedFlagCountry.setImageResource(selectedPlayer.getFlagCountry());
        birth.setText(selectedPlayer.getBirth());
        playerPosition.setText(selectedPlayer.getPosition());
        history.setText(selectedPlayer.getHistory());
        signedDate.setText(selectedPlayer.getSignedDate());

        arsenalPlayerDetail = "https://www.arsenal.com/arsenal/players/"+selectedPlayer.getPlayerFirstName().toLowerCase()+"-"+selectedPlayer.getPlayerLastName().toLowerCase();
        arsenalInjuryList = "https://www.arsenal.com/news/team-news";
        System.out.println("dsfds "+arsenalPlayerDetail);


        urlData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                if (isNetwork(getApplicationContext())){
                    getWebsite();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Connect Internet", Toast.LENGTH_SHORT).show();
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spinner.setVisibility(View.GONE);
                    }
                }, 1100);
            }
        });
    }
    private void getWebsite(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = (Document)Jsoup.connect(arsenalPlayerDetail).get();
                    Document injuredDoc = (Document)Jsoup.connect(arsenalInjuryList).get();
                    Elements title = doc.getElementsByTag("table");
                    Elements isInjured = injuredDoc.getElementsByAttribute("hreflang");
                    Set<String> playerList = new HashSet<String>();
                    for (Element element : isInjured) {
                        String tempStr = element.toString().substring(element.toString().indexOf(">")+1);
                        String playerNAme = tempStr.substring(0, tempStr.toString().indexOf("<"));
                        playerList.add(playerNAme);
                    }

                    String myTable = "<head><style>table, th, td {border: 1px solid black;}</style></head><body> \n" + title.toString().substring(0, title.toString().indexOf(">")+1)+ "\n" + "<col width=80> <col width=80>"
                            + "\n" +title.toString().substring(title.toString().indexOf(">")+1) + "</body>";

                    System.out.println("myTable%%%%%%%%%%%"+myTable);
                    System.out.println("myTable%%%%%%%%%%%");
                    System.out.println("myTable%%%%%%%%%%%"+title);
                    for (String pList : playerList) {
                        String pNAme = selectedPlayer.getPlayerFirstName()+ " " +selectedPlayer.getPlayerLastName();
                        if(pList.trim().equalsIgnoreCase(pNAme)){
                            selectedPlayer.setIsInjured("INJURED");
                        }else{
                            selectedPlayer.setIsInjured("MATCH FIT");
                        }
                    }
//                    Elements tHead = doc.getElementsByTag("thead");
//                   Elements tBody = doc.getElementsByTag("tbody");
                    System.out.println("@arsenalPlayerDetail@"+arsenalPlayerDetail);
                    PlayerListVO selectedItem = selectedPlayer;
                    selectedPlayer.setWebTable(myTable);
                    Intent intent = new Intent(PlayerDetailsActivity.this, PlayerStatsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selectedItem",selectedItem);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch (IOException e){
                    System.out.println("IOException ");
                }
            }
        }).start();


    }
    public Boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
