package com.example.arsenalteamlist;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class playerParseXML {
    private PlayerListVO [] player;
    private Context context ;

    public playerParseXML(Context c){
        this.context = c;

        // Get InputStream to XML
        InputStream stream = this.context.getResources().openRawResource(R.raw.player_xml_list);

        // Parse XML TO A Document
        DocumentBuilder builder = null;
        Document xml =  null;

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xml = builder.parse(stream);
        }catch(Exception e){

        }

        NodeList first_name_list = xml.getElementsByTagName("player_first_name");
        NodeList last_namev_list = xml.getElementsByTagName("player_last_namev");
        NodeList country_list = xml.getElementsByTagName("player_country");
        NodeList profile_Image_list = xml.getElementsByTagName("player_profile_Image");
        NodeList country_Image_list = xml.getElementsByTagName("player_country_Image");
        NodeList profile_video_list = xml.getElementsByTagName("player_profile_video");
        NodeList birth_list = xml.getElementsByTagName("birth");
        NodeList playerPosition_list = xml.getElementsByTagName("position");
        NodeList history_list = xml.getElementsByTagName("history");
        NodeList signedDate_list = xml.getElementsByTagName("signedDate");


        player = new PlayerListVO[first_name_list.getLength()];

        for(int i=0; i< player.length; i++){
            String fname = first_name_list.item(i).getFirstChild().getNodeValue();
            String lname = last_namev_list.item(i).getFirstChild().getNodeValue();
            String country = country_list.item(i).getFirstChild().getNodeValue();
            String profile_Image = profile_Image_list.item(i).getFirstChild().getNodeValue();
            String country_Image = country_Image_list.item(i).getFirstChild().getNodeValue();
            String profile_video = profile_video_list.item(i).getFirstChild().getNodeValue();

            String birth = birth_list.item(i).getFirstChild().getNodeValue();
            String playerPosition = playerPosition_list.item(i).getFirstChild().getNodeValue();
            String history = history_list.item(i).getFirstChild().getNodeValue();
            String signedDate = signedDate_list.item(i).getFirstChild().getNodeValue();

            profile_Image = profile_Image.substring(0, profile_Image.indexOf("."));
            country_Image = country_Image.substring(0, country_Image.indexOf((".")));
            profile_video = profile_video.substring(0,profile_video.indexOf("."));

            int profileImageId = this.context.getResources().getIdentifier(
                    profile_Image,
                    "drawable",
                    this.context.getPackageName());
            int flagImageId = this.context.getResources().getIdentifier(
                    country_Image,
                    "drawable",
                    this.context.getPackageName());


            player[i] = new PlayerListVO(profileImageId,fname,lname,country,flagImageId,profile_video,birth,playerPosition,history,signedDate);
        }
    }
    public PlayerListVO getCurrentPlayer(int i){ return player[i];}
    public int getLength(){return player.length;}

}
