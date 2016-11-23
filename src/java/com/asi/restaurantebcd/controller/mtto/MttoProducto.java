package com.asi.restaurantebcd.controller.mtto;


import com.asi.restaurantbcd.modelo.Grupoproducto;
import com.asi.restaurantbcd.modelo.Marcaproducto;
import com.asi.restaurantbcd.modelo.Medida;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Tipoproducto;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMarcaProductoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasMedidasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasTipoProductoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author hp
 */
@ManagedBean(name = "mttoProducto")
@ViewScoped
public class MttoProducto implements Serializable {

    /**
     * Creates a new instance of MttoProducto
     */
    public MttoProducto() {
    }
//           List <SelectItem> lstGrupos = new ArrayList<>();
    
    @PostConstruct
    public void postContruction() { 
        try {
//            sesion = Utilidades.findBean("sessionUsr");
//            if (sesion == null) {
//                alert("Debe Iniciar Sesion", FacesMessage.SEVERITY_FATAL);
//                FacesContext.getCurrentInstance().getViewRoot().
//                        getViewMap().clear();
//                String url = "http://localhost:8080/RestaurantBDC";
//                FacesContext.getCurrentInstance().getExternalContext().
//                        redirect(url);
//            }

         this.lstProducto = this.ejbBusqMttoP.buscarProd();
         this.lstMarcaProducto = this.ejbBusqLocal.buscarMarcaProductos();
         this.lstGrupoProducto = this.ejbBusqMttoP.buscarGrupoProducto();
         this.lstTipoProducto = this.ejbBusqMttoo.buscarTipoProducto();
         this.lstMedida = this.busquedasMedidas.buscarMedida();
         this.activo = true;
         this.preciocompra=0.0;
         this.precioventa = 0.0;
                  
           
          
         
               
//      List <SelectItem> lstTemp = new ArrayList<>();
//            for (Grupoproducto grupo : lstGrupoProducto) {
//                SelectItemGroup grItem  = new SelectItemGroup(grupo.getGrupoproducto());
//                          List <Grupoproducto> lstHijo =ejbBusqMttoP
//                            .buscarGrupoHijoProducto(idGrupoProducto);
//                          SelectItem[] item = null ;
//                          if (lstHijo != null) {
//                             lstTemp = new ArrayList<>();
//                              for (int i = 0; lstHijo.size() > 0; i++) {
//                                  Grupoproducto tm = lstHijo.get(i);
//                                  item[i] = new SelectItem(tm.getIdgrupoproducto(),
//                                          tm.getGrupoproducto());
//                                  
//                              }
////                              for (Grupoproducto grupoproducto : lstHijo) {
////                                  
////                                  SelectItem[] item  = new SelectItem(grupoproducto.getIdgrupoproducto(),
////                                          grupoproducto.getGrupoproducto());
////                                  lstTemp.add(item);
////                              }
//                          }
//                          System.out.println("lstt.." + lstTemp);
//                      grItem.setSelectItems(item);
//                lstGrupos.add(grItem);
//                
//            }

        
         
//           System.out.println(lstCompania);            
            System.out.println("load");
        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
        
    }
    
      //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /* busca beas de seciones activas */
    private SessionUsr sesion;

    /* variable de pk tabla producto*/
    private Integer idProducto;   
    /* variable de fk tabla marca producto*/
    private Integer idMarcaProducto;
    /* variable de fk tabla grupo producto*/
    private Integer idGrupoProducto;
     /* variable de fk tabla tipo producto*/
    private Integer idTipoProducto;
     /* variable de fk tabla medida*/
    private Integer idMedida;
     /* variable de fk tabla usuario*/
    private String idUsuario;
    
    private  Integer idReceta;
//    private Integer idReceta;
    
    /* variable de descripcion del producto*/
    private String producto;
    /* variable que contiene la fecha de creacion de producto*/
    private Date fechaCreacion;
   
   /*variable precio compra*/
    private Double preciocompra;
    /*variable precio venta*/
    private Double precioventa;
    /* variable  vendible*/
    private Boolean vendible;
    /*variable excento*/
    private Boolean excento; 
    /*variable activo*/
    private Boolean activo;
    
    /* constructor clase producto  */
    private Producto productoConstructor;
    /*constructor clase sucrsal*/
    private Marcaproducto marcaConstructor;
    /*constructor clase grupo producto*/
    private Grupoproducto gproductoConstructor;
    /*constructor clase tipo producto*/
    private Tipoproducto tproductoConstructor;
    /*constructor clase mediad*/
    private Medida medidaConstructor;
    /*constructor clase usuario*/
    private Usuario usuarioConstructor;
    
    private Receta recetaConstructor;
    
    
    //atributo que muestra en pantalla listado de impuestos
    private List<Producto> lstProducto;
    private List<Sucursal> lstSucursal;
    private List<Marcaproducto> lstMarcaProducto;
    private List<Grupoproducto> lstGrupoProducto;
    private List<Tipoproducto> lstTipoProducto;
    private List<Medida> lstMedida;
    private List<Usuario> lstUsario;
    private List<Receta> lstReceta;
//    private DataTable tablaProducto = new DataTable();
    
    

    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
   @EJB
    private CrudBDCLocal crud;

    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedasMttoLocal ejbBusqMtto;
    @EJB
    private BusquedasProductosLocal ejbBusqMttoP;
    @EJB
    private BusquedasMarcaProductoLocal ejbBusqLocal;
    @EJB
    private BusquedasMedidasLocal busquedasMedidas;
    @EJB
    private BusquedasTipoProductoLocal ejbBusqMttoo;
    
  
 
    //</editor-fold >
    
      //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    public void limpiarPantalla() {
       idProducto = null;
       idMarcaProducto = null;
       idGrupoProducto= null;
       idTipoProducto = null;
       idMedida = null;
       preciocompra = null;
       precioventa= null;
       producto = null;
       lstProducto = null;
        this.productoConstructor = null;
            this.gproductoConstructor = null;
            this.marcaConstructor = null;
            this.medidaConstructor = null;
            this.tproductoConstructor = null;
            this.usuarioConstructor = null;
       
       
        System.err.println("si entra");
       
       } 
    public void guardarProducto() {
        try {
            
            if (idGrupoProducto == null) {
                alert("Selecione una Grupo de Producto.", FacesMessage.SEVERITY_WARN);
                return;
            }
            if (idTipoProducto == null) {
                alert("Selecione una Tipo de Producto.", FacesMessage.SEVERITY_WARN);
                return;
            }
            
            if (producto == null || producto.equals("")) {
                alert("Descripcion de departamento es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (idMarcaProducto == null) {
                alert("Selecione una Marca de Producto.", FacesMessage.SEVERITY_WARN);
                return;
            }
            
            if (idMedida == null) {
                alert("Selecione una Medida.", FacesMessage.SEVERITY_WARN);
                return;
            }
                        
           productoConstructor = new Producto();
           marcaConstructor = new Marcaproducto();
           gproductoConstructor = new Grupoproducto();
           tproductoConstructor = new Tipoproducto();
           medidaConstructor = new Medida();
           usuarioConstructor = new Usuario();
        
                       
                     
                 
            marcaConstructor.setIdmarcaproducto(idMarcaProducto);
            productoConstructor.setIdmarcaproducto(marcaConstructor);
            
            gproductoConstructor.setIdgrupoproducto(idGrupoProducto);
            productoConstructor.setIdgrupoproducto(gproductoConstructor);
            
            tproductoConstructor.setIdtipoproducto(idTipoProducto);
            productoConstructor.setIdtipoproducto(tproductoConstructor);
            
            medidaConstructor.setIdmedida(idMedida);
            productoConstructor.setIdmedida(medidaConstructor);
            
            usuarioConstructor.setIdusuario(idUsuario);
            productoConstructor.setIdusuario(usuarioConstructor);
            
            productoConstructor.setProducto(producto.trim().toUpperCase());
            productoConstructor.setFechacreacion(new Date());
            productoConstructor.setActivo(activo);
            productoConstructor.setPreciocompra(preciocompra);
            productoConstructor.setPrecioventa(precioventa);
            productoConstructor.setVendible(vendible);
            productoConstructor.setExcento(excento);
                      
//            sesion = Utilidades.findBean("sessionUsr");
//            idUsuario = sesion.getUsuario().getIdusuario();            
    
//             productoConstructor.setIdusuario(usuarioConstructor);
            
            crud.guardarEntidad(this.productoConstructor);
           
            
            alert("Producto ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
          
            this.productoConstructor = null;
            this.gproductoConstructor = null;
            this.marcaConstructor = null;
            this.medidaConstructor = null;
            this.tproductoConstructor = null;
            this.usuarioConstructor = null;
            
               this.lstProducto = this.ejbBusqMttoP.buscarProd();
                                     
        } catch (Exception ex) {

            Logger.getLogger(MttoProducto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("ERROR EN EL GUARDAR PRODUCTO", FacesMessage.SEVERITY_ERROR);

        }
    }
    
    ///////////////////////////////////////////////////////
    public void actualizarProducto(RowEditEvent event) {
        System.out.println("Entro...");
        try {
            
            if (event.getObject() != null) {
                        System.out.println("Debug 1...");
            Producto imp = (Producto) event.getObject();                

                System.out.println("Debug 2..." + imp.getProducto());
                crud.guardarEntidad(imp);
                System.out.println("Actualizado..." + imp.getPreciocompra());
                alert("Producto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
            this.productoConstructor = null;
            this.gproductoConstructor = null;
            this.marcaConstructor = null;
            this.medidaConstructor = null;
            this.tproductoConstructor = null;
            this.usuarioConstructor = null;
            this.recetaConstructor = null;
            
            this.lstProducto = this.ejbBusqMttoP.buscarProd();
            imp = null;
//           this.lstProducto= this.ejbBusMtto.buscarProducto();
            }else{
                System.out.println("Objeto nulo...");
            }
      }catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    // eliminar productos
     public void eliminarProducto(Producto p) {
        try {
            if (p != null) {

                Producto imp = p;
                if (crud.eliminarEntidad(imp) == true) {
                    lstProducto.remove(imp);
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
//                    this.lstProducto = this.ejbBusMtto.buscarProducto();
                    this.productoConstructor = null;

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    
     public void buscarProducto() {
        try {
          this.lstProducto = this.ejbBusqMttoP.buscarProd();
         this.lstMarcaProducto = this.ejbBusqLocal.buscarMarcaProductos();
         this.lstGrupoProducto = this.ejbBusqMttoP.buscarGrupoProducto();
          this.lstTipoProducto = this.ejbBusqMttoo.buscarTipoProducto();
         this.lstMedida = this.busquedasMedidas.buscarMedida();
        this.lstReceta = this.ejbBusqMttoP.buscarReceta();
         sesion = Utilidades.findBean("sessionUsr");
         idUsuario = sesion.getUsuario().getIdusuario();
         
         
         System.out.println(lstProducto);
           
         if (this.lstProducto == null || this.lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    
    
       public void onCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
    }

    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "Problemas al realizar operación";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
   
      //</editor-fold >
    
    
    //<editor-fold  defaultstate="collapsed" desc="Getter and Setter" >
    
    public SessionUsr getSesion() {
        return sesion;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

   public Integer getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public Integer getIdGrupoProducto() {
        return idGrupoProducto;
    }

    public void setIdGrupoProducto(Integer idGrupoProducto) {
        this.idGrupoProducto = idGrupoProducto;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Producto getProductoConstructor() {
        return productoConstructor;
    }

    public void setProductoConstructor(Producto productoConstructor) {
        this.productoConstructor = productoConstructor;
    }

    public Marcaproducto getMarcaConstructor() {
        return marcaConstructor;
    }

    public void setMarcaConstructor(Marcaproducto marcaConstructor) {
        this.marcaConstructor = marcaConstructor;
    }

    public Grupoproducto getGproductoConstructor() {
        return gproductoConstructor;
    }

    public void setGproductoConstructor(Grupoproducto gproductoConstructor) {
        this.gproductoConstructor = gproductoConstructor;
    }

    public Tipoproducto getTproductoConstructor() {
        return tproductoConstructor;
    }

    public void setTproductoConstructor(Tipoproducto tproductoConstructor) {
        this.tproductoConstructor = tproductoConstructor;
    }

    public Medida getMedidaConstructor() {
        return medidaConstructor;
    }

    public void setMedidaConstructor(Medida medidaConstructor) {
        this.medidaConstructor = medidaConstructor;
    }

    public Usuario getUsuarioConstructor() {
        return usuarioConstructor;
    }

    public void setUsuarioConstructor(Usuario usuarioConstructor) {
        this.usuarioConstructor = usuarioConstructor;
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public List<Marcaproducto> getLstMarcaProducto() {
        return lstMarcaProducto;
    }

    public void setLstMarcaProducto(List<Marcaproducto> lstMarcaProducto) {
        this.lstMarcaProducto = lstMarcaProducto;
    }

    public List<Grupoproducto> getLstGrupoProducto() {
        return lstGrupoProducto;
    }

    public void setLstGrupoProducto(List<Grupoproducto> lstGrupoProducto) {
        this.lstGrupoProducto = lstGrupoProducto;
    }

    public List<Tipoproducto> getLstTipoProducto() {
        return lstTipoProducto;
    }

    public void setLstTipoProducto(List<Tipoproducto> lstTipoProducto) {
        this.lstTipoProducto = lstTipoProducto;
    }

    public List<Medida> getLstMedida() {
        return lstMedida;
    }

    public void setLstMedida(List<Medida> lstMedida) {
        this.lstMedida = lstMedida;
    }

    public List<Usuario> getLstUsario() {
        return lstUsario;
    }

    public void setLstUsario(List<Usuario> lstUsario) {
        this.lstUsario = lstUsario;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasProductosLocal getEjbBusMtto() {
        return ejbBusqMttoP;
    }

    public Double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(Double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public Boolean getVendible() {
        return vendible;
    }

    public void setVendible(Boolean vendible) {
        this.vendible = vendible;
    }

    public Boolean getExcento() {
        return excento;
    }

    public void setExcento(Boolean excento) {
        this.excento = excento;
    }

    public BusquedasMttoLocal getEjbBusqMtto() {
        return ejbBusqMtto;
    }

    public void setEjbBusqMtto(BusquedasMttoLocal ejbBusqMtto) {
        this.ejbBusqMtto = ejbBusqMtto;
    }

    public BusquedasProductosLocal getEjbBusqMttoP() {
        return ejbBusqMttoP;
    }

    public void setEjbBusqMttoP(BusquedasProductosLocal ejbBusqMttoP) {
        this.ejbBusqMttoP = ejbBusqMttoP;
    }

    public BusquedasMarcaProductoLocal getEjbBusqLocal() {
        return ejbBusqLocal;
    }

    public void setEjbBusqLocal(BusquedasMarcaProductoLocal ejbBusqLocal) {
        this.ejbBusqLocal = ejbBusqLocal;
    }

    public BusquedasMedidasLocal getBusquedasMedidas() {
        return busquedasMedidas;
    }

    public void setBusquedasMedidas(BusquedasMedidasLocal busquedasMedidas) {
        this.busquedasMedidas = busquedasMedidas;
    }

    public BusquedasTipoProductoLocal getEjbBusqMttoo() {
        return ejbBusqMttoo;
    }

    public void setEjbBusqMttoo(BusquedasTipoProductoLocal ejbBusqMttoo) {
        this.ejbBusqMttoo = ejbBusqMttoo;
    }

    public void setEjbBusMtto(BusquedasProductosLocal ejbBusMtto) {
        this.ejbBusqMttoP = ejbBusqMttoP;
    }
    
    public Receta getRecetaConstructor() {
        return recetaConstructor;
    }

    public void setRecetaConstructor(Receta recetaConstructor) {
        this.recetaConstructor = recetaConstructor;
    }

    public List<Receta> getLstReceta() {
        return lstReceta;
    }

    public void setLstReceta(List<Receta> lstReceta) {
        this.lstReceta = lstReceta;
    }
    
     //</editor-fold >

//    public List<SelectItem> getLstGrupos() {
//        return lstGrupos;
//    }
//
//    public void setLstGrupos(List<SelectItem> lstGrupos) {
//        this.lstGrupos = lstGrupos;
//    }

    

    
    
    
    
    
    
    
    
}
