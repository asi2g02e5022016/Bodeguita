package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.OpcionMenu;
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.util.MttoUtil;
import com.asi.restaurantbcd.util.UserNavigTreeFactory;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    	private List<OpcionMenu> menusDisponibles = new ArrayList<OpcionMenu>();
	private List<OpcionMenu> menusAsociados = new ArrayList<OpcionMenu>();
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
		setMenusDisponibles((List<OpcionMenu>) getEntityManager().createQuery("select m from OpcionMenu m where " +
				"m.estado like 'ACT' and m.menuPadre is not null ORDER BY m.menuPadre.etiqueta ASC ")
				.getResultList());
		check = true;
		for(OpcionMenu opcionMenu : getMenusDisponibles()){
			if(opcionMenu.getSubMenus().contains(getInstance())){
				opcionMenu.setAsociado(true);
			}else{
				check = false;
			}
		}
	}
        
        	public void changeAll(){
		for(OpcionMenu opcionMenu : getMenusDisponibles()){
			opcionMenu.setAsociado(check);
		}
	}
	
	public String guardarAsociacion(){
		//getInstance().setOpcionesDeMenu(new HashSet<OpcionMenu>());
		for(OpcionMenu opcionMenu : getMenusDisponibles()){
			if(opcionMenu.isAsociado()){
				//getInstance().getOpcionesDeMenu().add(opcionMenu);
			}
		}
		return "save";
//		if(this.modify()){
//			return "save";
//		}else{
//			return "error";
//		}
		 
	}
	
    
    @Override
    protected boolean validateSave() {
        actualizarMenusTree();
        guardarMenusTree();
        return true;
    }

    @Override
    protected boolean validateDelete() {
        actualizarMenusTree();
        guardarMenusTree();
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
//        this.setMenusAsociados(new ArrayList<OpcionMenu>(result.getOpcionesDeMenu()));
//		loadMenusTree();
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
//		this.getInstance().setOpcionesDeMenu(new HashSet<OpcionMenu>());
//		for(OpcionMenu opcionMenu: this.getMenusAsociados()){
//			this.getInstance().getOpcionesDeMenu().add(opcionMenu);
//		}
	}
        public void actualizarMenusTree(){
		this.setMenusAsociados(new ArrayList<OpcionMenu>());
		for(TreeNode node:this.getMenusAgregados()){
                    OpcionMenu menu=(OpcionMenu)node.getData();
			this.getMenusAsociados().add(menu);
		}
		guardarMenusTree();
		loadMenusTree();
	}

    @Override
    protected void prepareCreate() {
//        this.setMenusAsociados(new ArrayList<OpcionMenu>(getInstance().getOpcionesDeMenu()));
//		for(OpcionMenu opcionMenu: getInstance().getOpcionesDeMenu()){
//			System.out.println("Menus prepare: " + opcionMenu.getEtiqueta());
//		}          
			loadMenusTree();
        		cargarMenusDisponibles();
    }

    @Override
    protected void prepareUpdate() {
//                        this.setMenusAsociados(new ArrayList<OpcionMenu>(getInstance().getOpcionesDeMenu()));
//                      for(OpcionMenu opcionMenu: getInstance().getOpcionesDeMenu()){
//			System.out.println("Menus prepare: " + opcionMenu.getEtiqueta());
//                      }       
			loadMenusTree();
        		cargarMenusDisponibles();
    }

    /**
     * @return the menusDisponibles
     */
    public List<OpcionMenu> getMenusDisponibles() {
        return menusDisponibles;
    }

    /**
     * @param menusDisponibles the menusDisponibles to set
     */
    public void setMenusDisponibles(List<OpcionMenu> menusDisponibles) {
        this.menusDisponibles = menusDisponibles;
    }

    /**
     * @return the menusAsociados
     */
    public List<OpcionMenu> getMenusAsociados() {
        return menusAsociados;
    }

    /**
     * @param menusAsociados the menusAsociados to set
     */
    public void setMenusAsociados(List<OpcionMenu> menusAsociados) {
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


    

