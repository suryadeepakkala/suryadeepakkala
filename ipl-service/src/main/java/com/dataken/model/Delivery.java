package com.dataken.model;

import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import javax.persistence.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {

    private int id;
    private Match match;
    private int inning;
    private int over;
    private int ball;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private int batsmanRuns;
    private int extraRuns;
    private int totalRuns;
    private boolean nonBoundary;
    private boolean wicket;
    private String dismissalKind;
    private String playerDismissed;
    private String fielder;
    private String extrasType;
    private String battingTeam;
    private String bowlingTeam;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public int getBatsmanRuns() {
        return batsmanRuns;
    }

    public void setBatsmanRuns(int batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(int extraRuns) {
        this.extraRuns = extraRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public boolean isNonBoundary() {
        return nonBoundary;
    }

    public void setNonBoundary(boolean nonBoundary) {
        this.nonBoundary = nonBoundary;
    }

    public boolean isWicket() {
        return wicket;
    }

    public void setWicket(boolean wicket) {
        this.wicket = wicket;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public void setDismissalKind(String dismissalKind) {
        this.dismissalKind = dismissalKind;
    }

    public String getPlayerDismissed() {
        return playerDismissed;
    }

    public void setPlayerDismissed(String playerDismissed) {
        this.playerDismissed = playerDismissed;
    }

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }

    public String getExtrasType() {
        return extrasType;
    }

    public void setExtrasType(String extrasType) {
        this.extrasType = extrasType;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }


}
