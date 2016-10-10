package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.Opcionmenu;
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantebcd.negocio.util.MttoUtil;
import com.asi.restaurantebcd.negocio.util.UserNavigTreeFactory;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.TreeNode;


/**
 * @author luis_portillo
 */
@Named(value = "mttoPerfil")
@ConversationScoped
public class MttoPerfilMB extends MttoUtil<Perfil> implements Serializable{
   
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    CrudBDCLocal ejbCrud;
    
    	private List<Opcionmenu> menusDisponibles = new ArrayList<Opcionmenu>();
	private List<Opcionmenu> menusAsociados = new ArrayList<Opcionmenu>();
	private TreeNode[] menusAgregados;
	private TreeNode menusTreeNode;
        
        private boolean check;
	
	@Inject
	UserNavigTreeFactory perfilMenuTreeFactory;
    
   /**
     * Creates una nueva instancia de MttoPerfilMB
     * Es obligatorio el llamado a setJpql 
     */
    public MttoPerfilMB() {
        super(); //Llama el constructor de la clase padre
        setJpql("select p from Perfil p order by p.idPerfil desc"); 
           //Configura el Jpql a ejecutar para el listado de catalogo
    }
    
    	public void cargarMenusDisponibles(){
		setMenusDisponibles((List<Opcionmenu>) getEntityManager().createQuery("select m from Opcionmenu m where " +
				"m.visible = TRUE and m.menuPadre is not null ORDER BY m.menuPadre.etiqueta ASC ")
				.getResultList());
		check = true;
		for(Opcionmenu opcionMenu : getMenusDisponibles()){
			if(opcionMenu.getSubMenus().contains(getInstance())){
				opcionMenu.setAsociado(true);
			}else{
				check = false;
			}
		}
	}
        
        	public void changeAll(){
		for(Opcionmenu opcionMenu : getMenusDisponibles()){
			opcionMenu.setAsociado(check);
		}
	}
	
	public String guardarAsociacion(){
		//getInstance().setOpcionmenuList(new HashSet<Opcionmenu>());
		for(Opcionmenu opcionMenu : getMenusDisponibles()){
			if(opcionMenu.isAsociado()){
				getInstance().getOpcionesDeMenu().add(opcionMenu);
			}
		}
		return "save";
	}
	
    
    @Override
    protected boolean validateSave() {
        actualizarMenusTree();
        guardarMenusTree();
        return true;
    }

    @Override
    protected boolean validateDelete() {
        this.getInstance().setOpcionesDeMenu(new HashSet<Opcionmenu>());
        return true;
    }

    @Override
    protected boolean validateUpdate() {
        actualizarMenusTree();
        guardarMenusTree();
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
        Perfil result=new Perfil();
        this.setMenusAsociados(new ArrayList<Opcionmenu>(result.getOpcionesDeMenu()));
		loadMenusTree();
        return result;
    }    

    @Override
    protected Perfil select(Object key) {
        try{  
                if(key!=null){
                        key = Integer.parseInt(key.toString()); //Conversión del Key a tipo numerico
                     } 
                  Perfil result =ejbCrud.buscarEntidad(Perfil.class, key);
		return result;
               } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               return null;
           }
        }
    
    public void loadMenusTree(){
        this.setMenusTreeNode(perfilMenuTreeFactory.getNavigTree(this.getMenusAsociados()));
	}
	
	public void guardarMenusTree(){
		this.getInstance().setOpcionesDeMenu(new HashSet<Opcionmenu>());
		for(Opcionmenu opcionMenu: this.getMenusAsociados()){
                        System.out.println("actualizando:  " + opcionMenu.getEtiqueta());
			this.getInstance().getOpcionesDeMenu().add(opcionMenu);
		}
	}
        public void actualizarMenusTree(){
		this.setMenusAsociados(new ArrayList<Opcionmenu>());
		for(TreeNode node:this.getMenusAgregados()){
                    Opcionmenu menu=(Opcionmenu)node.getData();
	            this.getMenusAsociados().add(menu);
                    System.out.println("Added: " + menu.getEtiqueta());
		}
		guardarMenusTree();
		loadMenusTree();
	}

    @Override
    protected void prepareCreate() {
        this.setMenusAsociados(new ArrayList<Opcionmenu>(getInstance().getOpcionesDeMenu()));
		for(Opcionmenu opcionMenu: getInstance().getOpcionesDeMenu()){
			System.out.println("Menus prepare: " + opcionMenu.getEtiqueta());
		}          
			loadMenusTree();
        		cargarMenusDisponibles();
    }

    @Override
    protected void prepareUpdate() {
                        this.setMenusAsociados(new ArrayList<Opcionmenu>(getInstance().getOpcionesDeMenu()));
                      for(Opcionmenu opcionMenu: getInstance().getOpcionesDeMenu()){
			System.out.println("Menus prepare: " + opcionMenu.getEtiqueta());
                      }       
			loadMenusTree();
        		cargarMenusDisponibles();
    }

    /**
     * @return the menusDisponibles
     */
    public List<Opcionmenu> getMenusDisponibles() {
        return menusDisponibles;
    }

    /**
     * @param menusDisponibles the menusDisponibles to set
     */
    public void setMenusDisponibles(List<Opcionmenu> menusDisponibles) {
        this.menusDisponibles = menusDisponibles;
    }

    /**
     * @return the menusAsociados
     */
    public List<Opcionmenu> getMenusAsociados() {
        return menusAsociados;
    }

    /**
     * @param menusAsociados the menusAsociados to set
     */
    public void setMenusAsociados(List<Opcionmenu> menusAsociados) {
        this.menusAsociados = menusAsociados;
    }

    /**
     * @return the menusAgregados
     */
    public TreeNode[] getMenusAgregados() {
        return menusAgregados;
    }

    /**
     * @param menusAgregados the menusAgregados to set
     */
    public void setMenusAgregados(TreeNode[] menusAgregados) {
        this.menusAgregados = menusAgregados;
    }

    /**
     * @return the menusTreeNode
     */
    public TreeNode getMenusTreeNode() {
        return menusTreeNode;
    }

    /**
     * @param menusTreeNode the menusTreeNode to set
     */
    public void setMenusTreeNode(TreeNode menusTreeNode) {
        this.menusTreeNode = menusTreeNode;
    }

    }


    

