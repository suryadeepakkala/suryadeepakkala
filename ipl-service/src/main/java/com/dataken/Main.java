package com.dataken;

import com.dataken.dao.DAOUtils;
import com.dataken.dao.DeliveryDAO;
import com.dataken.dao.MatchDAO;
import com.dataken.model.Delivery;
import com.dataken.model.Match;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;




/**
 * Main class.
 *
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:7777/";
    private static MatchDAO dao = new MatchDAO();
    private static DeliveryDAO deliveryDao = new DeliveryDAO();


    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.dataken package
        final ResourceConfig rc = new ResourceConfig().packages("com.dataken.service");

        //my modifications

        rc.register(new CORSFilter());
        rc.register(CORSFilter.class);

        //my modifications end

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ensureData();
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }

    private static void ensureData() {
        try {
            deliveryDao.deleteAll();
            List<Match> matchList = DAOUtils.parseMatches("./ipl-service/src/main/resources/ipl-matches.csv");
            dao.persist(matchList);
            List<Delivery> deliveries = DAOUtils.parseDeliveries("./ipl-service/src/main/resources/ipl-ball-by-ball.csv");
            deliveryDao.persist(deliveries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        //my modifications start
    public static class CORSFilter implements ContainerResponseFilter {

        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

            MultivaluedMap<String, Object> headers = responseContext.getHeaders();

            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            headers.add("Access-Control-Allow-Headers", "Content-Type");
        }
    }
    //my modifications end
}

