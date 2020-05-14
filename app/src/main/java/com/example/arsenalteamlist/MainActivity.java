package com.example.arsenalteamlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PlayerListVO> playerList;
    private ListView listView;
    private playerParseXML player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        player = new playerParseXML(getApplicationContext());

        playerList = new ArrayList<>();
        listView = findViewById(R.id.playerListView);
        for(int i =0; i<player.getLength();i++){
            playerList.add(player.getCurrentPlayer(i));
        }

        PlayerListAdapter adapter = new PlayerListAdapter(this,R.layout.team_list_layout,playerList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerListVO clickedItem = (PlayerListVO) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, PlayerDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("clickedItem",clickedItem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
