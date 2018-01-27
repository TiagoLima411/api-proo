/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author tiago
 */
@Path("/")
public class ProdutoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoResource
     */
    public ProdutoResource() {
    }

    /**
     * Retrieves representation of an instance of services.ProdutoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCliente(String content) {
        String message = null;        
        try {
            Gson g = new Gson();                       
            message = "hello produto";
            Map objMessage;
            objMessage = ConvertMap.converterToMap(message);
            return Response.ok().entity(g.toJson(objMessage)).build();            
        } catch (Exception e) {            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
