package com.example.vmac.WatBot;

public class Stats {

    private String date;
    private int cases;
    private int deaths;
    private int pupulation;
    private int cumulativeNr;
    private boolean expanded;


    public Stats(String date, int cases, int deaths, int pupulation, int cumulativeNr) {
        this.date = date;
        this.cases = cases;
        this.deaths = deaths;
        this.pupulation = pupulation;
        this.cumulativeNr = cumulativeNr;
        this.expanded = false;
    }

    public Stats(String date, int cases, int deaths, int cumulativeNr) {
        this.date = date;
        this.cases = cases;
        this.deaths = deaths;
        this.cumulativeNr = cumulativeNr;
        this.expanded = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getPupulation() {
        return pupulation;
    }

    public void setPupulation(int pupulation) {
        this.pupulation = pupulation;
    }

    public int getCumulativeNr() {
        return cumulativeNr;
    }

    public void setCumulativeNr(int cumulativeNr) {
        this.cumulativeNr = cumulativeNr;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
