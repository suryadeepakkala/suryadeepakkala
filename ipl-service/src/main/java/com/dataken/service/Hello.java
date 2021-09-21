package com.dataken.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Hello {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlWelcome() {
        return "<html> " + "<title>" + "Welcome Page" + "</title>"
                + "<body><h1>" + "Welcome!" + "</body></h1>" + "</html> ";

    }
}
