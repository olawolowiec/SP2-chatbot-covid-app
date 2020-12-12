package com.example.vmac.WatBot;

public class Stats {

    private String date;
    private String cases;
    private String deaths;
    private String pupulation;
    private String cumulativeNr;
    private boolean expanded;


    public Stats(String date, String cases, String deaths, String pupulation, String cumulativeNr) {
        this.date = date;
        this.cases = cases;
        this.deaths = deaths;
        this.pupulation = pupulation;
        this.cumulativeNr = cumulativeNr;
        this.expanded = false;
    }

    public Stats(String date, String cases, String deaths, String cumulativeNr) {
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

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getPupulation() {
        return pupulation;
    }

    public void setPupulation(String pupulation) {
        this.pupulation = pupulation;
    }

    public String getCumulativeNr() {
        return cumulativeNr;
    }

    public void setCumulativeNr(String cumulativeNr) {
        this.cumulativeNr = cumulativeNr;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
