package com.dataken.dao;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MatchDAOTest {

    @Test
    public void queryTopTenRunScorers() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryTopTenRunScorers("2020");
        System.out.println(stats);
    }

    @Test
    public void queryTopTenWicketTakers() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryTopTenWicketTakers("2020");
        System.out.println(stats);
    }


    @Test
    public void queryRunsScoredByEachTeam() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryRunsScoredByEachTeam("2020");
        System.out.println(stats);
    }

    @Test
    public void queryMatchesPlayedInEachCity() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryMatchesPlayedInEachCity("2020");
        System.out.println(stats);
    }

    @Test
    public void queryTopScorers() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryTopScorers("2020");
        System.out.println(stats);
    }

    @Test
    public void queryCenturyScored() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryCenturyScored("2020");
        System.out.println(stats);
    }

    @Test
    public void queryFiftyScored() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryFiftyScored("2020");
        System.out.println(stats);
    }

    @Test
    public void queryFastestFiftyScored() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryFastestFiftyScored("2020");
        System.out.println(stats);
    }

    @Test
    public void queryBattingStrikeRate() {
        MatchDAO dao = new MatchDAO();
        Map<String, Double> stats = dao.queryBattingStrikeRate("2020");
        System.out.println(stats);
    }

    @Test
    public void queryBattingAverage() {
        MatchDAO dao = new MatchDAO();
        Map<String, Double> stats = dao.queryBattingAverage("2020");
        System.out.println(stats);
    }

    @Test
    public void queryMaidensBowled() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryMaidensBowled("2020");
        System.out.println(stats);
    }

    @Test
    public void queryDotBallsBowled() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryDotBallsBowled("2020");
        System.out.println(stats);
    }

    @Test
    public void queryBowlingEconomy() {
        MatchDAO dao = new MatchDAO();
        Map<String, Integer> stats = dao.queryBowlingEconomy("2020");
        System.out.println(stats);
    }
}
