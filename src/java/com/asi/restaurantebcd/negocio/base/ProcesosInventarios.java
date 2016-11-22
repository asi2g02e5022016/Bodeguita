/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.ExistenciaPK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Usuario;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ProcesosInventarios implements ProcesosInventariosLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @EJB
    private CrudBDCLocal crudBDC;

    /**
     * Calculo de existencia de productos.
     *
     * @param cantidad Cantidad a transaccionar de el documento.
     * @param producto Entidad productos asociado a cargar o abonar el
     * inventario.
     * @param usr codigode usuario que ejecuta la accion.
     * @param sucursal Sucursal que esta afectando la existencia.
     * @param costo costo de producto.
     * @param descargaInv true si quiere descargar cantidades de inventario,
     * false si quiere cargar existencias al inventario.
     * @param calcularCstProm true si quiere que recalcule el costo promedio.
     * @throws Exception Error generico.
     */
    @Override
    public void afectarExistencia(Double cantidad, Producto producto,
            Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv,
            boolean calcularCstProm) throws Exception {
        try {
            if (cantidad == null) {
                throw new Exception("La cantidad es obligatorio.");
            }
            Double exisActual;
            Double costoPromedioTotalActual = Double.valueOf("0");
            Double costoPromedioActual = Double.valueOf("0");
            Double costoTotalTransaccion = costo * cantidad;
            Double exisTotal;
            Double costoTotal;
            Double costoPromedioNew;
            ExistenciaPK idExis = new ExistenciaPK();
            idExis.setIdsucursal(sucursal.getIdsucursal());
            idExis.setIdproducto(producto.getIdproducto());
            Existencia exis = crudBDC.buscarEntidad(Existencia.class, idExis);
            if (exis == null) {
                exis = new Existencia();
                exis.setExistenciaPK(idExis);
                exis.setReservado(0D);
                exis.setTransito(0D);
            }
            if (exis.getValor() != null
                    && !exis.getValor().equals(Double.valueOf("0"))) {
                exisActual = exis.getValor();
                if (descargaInv) {
                    exisTotal = exisActual - cantidad;
                } else {
                    exisTotal = exisActual + cantidad;
                }
                if (costoPromedioActual != null) {
                    costoPromedioTotalActual = exisActual * exis.getValor();
                }
            } else {
                exisTotal = descargaInv?-cantidad:cantidad;
            }
            if (calcularCstProm) {
                costoTotal = costoPromedioTotalActual + costoTotalTransaccion;
                costoPromedioNew = costoTotal / exisTotal;
                exis.setCostounitario(costoPromedioNew);
            } else {
                exis.setCostounitario(costo);
            }

            exis.setValor(exisTotal);
            exis.setSucursal(sucursal);
            crudBDC.guardarEntidad(exis);
        } catch (Exception ex) {
            Logger.getLogger(ProcesosInventarios.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    /**
     * Metodo de busqueda de comras.
     * @param sucursal sucursal 
     * @param fechaInicia fecha inicial de compra.
     * @param fechaFinal fecha final de compra.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Compra> buscarCompras(Sucursal sucursal,
            Date fechaInicia, Date fechaFinal) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT c FROM Compra c WHERE 1 = 1 ");
            if (sucursal != null) {
                jpql.append(" AND c.compraPK.idsucursal = :sucursal ");
            }
            if (fechaInicia != null && fechaFinal != null) {
             jpql.append(" AND c.fechacompra BETWEEN :fechaInicia AND :fechaFinal ");
            }
            Query query = em.createQuery(jpql.toString());
            if (sucursal != null) {
                query.setParameter("sucursal", sucursal.getIdsucursal());
            }
            if (fechaInicia != null && fechaFinal != null) {
            query.setParameter("fechaInicia", fechaInicia);
            query.setParameter("fechaFinal", fechaFinal);
            }
            return query.getResultList();
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void afectarTransito(Double cantidad, Producto producto, Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv, boolean  calcularCstProm) throws Exception {
          try {
            if (cantidad == null) {
                throw new Exception("La cantidad es obligatorio.");
            }
            Double transitoActual;
            Double transitoTotal;
            ExistenciaPK idExis = new ExistenciaPK();
            idExis.setIdsucursal(sucursal.getIdsucursal());
            idExis.setIdproducto(producto.getIdproducto());
            Existencia exis = crudBDC.buscarEntidad(Existencia.class, idExis);
            if (exis == null) {
                exis = new Existencia();
                exis.setExistenciaPK(idExis);
                exis.setReservado(0D);
                exis.setValor(0D);
                exis.setCostounitario(producto.getPreciocompra());
            }
            if (exis.getTransito()!= null
                    && !exis.getTransito().equals(Double.valueOf("0"))) {
                transitoActual = exis.getTransito();
                if (descargaInv) {
                    transitoTotal = transitoActual - cantidad;
                } else {
                    transitoTotal = transitoActual + cantidad;
                }
              
            } else {
                transitoTotal = descargaInv?-cantidad:cantidad;
            }
            
            exis.setTransito(transitoTotal);
            exis.setSucursal(sucursal);
            crudBDC.guardarEntidad(exis);
        } catch (Exception ex) {
            Logger.getLogger(ProcesosInventarios.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void afectarReservado(Double cantidad, Producto producto, Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv, boolean calcularCstProm) throws Exception {
        try {
            if (cantidad == null) {
                throw new Exception("La cantidad es obligatorio.");
            }
            Double reservadoActual;
            Double reservadoTotal;
            ExistenciaPK idExis = new ExistenciaPK();
            idExis.setIdsucursal(sucursal.getIdsucursal());
            idExis.setIdproducto(producto.getIdproducto());
            Existencia exis = crudBDC.buscarEntidad(Existencia.class, idExis);
            if (exis == null) {
                exis = new Existencia();
                exis.setExistenciaPK(idExis);
                exis.setTransito(0D);
                exis.setCostounitario(producto.getPreciocompra());
                exis.setValor(0D);
            }
            if (exis.getReservado()!= null
                    && !exis.getReservado().equals(Double.valueOf("0"))) {
                reservadoActual = exis.getReservado();
                if (descargaInv) {
                    reservadoTotal = reservadoActual - cantidad;
                } else {
                    reservadoTotal = reservadoActual + cantidad;
                }
              
            } else {
                reservadoTotal = descargaInv?-cantidad:cantidad;
            }
            
            exis.setReservado(reservadoTotal);
            exis.setSucursal(sucursal);
            crudBDC.guardarEntidad(exis);
        } catch (Exception ex) {
            Logger.getLogger(ProcesosInventarios.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

}
