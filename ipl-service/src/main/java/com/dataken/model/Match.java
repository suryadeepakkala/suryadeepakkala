package com.dataken.model;

import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import javax.persistence.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "matches")
public class Match implements Serializable {

    private int id;
    private String city;
    private Date matchDate;
    private String manOftheMatch;
    private String venue;
    private boolean neutralVenue;
    private String firstTeam;
    private String secondTeam;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String result;
    private int resultMargin;
    private boolean eliminator;
    private String method;
    private String umpireOne;
    private String umpireTwo;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Temporal(TemporalType.DATE)
    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getManOftheMatch() {
        return manOftheMatch;
    }

    public void setManOftheMatch(String manOftheMatch) {
        this.manOftheMatch = manOftheMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean isNeutralVenue() {
        return neutralVenue;
    }

    public void setNeutralVenue(boolean neutralVenue) {
        this.neutralVenue = neutralVenue;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultMargin() {
        return resultMargin;
    }

    public void setResultMargin(int resultMargin) {
        this.resultMargin = resultMargin;
    }

    public boolean isEliminator() {
        return eliminator;
    }

    public void setEliminator(boolean eliminator) {
        this.eliminator = eliminator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUmpireOne() {
        return umpireOne;
    }

    public void setUmpireOne(String umpireOne) {
        this.umpireOne = umpireOne;
    }

    public String getUmpireTwo() {
        return umpireTwo;
    }

    public void setUmpireTwo(String umpireTwo) {
        this.umpireTwo = umpireTwo;
    }

}
