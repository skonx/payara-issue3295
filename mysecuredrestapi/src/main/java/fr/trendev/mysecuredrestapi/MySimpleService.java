/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trendev.mysecuredrestapi;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jsie
 */
@Stateless
@Path("service")
public class MySimpleService {

    @GET
    @Path("hello-world")
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloWorld() {
        return Response.ok(this.createMsg("Hello World")).build();
    }

    @GET
    @Path("special")
    @Produces(MediaType.APPLICATION_JSON)
    public Response somethingSpecial() {
        return Response.
                ok(this.createMsg("You are someone very special ;)")).
                build();
    }

    private final JsonObject createMsg(String msg) {
        return Json.createObjectBuilder().add("message", msg).build();
    }

}
