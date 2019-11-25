package com.example.exercise3;

public class AdapterData {
    public String home;
    public String homescore;
    public String guest;
    public String guestscore;

    public AdapterData(String home, String homescore, String guest, String guestscore) {
        this.home = home;
        this.homescore = homescore;
        this.guest = guest;
        this.guestscore = guestscore;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomescore() {
        return homescore;
    }

    public void setHomescore(String homescore) {
        this.homescore = homescore;
    }
    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getGuestscore() {
        return guestscore;
    }

    public void setGuestscore(String guestscore) {
        this.guestscore = guestscore;
    }

    public String getTeams() {
        return this.home + " VS " + this.guest;
    }

    public String getScore() {
        return this.homescore + " - " + this.guestscore;
    }
}
