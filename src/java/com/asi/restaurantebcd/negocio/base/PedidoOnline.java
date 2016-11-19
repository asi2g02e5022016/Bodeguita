/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.ExistenciaPK;
import com.asi.restaurantbcd.modelo.Impuesto;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.OrdenpedidoPK;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.OrdenpedidodetallePK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class PedidoOnline implements PedidoOnlineLocal {

    @EJB
    private BusquedasComprasLocal busquedasCompras;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @EJB
    private CrudBDCLocal crudBDC;
 
    
    
    
    /**
     * 
     * @param codigoCliente
     * @param usr
     * @param idsucursal
     * @param lstDetalle
     * @throws java.lang.Exception
     */
    @Override
    public void guardarPedidoOnline(Integer codigoCliente,
            String usr, Integer idsucursal,
            List < Ordenpedidodetalle > lstDetalle) throws Exception {
        Impuesto impuestos = crudBDC.buscarEntidad(Impuesto.class, 1);
        if (impuestos == null) {
            throw new Exception("No se encontraron resultados de IVA");
        }
        Double iva =  impuestos.getPorcentaje();
        OrdenpedidoPK idOt = new OrdenpedidoPK();
        Integer idCoorr = busquedasCompras
                .obtenerCorreltivoCompra(idsucursal, 
                        Ordenpedido.class, "idsucursal", "idordenpedido");
        idOt.setIdordenpedido(idCoorr);
        idOt.setIdsucursal(idsucursal);
        
        Ordenpedido ped = crudBDC.buscarEntidad(Ordenpedido.class, idOt);
        if (ped != null) {
            throw new Exception("Ya existe un pedido con este correlativo.");
        } else {
            ped = new Ordenpedido();
            ped.setOrdenpedidoPK(idOt);
        }
        
        ped.setFechapedido(new Date());
        Cliente cli  = 
                crudBDC.buscarEntidad(Cliente.class, codigoCliente);
        ped.setIdcliente(cli);
        Estado est = crudBDC.buscarEntidad(Estado.class, 
                EstadoEnum.PENDIENTE_DESPACHAR.getInteger());
        ped.setIdestado(est);
        Usuario usuario = crudBDC.buscarEntidad(Usuario.class,  usr);
        ped.setIdusuario(usuario);
        ped.setMesa(null);
         ped.setWeb(Integer.parseInt("1"));
        Sucursal suc = crudBDC.buscarEntidad(Sucursal.class, idsucursal);
        ped.setSucursal(suc);
         
        List <Ordenpedidodetalle> lstDet = new ArrayList<>();
        int corle = 0;
        System.out.println("lstDetalle.. " +lstDetalle);
        if (lstDetalle != null && !lstDetalle.isEmpty()) {
            for (Ordenpedidodetalle ordenpedidodetalle : lstDetalle) {
                corle++;
                OrdenpedidodetallePK idOdeDet =new OrdenpedidodetallePK();
                idOdeDet.setIdSucursal(idsucursal);
                idOdeDet.setIdordenpedido(idCoorr);
                idOdeDet.setIdordenpedidodet(corle);
               Ordenpedidodetalle pedDet = new Ordenpedidodetalle();
                pedDet.setOrdenpedidodetallePK(idOdeDet);
                pedDet.setCantidadconfirmada(ordenpedidodetalle.getCantidadconfirmada());
                pedDet.setCantidadsolicitada(ordenpedidodetalle.getCantidadsolicitada());
                Existencia exist = crudBDC.buscarEntidad(Existencia.class, 
                        new ExistenciaPK(ordenpedidodetalle
                                .getIdproducto().getIdproducto(), idsucursal));
                if (exist != null && exist.getCostounitario() != null) {
                    pedDet.setCosto(exist.getCostounitario());
                } else {
                    pedDet.setCosto(Double.parseDouble("0"));
                }
                Producto pro = crudBDC.buscarEntidad(Producto.class, ordenpedidodetalle
                                .getIdproducto().getIdproducto());
                if (pro == null) {
                    throw new Exception("NO se encontro resultado de producto.");
                }
                pedDet.setIdproducto(pro);
                pedDet.setIva(iva);
                pedDet.setOrdenpedido(ped);
                pedDet.setPrecio(pro.getPrecioventa());
                lstDet.add(pedDet);
            }
        }
        System.out.println("lstDet..." +lstDet);
        ped.setOrdenpedidoPK(idOt);
        ped.setSucursal(suc);
         ped.setOrdenpedidodetalleList(lstDet);
         System.out.println("ped,,,,," +ped);
         crudBDC.guardarEntidad(ped);
        
    }
    
    @Override
    public  List <Ordenpedido> lstPedido() {
        StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT o FROM Ordenpedido o ");
            jpql.append(" WHERE o.idestado.idestado  = 5 ");
            Query q = em.createQuery(jpql.toString());
            return q.getResultList();
    }
    /**
     * 
     * @return
     * @throws Exception 
     */
    @Override
    public  Cliente lstClientes(String usuario, String password) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT o FROM Cliente o ");
            jpql.append(" WHERE o.usuario = :usuario ");
            jpql.append(" AND o.password = :password ");
            Query q = em.createQuery(jpql.toString());
            q.setParameter("usuario", usuario);
            q.setParameter("password", password);
            List <Cliente> lstCli = q.getResultList();
            System.out.println("lstCli.." +lstCli);
            if (lstCli == null || lstCli.isEmpty()) {
                throw new Exception("El cliente no tiene  credenciales .");
            }
            if (lstCli.size() >1) {
                 throw new Exception("El codigo de cliente esta repetido.");
            }
            Cliente cli = lstCli.get(0);
            return cli;
          } catch (NoResultException re) {
          throw new Exception("El cliente no tiene  credenciales .");
        
        } catch (Exception e) {
            e.printStackTrace();
             throw new Exception(e);
            
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
  
}
