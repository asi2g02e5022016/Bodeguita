/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

/**
 *
 * @author samaelopez
 */
public class ReponseWs {
private String descripcion ;
private String error;
private String content;
private int status ;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(int status) {
        this.status = status;

    }  

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
