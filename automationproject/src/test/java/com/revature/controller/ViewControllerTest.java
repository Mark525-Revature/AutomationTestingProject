package com.revature.controller;

// import java.io.IOException;
// import java.net.URI;
import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;

import org.junit.After;
// import org.junit.Assert;
import org.junit.Before;
// import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.planetarium.controller.ViewController;
import com.revature.Setup;
import io.javalin.Javalin;

public class ViewControllerTest {
    ViewController viewController;
    HttpClient webClient;
    ObjectMapper objectMapper;
    Javalin app;
    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        viewController = new ViewController();
        webClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
        app.start(8080);
        Thread.sleep(1000);
    }
    @After
    public void tearDown(){
        app.stop();
    }
    /* Tests to make:
     * method: successfull   / unsucessfull
     * login: ctx.status(200);/ctx.status(400);
     * home: ctx.status(200);/ctx.status(400);
     * register: ctx.status(200);/ctx.status(400);
     * background: ?;/ctx.status(404);
    */
}
