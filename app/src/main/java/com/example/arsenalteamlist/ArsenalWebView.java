package com.example.arsenalteamlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ArsenalWebView extends AppCompatActivity {

    private WebView myWebView;
    private String arsenalPlayerDetail;
    private PlayerListVO selectedPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arsenal_web_view);
        myWebView = findViewById(R.id.myWebView);
        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        final Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();
        selectedPlayer = (PlayerListVO) bundle.getSerializable("selectedItem");


        arsenalPlayerDetail = "https://www.arsenal.com/arsenal/players/"+selectedPlayer.getPlayerFirstName().toLowerCase()+"-"+selectedPlayer.getPlayerLastName().toLowerCase();
        myWebView.loadUrl(arsenalPlayerDetail);
        myWebView.getSettings().setJavaScriptEnabled(true);
    }
}
