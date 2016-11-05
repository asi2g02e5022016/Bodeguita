/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import com.google.gson.Gson;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author samaelopez
 */
@Provider
public class WsExceptionHandler implements ExceptionMapper<WsException> {

        @Override
    public Response toResponse(WsException exception) {
        ReponseWs errorMessage = new ReponseWs();
        setHttpStatus(exception, errorMessage);
        errorMessage.setDescripcion(exception.getMessage());
        errorMessage.setError("1");
        Gson gson = new Gson();
        gson.toJson(errorMessage);
        return Response.status(Status.BAD_REQUEST)
                .entity(gson.toJson(errorMessage))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
     private void setHttpStatus(Throwable ex, ReponseWs errorMessage) {
         if (ex instanceof WebApplicationException) {
             errorMessage.setStatus(((WebApplicationException) ex)
                     .getResponse().getStatus());
         } else {
             //defaults to internal server error 500
             errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR
                     .getStatusCode());
         }
     }
    
}
