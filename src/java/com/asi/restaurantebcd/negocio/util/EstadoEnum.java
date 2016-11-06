/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author samaelopez
 */
public enum EstadoEnum {
        /**constante de numero cero.*/
    GENERADO(new BigInteger("1")),
    /**constante de numero uno.*/
    PENDIENTE_ALMACENAMIENTO(new BigInteger("1")),
    /**constante de numero dos.*/
    INGRESO_INVENTARIO(new BigInteger("3")),
    /**constante de numero trs.*/
    TRANSITO(new BigInteger("4")),
    ANULADO(new BigInteger("9")),
    /**constante de numero cuatro.*/
    TERMINADO(new BigInteger("10"));
   
    
    
    
    private final BigInteger estado;
       
    /**
     * Constructor de la Instancia.
     *
     * @param cod El valor del codigo del Modulo.
     */
    private EstadoEnum(final BigInteger cod) {
        this.estado = cod;
    }

    /**
     * Accesor del codigo del modulo.
     *
     * @return El valor de la instancia.
     */
    public BigInteger getBigInteger() {
        return estado;
    }

    /**
     * Accesor del codigo del modulo.
     *
     * @return El valor de la instancia.
     */
    public BigDecimal getBigDecimal() {
        return new BigDecimal(estado.toString());
    }

    /**
     * Accesor del codigo del modulo.
     *
     * @return El valor de la instancia.
     */
    public Integer getInteger() {
        return new Integer(estado.toString());
    }

    /**
     * Accesor del codigo del modulo.
     *
     * @return El valor de la instancia.
     */
    public int intValue() {
        return estado.intValue();
    }
    
}
