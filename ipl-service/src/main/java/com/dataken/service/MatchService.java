package com.dataken.service;

import com.dataken.dao.MatchDAO;
import com.dataken.model.Match;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*@Path("/")
class Welcome {

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlWelcome() {
        return "<html> " + "<title>" + "Welcome Page" + "</title>"
                + "<body><h1>" + "Welcome!" + "</body></h1>" + "</html> ";
    }*/


@Path("/matches")
public class MatchService {

    private static Gson gson = new GsonBuilder().create();
    private MatchDAO matchDao = new MatchDAO();

    /*@Path("")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getwelcome() {
        return "Welcome";
    }*/

    @Path("/matchList/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Match> queryMatches(@PathParam("season") String season) {
        return matchDao.queryMatches(season);
    }

    @Path("/topScorers/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> topScorers(@PathParam("season") String season) {
        return matchDao.queryTopTenRunScorers(season);
    }

    @Path("/topBowlers/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> topBowlers(@PathParam("season") String season) {
        return matchDao.queryTopTenWicketTakers(season);
    }


    @Path("/scoredRuns/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> scoredRuns(@PathParam("season") String season) {
        return matchDao.queryRunsScoredByEachTeam(season);
    }

    @Path("/matchesPlayed/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> matchesPlayed(@PathParam("season") String season) {
        return matchDao.queryMatchesPlayedInEachCity(season);
    }


    @Path("/topRuns/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> topRuns(@PathParam("season") String season) {
        return matchDao.queryTopScorers(season);
    }



    @Path("/centuryScored/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> centuryScored(@PathParam("season") String season) {
        return matchDao.queryCenturyScored(season);
    }

    @Path("/fiftyScored/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> fiftyScored(@PathParam("season") String season) {
        return matchDao.queryFiftyScored(season);
    }

    @Path("/fastestFiftyScored/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> fastestFiftyScored(@PathParam("season") String season) {
        return matchDao.queryFastestFiftyScored(season);
    }

    @Path("/battingStrikeRate/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Double> battingStrikeRate(@PathParam("season") String season) {
        return matchDao.queryBattingStrikeRate(season);
    }

    @Path("/battingAverage/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Double> battingAverage(@PathParam("season") String season) {
        return matchDao.queryBattingAverage(season);
    }

    @Path("/maidensBowled/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> maidensBowled(@PathParam("season") String season) {
        return matchDao.queryMaidensBowled(season);
    }

    @Path("/dotBallsBowled/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> dotBallsBowled(@PathParam("season") String season) {
        return matchDao.queryDotBallsBowled(season);
    }

    @Path("/bowlingEconomy/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> bowlingEconomy(@PathParam("season") String season) {
        return matchDao.queryBowlingEconomy(season);
    }

    @Path("/runsConceded/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Map<String, Integer> runsConceded(@PathParam("season") String season) {
        return matchDao.queryRunsConceded(season);
    }


}
