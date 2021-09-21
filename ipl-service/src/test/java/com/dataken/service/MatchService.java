package com.dataken.service;

import com.dataken.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchService {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("matches/matchList/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }



    @Test
    public void testscoredRuns() {
        String responseMsg = target.path("matches/scoredRuns/2020").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testmatchesPlayed() {
        String responseMsg = target.path("matches/matchesPlayed/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testtopRuns() {
        String responseMsg = target.path("matches/topRuns/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testcenturyScored() {
        String responseMsg = target.path("matches/centuryScored/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testfiftyScored() {
        String responseMsg = target.path("matches/fiftyScored/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testfastestFiftyScored() {
        String responseMsg = target.path("matches/fastestFiftyScored/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testbattingStrikeRate() {
        String responseMsg = target.path("matches/battingStrikeRate/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testbattingAverage() {
        String responseMsg = target.path("matches/battingAverage/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testmaidensBowled() {
        String responseMsg = target.path("matches/maidensBowled/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testdotBallsBowled() {
        String responseMsg = target.path("matches/dotBallsBowled/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void bowlingEconomy() {
        String responseMsg = target.path("matches/bowlingEconomy/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }

    @Test
    public void testrunsConceded() {
        String responseMsg = target.path("matches/runsConceded/2008").request().get(String.class);
        assertEquals("Hello world!", responseMsg);
    }



}
