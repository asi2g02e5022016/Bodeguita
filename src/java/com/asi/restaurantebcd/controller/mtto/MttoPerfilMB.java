package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.util.MttoUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 * @author luis_portillo
 */
@Named(value = "mttoPerfil")
@RequestScoped
public class MttoPerfilMB extends MttoUtil<Perfil> {
   

   /**
     * Creates una nueva instancia de MttoPerfilMB
     * Es obligatorio el llamado a setJpql 
     */
    public MttoPerfilMB() {
        super(); //Llama el constructor de la clase padre
        setJpql("select p from Perfil p"); //Configura el Jpql a ejecutar para el listado de catalogo
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
    }

    /**
     * Retorna una nueva instancia de Perfil
     */
    @Override
    protected Perfil getNew() {
        return new Perfil();
    }    
    
}

