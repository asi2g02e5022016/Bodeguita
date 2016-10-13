/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Marcaproducto;
import java.util.List;
 
/** 
 * 
 *
 * @author Melendez
 */
public interface BusquedasMarcaProductoLocal {
    /**
     * Obtener el ultimo correlativo de estado.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorreltivoMarcaProductos() throws Exception;
       /**
     * Obtiene la lista de estado documentos.
     * @return List Estado documento.
     * @throws Exception Error generico.
     */
     List <Marcaproducto> buscarMarcaProductos() throws Exception;
}
