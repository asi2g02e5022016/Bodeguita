package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.util.MttoUtil;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;


/**
 * @author luis_portillo
 */
@Named(value = "mttoPerfil")
@ConversationScoped
public class MttoPerfilMB extends MttoUtil<Perfil> implements Serializable{
   
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    CrudBDCLocal ejbCrud;
    
   /**
     * Creates una nueva instancia de MttoPerfilMB
     * Es obligatorio el llamado a setJpql 
     */
    public MttoPerfilMB() {
        super(); //Llama el constructor de la clase padre
        setJpql("select p from Perfil p order by p.idPerfil desc"); 
           //Configura el Jpql a ejecutar para el listado de catalogo
    }
    
    @Override
    protected boolean validateSave() {
        return true;
    }

    @Override
    protected boolean validateDelete() {
        return true;
    }

    @Override
    protected boolean validateUpdate() {
        return true;
    }

    @Override
    protected void postSave() {
        addMessage(INFO, "Guardado", "Perfil guardado correctamente");
    }
    
    @Override
    protected void postUpdate() {
      addMessage(INFO, "Editado", "Perfil editado correctamente");
    }
    
    @Override
    protected void postDelete() {
        addMessage(INFO, "Eliminado", "Perfil eliminado correctamente");
    }

    /**
     * Retorna una nueva instancia de Perfil
     * @return 
     */
    @Override
    protected Perfil getNew() {
        return new Perfil();
    }    

    @Override
    protected Perfil select(Object key) {
        try{  
                if(key!=null){
                        key = Integer.parseInt(key.toString()); //Conversión del Key a tipo numerico
                     } 
               return ejbCrud.buscarEntidad(this.getNew().getClass(), key);
               } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               return null;
           }
        }

    }


    

