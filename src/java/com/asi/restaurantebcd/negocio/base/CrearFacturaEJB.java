/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantbcd.modelo.Condicionpago;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Facturadetalle;
import com.asi.restaurantbcd.modelo.Facturadetinterface;
import com.asi.restaurantbcd.modelo.Facturaencabezado;
import com.asi.restaurantbcd.modelo.FacturaencabezadoPK;
import com.asi.restaurantbcd.modelo.Facturainterface;
import com.asi.restaurantbcd.modelo.Formapago;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.OrdenpedidoPK;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.OrdenpedidodetallePK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Recetadetalle;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Tipodocumento;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantbcd.modelo.Vwexistencias;
import com.asi.restaurantebcd.negocio.base.BusquedasExistenciasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasPedidoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author siman
 */
//@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class CrearFacturaEJB implements CrearFacturaEJBLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    
    List<Facturainterface> encabezados;
    List<Facturadetinterface> detalle;
    
    @EJB
    BusquedasPedidoLocal ejbPedido;
    
    @EJB
    CrudBDCLocal ejbCrud;
    
    @EJB
    BusquedasExistenciasLocal ejbExistencias;
    
    @EJB
    ProcesosInventariosLocal ejbInventario;
//    @Resource
 //   private UserTransaction trx;
    
  
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void procesarFactura(Facturainterface f) throws IllegalStateException, SecurityException, SystemException, NamingException{
          
          
      try{

              System.out.println("encabezado: " + f.getDocumento());
  //            trx.begin();
              Sucursal sucursal;
             sucursal = (Sucursal) em.createQuery("select c.idsucursal from Caja c where c.caja =:caja")
                               .setParameter("caja", f.getCaja())
                               .getSingleResult();
              System.out.println("sucursal: " + sucursal.getSucursal());
             long count = (long)em.createQuery("SELECT COUNT(f.facturaencabezadoPK.idfactura) FROM Facturaencabezado f "
                                    + " where f.facturaencabezadoPK.idfactura = :idfac and f.facturaencabezadoPK.idserie =:idserie and f.facturaencabezadoPK.idsucursal = :idsucursal")
                               .setParameter("idfac", f.getDocumento().intValue())
                               .setParameter("idserie", f.getSerie())
                               .setParameter("idsucursal", sucursal.getIdsucursal().intValue())
                              .getSingleResult();
            System.out.println("count: " + count);
             if(count>0) {
                 return;
             }
              Ordenpedido pedido;
              OrdenpedidoPK pedidoPk;
              
              pedidoPk = new OrdenpedidoPK();
              pedido = new Ordenpedido();
              
              pedidoPk.setIdsucursal(sucursal.getIdsucursal());
              Integer idpedido = ejbPedido.obtenerCorreltivoPedido();
              pedidoPk.setIdordenpedido(idpedido);
              pedido.setOrdenpedidoPK(pedidoPk);
              pedido.setFechapedido(f.getFechaDocumento());
              pedido.setSucursal(sucursal);
              
              Cliente cliente;
              
              cliente = (Cliente) em.createQuery("select c from Cliente c where c.idcliente = :id")
                        .setParameter("id", f.getCliente()==null?-1:Integer.parseInt(f.getCliente()))
                        .getSingleResult();
              System.out.println("Cliente: " + cliente.getNombre());
              pedido.setIdcliente(cliente);
              
              Usuario usuario;
              
              usuario = (Usuario) em.createQuery("select u from Usuario u where u.idusuario = :id")
                        .setParameter("id", f.getCajero())
                        .getSingleResult();
              System.out.println("usuario: " + usuario.getIdusuario());
              pedido.setIdusuario(usuario);
              
              detalle = em.createQuery("select f from Facturadetinterface f where f.caja =:caja and f.documento =:documento and f.serie =:serie")
                         .setParameter("caja", f.getCaja())
                         .setParameter("documento", f.getDocumento())
                         .setParameter("serie", f.getSerie())
                        .getResultList();
              List <Ordenpedidodetalle> pdetalle=new ArrayList<Ordenpedidodetalle>();
              int iddet=0;
              System.out.println("detalle... ");
              for(Facturadetinterface det:detalle){
              System.out.println("detalle: " + det.getLinea());
                  iddet++;
                   Ordenpedidodetalle pdet = new Ordenpedidodetalle();
                   OrdenpedidodetallePK pdetPk = new OrdenpedidodetallePK();
                   
                   pdetPk.setIdSucursal(sucursal.getIdsucursal());
                   pdetPk.setIdordenpedido(idpedido);
                   pdetPk.setIdordenpedidodet(iddet);
                   
                   pdet.setOrdenpedidodetallePK(pdetPk);
                   pdet.setCantidadsolicitada(det.getUnidades());
                   pdet.setCantidadconfirmada(det.getUnidades());
                   
                   Producto producto;
                   System.out.println("idProd:" + det.getProducto());
                   producto = (Producto) em.createQuery("select p from Producto p where p.idproducto = :id")
                        .setParameter("id", Integer.parseInt(det.getProducto()))
                        .getSingleResult();
                   pdet.setIdproducto(producto);
                   pdet.setPrecio(det.getPrecio());
                   Double iva = det.getIva();
                   pdet.setIva(iva);
                   
                   Vwexistencias pex = null;
                   try {
                   pex = (Vwexistencias) em.createQuery("select p from Vwexistencias p where p.idproducto = :id and p.idsucursal = :idsuc")
                        .setParameter("id", Integer.parseInt(det.getProducto()))
                         .setParameter("idsuc", sucursal.getIdsucursal().intValue())
                        .getSingleResult();}catch(Exception ex){ex.printStackTrace();}
                   
                   Double costoExtendido;
                   if(pex==null){
                     costoExtendido = Double.parseDouble("0");
                   }else{
                     costoExtendido = pex.getCostounitario()*det.getUnidades();
                   }
                   
                   pdet.setCosto(costoExtendido);
                   pdet.setFactDetInterface(det);
                   pdetalle.add(pdet);
              
              }
              
              Estado estado = new Estado();
              estado.setIdestado(EstadoEnum.TERMINADO.getInteger());
              pedido.setIdestado(estado);
              pedido.setOrdenpedidodetalleList(pdetalle);
              
              FacturaencabezadoPK factPk = new FacturaencabezadoPK();
              Facturaencabezado factEnca = new Facturaencabezado();
              
              factPk.setIdfactura(f.getDocumento());
              factPk.setIdserie(f.getSerie());
              factPk.setIdsucursal(sucursal.getIdsucursal());
              
              factEnca.setFacturaencabezadoPK(factPk);
              
              factEnca.setAnulado(false);
              factEnca.setIdcliente(pedido.getIdcliente());
              factEnca.setFechafactura(pedido.getFechapedido());
              
              Formapago formaPago = new Formapago();
              
              Integer idFomraPago  = 3;
              
              if(f.getFormaPago()=="cash"){
                idFomraPago = 3;
              }
              
              if(f.getFormaPago()=="cheque"){
                idFomraPago = 4;
              }
              
              if(f.getFormaPago()=="magcard"){
                idFomraPago = 1;
              }
              formaPago.setIdformapago(idFomraPago);
              
              
              factEnca.setIdformapago(formaPago);

              Caja caja;
              caja = (Caja) em.createQuery("select c from Caja c where c.caja = :caja")
                        .setParameter("caja", f.getCaja())
                        .getSingleResult();
                   
              factEnca.setIdcaja(caja);
              
              Condicionpago condicion = new Condicionpago();
              
              condicion.setIdcondicionpago(1);
              
              factEnca.setIdcondicionpago(condicion);
              
              Tipodocumento tipoDoc = new Tipodocumento();
              tipoDoc.setIdtipodocumento(3);
              factEnca.setIdtipodocumento(tipoDoc);
              
              factEnca.setIdusuario(pedido.getIdusuario());
              List<Facturadetalle> factDetalles = new ArrayList<Facturadetalle>();
              for(Ordenpedidodetalle pdet:pedido.getOrdenpedidodetalleList()){
               Facturadetalle fdet = new Facturadetalle();
               
               fdet.setFacturaencabezado(factEnca);
               fdet.setCantidad(pdet.getCantidadconfirmada());
               
               fdet.setIdproducto(pdet.getIdproducto());
               fdet.setCosto(pdet.getCosto());
               fdet.setIva(pdet.getIva());
               fdet.setPrecio(pdet.getPrecio());
               
               factDetalles.add(fdet);
              }
              
              factEnca.setFacturadetalleList(factDetalles);
              
              pedido = ejbCrud.guardarEntidad(pedido);
              
              factEnca.setOrdenpedido(pedido);
              
              ejbCrud.persistirEntidad(factEnca);
              
              f.setCargada(true);
              
              ejbCrud.persistirEntidad(f);
            //afectarExistencia(Double cantidad, Producto producto,
           // Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv,
           // boolean  calcularCstProm)          
              for(Facturadetalle fdet:factDetalles){
                   if(fdet.getIdproducto().getIdreceta()==null){
                   ejbInventario.afectarExistencia(new Double(fdet.getCantidad()), fdet.getIdproducto() , fdet.getFacturaencabezado().getIdusuario(), fdet.getFacturaencabezado().getOrdenpedido().getSucursal(), new Double(fdet.getCosto()),true,false);
                   }else {
                     for(Recetadetalle rdet: fdet.getIdproducto().getIdreceta().getRecetadetalleList()){

                         ejbInventario.afectarExistencia(new Double(fdet.getCantidad()*rdet.getCantidad()), rdet.getProducto() , fdet.getFacturaencabezado().getIdusuario(), fdet.getFacturaencabezado().getOrdenpedido().getSucursal(), new Double(rdet.getProducto().getPreciocompra()),true,false);
                     }
                   }
              }
              
    //          trx.commit();
          }catch(Exception ex){ 
              
              ex.printStackTrace();
             // trx.rollback();
              }
        }
  
   public void procesarFactura(Ordenpedido p, String serie, Integer numeroDocu, Integer idCaja) throws IllegalStateException, SecurityException, SystemException, NamingException{
           
        try {
            Ordenpedido pedido = p;
            
            FacturaencabezadoPK factPk = new FacturaencabezadoPK();
            Facturaencabezado factEnca = new Facturaencabezado();
            
            Integer documento =numeroDocu;
            
            
            factPk.setIdfactura(documento);
            factPk.setIdserie(serie);
            
            factPk.setIdsucursal(pedido.getOrdenpedidoPK().getIdsucursal());
            
            factEnca.setFacturaencabezadoPK(factPk);
            
            factEnca.setAnulado(false);
            factEnca.setIdcliente(pedido.getIdcliente());
            factEnca.setFechafactura(pedido.getFechapedido());
            
            Formapago formaPago = new Formapago();
            
            Integer idFomraPago  = 3;
            
            formaPago.setIdformapago(idFomraPago);
            
            
            factEnca.setIdformapago(formaPago);
            
            Caja caja=null;
            
            
            factEnca.setIdcaja(caja);
            
            Condicionpago condicion = new Condicionpago();
            
            condicion.setIdcondicionpago(1);
            
            factEnca.setIdcondicionpago(condicion);
            
            Tipodocumento tipoDoc = new Tipodocumento();
            tipoDoc.setIdtipodocumento(1);
            factEnca.setIdtipodocumento(tipoDoc);
            
            factEnca.setIdusuario(pedido.getIdusuario());
            List<Facturadetalle> factDetalles = new ArrayList<Facturadetalle>();
            for(Ordenpedidodetalle pdet:pedido.getOrdenpedidodetalleList()){
                Facturadetalle fdet = new Facturadetalle();
                
                fdet.setFacturaencabezado(factEnca);
                fdet.setCantidad(pdet.getCantidadconfirmada());
                
                fdet.setIdproducto(pdet.getIdproducto());
                fdet.setCosto(pdet.getCosto());
                fdet.setIva(pdet.getIva());
                fdet.setPrecio(pdet.getPrecio());
                
                factDetalles.add(fdet);
            }
            
            factEnca.setFacturadetalleList(factDetalles);
            
            
            
            factEnca.setOrdenpedido(pedido);
            
            for(Facturadetalle fdet:factDetalles){
                   if(fdet.getIdproducto().getIdreceta()==null){
                   ejbInventario.afectarExistencia(new Double(fdet.getCantidad()), fdet.getIdproducto() , fdet.getFacturaencabezado().getIdusuario(), fdet.getFacturaencabezado().getOrdenpedido().getSucursal(), new Double(fdet.getCosto()),true,false);
                   }else {
                     for(Recetadetalle rdet: fdet.getIdproducto().getIdreceta().getRecetadetalleList()){
                         ejbInventario.afectarExistencia(new Double(fdet.getCantidad()*rdet.getCantidad()), rdet.getProducto() , fdet.getFacturaencabezado().getIdusuario(), fdet.getFacturaencabezado().getOrdenpedido().getSucursal(), new Double(rdet.getProducto().getPreciocompra()),true,false);
                     }
                   }
              }
            
            factEnca.setIdcaja(new Caja(idCaja));
            ejbCrud.persistirEntidad(factEnca);
            
            pedido.setIdestado(new Estado(EstadoEnum.TERMINADO.getInteger()));
            
            ejbCrud.guardarEntidad(pedido);
            
        } catch (Exception ex) {
            Logger.getLogger(CrearFacturaEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public List<Numerofiscal> numeroFiscalList(Sucursal s) throws IllegalStateException, SecurityException, SystemException, NamingException {
             List<Numerofiscal> result;
             
             result = em.createQuery("select n from Numerofiscal n where n.idcaja.idsucursal = :s").setParameter("s", s).getResultList();
             
             return result;
    }

    @Override
    public List<Caja> cajaList(Sucursal s) throws IllegalStateException, SecurityException, SystemException, NamingException {
         List<Caja> result;
             
             result = em.createQuery("select c from Caja c where c.idsucursal = :s").setParameter("s", s).getResultList();
             
             return result;
    }
   
   
  
}
