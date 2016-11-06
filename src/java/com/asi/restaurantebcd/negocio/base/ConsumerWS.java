 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantebcd.negocio.util.ReponseWs;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.net.ConnectException;

import java.net.NoRouteToHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ConsumerWS implements ConsumerWSLocal {

public String consumirWebservices(String usr,
            String metodo, String recurso, String jsonDatos,String URLBase) throws Exception {
        String respuesta = null;
        if (URLBase == null) {
            throw new Exception("La url base es obligatorio.");
        }     
        String token = crearTokenWebservice(usr);
        Map mapHeader = new HashMap();
        mapHeader.put("User", usr);
        mapHeader.put("fecha", new Date().toString());
        mapHeader.put("Token", token);
        try {
            String jsonHeader = new Gson().toJson(mapHeader);
            Client client = Client.create();
            WebResource webResource = client.resource("urlbase");
            WebResource.Builder buildws;
            buildws = webResource.path(recurso).path(metodo)
                    .header("autorizacion", jsonHeader);
            ClientResponse response;
            response = buildws.post(ClientResponse.class, jsonDatos);
            String rs = response.getEntity(String.class);
               Gson json = new Gson();
        ReponseWs ws = json.fromJson(rs, ReponseWs.class);
            if (ws != null && ws.getError().equals("1")) {
                throw new Exception("Peidos Ws: " +ws.getDescripcion());
            }
            if (ws != null && ws.getContent() != null) {
                respuesta = ws.getContent();
            }
            return respuesta;
        } catch (Exception e) {
            if (e instanceof ConnectException
                    || e instanceof ClientHandlerException
                    || e instanceof NoRouteToHostException) {
                throw new Exception("No se pudo establecer comunicacion con servicios web,"
                        + " comuniquese con el administrador");
            }
            throw new Exception(e.getMessage());
        }
    }

private String crearTokenWebservice(String usr) {
     String token = null;
     return token;
}
}
