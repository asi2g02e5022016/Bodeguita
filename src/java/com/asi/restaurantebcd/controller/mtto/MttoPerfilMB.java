package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.util.MttoUtil;
import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;


/**
 * @author luis_portillo
 */
@Named(value = "mttoPerfil")
@ConversationScoped
public class MttoPerfilMB extends MttoUtil<Perfil> implements Serializable{
   
	
    private static final long serialVersionUID = 1L;
   /**
     * Creates una nueva instancia de MttoPerfilMB
     * Es obligatorio el llamado a setJpql 
     */
    public MttoPerfilMB() {
        super(); //Llama el constructor de la clase padre
        setJpql("select p from Perfil p order by p.idPerfil desc"); //Configura el Jpql a ejecutar para el listado de catalogo
    }


     /**
     * Se ejecuta al realizar la carga del mantenimiento
     */
    @Override
    protected void onLoad() {
        if(this.getKey()!=null){
        this.setKey(Integer.parseInt(this.getKey().toString())); //Conversión del Key a tipo numerico
       } 
    }

    @Override
    protected void postSave() {
        addMessage(INFO, "Guardado", "Perfil guardado correctamente");
        this.endConversation();
    }
    
    @Override
    protected void postUpdate() {
      addMessage(INFO, "Editado", "Perfil editado correctamente");
      this.endConversation();
    }
    
    @Override
    protected void postDelete() {
        addMessage(INFO, "Eliminado", "Perfil eliminado correctamente");
        this.endConversation();
    }

    /**
     * Retorna una nueva instancia de Perfil
     * @return 
     */
    @Override
    protected Perfil getNew() {
        return new Perfil();
    }    


    
}

