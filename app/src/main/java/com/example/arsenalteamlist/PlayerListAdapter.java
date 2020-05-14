package com.example.arsenalteamlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlayerListAdapter extends ArrayAdapter<PlayerListVO> {

    List<PlayerListVO> playerList;
    Context context;
    int resources;

    public PlayerListAdapter(Context context, int resource, List<PlayerListVO> playerList) {
        super(context, resource, playerList);
        this.context = context;
        this.resources = resource;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resources,null,false);
        ImageView playerImage = view.findViewById(R.id.playerImage);
        TextView playerFirstName = view.findViewById(R.id.first_nameDetailed);
        TextView playerLastName = view.findViewById(R.id.last_nameDetailed);
        TextView playerCountry = view.findViewById(R.id.countryDetailed);
        ImageView flagCountry = view.findViewById(R.id.flagImageDetailed);

        TextView birth = view.findViewById(R.id.birth);
        TextView playerPosition = view.findViewById(R.id.position);
        TextView history = view.findViewById(R.id.history);
        TextView signedDate = view.findViewById(R.id.signedDate);

        PlayerListVO playerListVO = playerList.get(position);

        playerImage.setImageDrawable(context.getResources().getDrawable(playerListVO.getPlayerImage()));
        playerFirstName.setText(playerListVO.getPlayerFirstName());
        playerLastName.setText(playerListVO.getPlayerLastName());
        playerCountry.setText(playerListVO.getPlayerCountry());
        flagCountry.setImageDrawable(context.getResources().getDrawable(playerListVO.getFlagCountry()));
        return view;


    }
}
