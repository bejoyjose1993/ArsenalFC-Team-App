package com.example.arsenalteamlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class PlayerStatsActivity extends AppCompatActivity {

    private VideoView playerVideo;
    private WebView webView;
    private ImageButton webUrl;
    private String arsenalPlayerDetail;
    private TextView careerText,playerStatus;
    PlayerListVO selectedPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        final Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();
        selectedPlayer = (PlayerListVO) bundle.getSerializable("selectedItem");
        int rawId  = getResources().getIdentifier(selectedPlayer.getVideoFile(), "raw", getPackageName());
        System.out.println("My"+ selectedPlayer.getVideoFile());

        playerVideo = findViewById(R.id.playerVideoView);
        webView = findViewById(R.id.detail_web_view);
        careerText = findViewById(R.id.textView10);
        playerStatus = findViewById(R.id.textView3);
        webView.getSettings().setJavaScriptEnabled(true);

        webUrl = findViewById(R.id.getWebUrlData);
        String str = selectedPlayer.getWebTable();
        String cText = selectedPlayer.getPlayerFirstName().substring(0, 1) + selectedPlayer.getPlayerFirstName().substring(1).toLowerCase()  + " " + selectedPlayer.getPlayerLastName() +"'s " + "Arsenal Career";
        careerText.setText(cText);
        playerStatus.setText(selectedPlayer.getIsInjured());
        System.out.println("str"+ str);
        webView.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        //webView.loadData(str,"text/html","UTF-8");
        String videoPath = "android.resource://" + getPackageName() + "/" + rawId;
        Uri uri = Uri.parse(videoPath);
        playerVideo.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        playerVideo.setMediaController(mediaController);
        playerVideo.seekTo( 1 );
        mediaController.setAnchorView(playerVideo);
        arsenalPlayerDetail = "https://www.arsenal.com/arsenal/players/"+selectedPlayer.getPlayerFirstName().toLowerCase()+"-"+selectedPlayer.getPlayerLastName().toLowerCase();
        webUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isNetwork(getApplicationContext())){
                    Intent intent = new Intent(PlayerStatsActivity.this, ArsenalWebView.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selectedItem",selectedPlayer);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Connect Internet", Toast.LENGTH_SHORT).show();
                }
                //make an intntent in view the url

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(arsenalPlayerDetail));
//                //getWebsite();
//                System.out.println("My ");
//                // startactivity
//                startActivity(intent);
            }
        });
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
