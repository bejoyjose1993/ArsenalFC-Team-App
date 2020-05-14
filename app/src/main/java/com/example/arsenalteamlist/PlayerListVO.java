package com.example.arsenalteamlist;

import java.io.Serializable;

public class PlayerListVO implements Serializable {
    private int playerImage;
    private String playerFirstName;
    private String playerLastName;
    private String playerCountry;
    private int flagCountry;
    private String videoFile;
    private String birth;
    private String position;
    private String history;
    private String signedDate;
    private String webTable;
    private String isInjured;


    public PlayerListVO(int playerImage, String playerFirstName, String playerLastName, String playerCountry, int flagCountry, String videoFile, String birth, String position, String history,String signedDate) {
        this.playerImage = playerImage;
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.playerCountry = playerCountry;
        this.flagCountry = flagCountry;
        this.videoFile = videoFile;
        this.birth = birth;
        this.position = position;
        this.history = history;
        this.signedDate = signedDate;
    }

    public int getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(int playerImage) {
        this.playerImage = playerImage;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public int getFlagCountry() {
        return flagCountry;
    }

    public void setFlagCountry(int flagCountry) {
        this.flagCountry = flagCountry;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }

    public String getWebTable() {
        return webTable;
    }

    public void setWebTable(String webTable) {
        this.webTable = webTable;
    }

    public String getIsInjured() {
        return isInjured;
    }

    public void setIsInjured(String isInjured) {
        this.isInjured = isInjured;
    }
}
