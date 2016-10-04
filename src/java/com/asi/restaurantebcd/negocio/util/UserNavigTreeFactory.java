package com.asi.restaurantebcd.negocio.util;

import com.asi.restaurantbcd.modelo.OpcionMenu;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.controller.seguridad.MenuChildList;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SuppressWarnings("unused")
@Dependent
public class UserNavigTreeFactory implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private MenuChildList childList;
	
	@Inject
	SessionUsr loginUser; 
	
	private TreeNode root;
	HashMap<Integer,TreeNode> addedNodes;
	Usuario user;
	List<OpcionMenu> seleccionados=new ArrayList<OpcionMenu>();
	
	public UserNavigTreeFactory(){}
	
	public List<OpcionMenu> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<OpcionMenu> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public TreeNode getNavigTree(List<OpcionMenu> seleccionados){
		this.seleccionados=seleccionados;
		return getNavigTree();
	}
	public TreeNode getUserNavigTree(Usuario usuario){
	
		this.user=usuario;
		addedNodes=new HashMap<Integer, TreeNode>();
		String BUNDLE_NAME = "bundle.messages";
		//ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,
		//		new Locale("es"//loginUser.getLanguage()
                //                           ));
		
		root = new DefaultTreeNode("Root", null);
		//TreeNode home_node = new DefaultTreeNode(RESOURCE_BUNDLE.getString("home"), root);
		
		/*
		for (OpcionMenu om : childList.getResultList()) {
			if(isContained(om,user.getOpcionesAdicionles()) && !isContained(om,user.getOpcionesExluidas())){
				TreeNode node = loadUserNode(om, root);
			}else if(!isContained(om,user.getOpcionesExluidas())){				
				for(Perfil pf: user.getPerfilesDeUsuario()){
					for(OpcionMenu sm: pf.getOpcionesDeMenu()){
							if(sm.getId()==om.getId()){
								TreeNode node = loadUserNode(om, root);
								break;
							}
						}					
				}
			}
		}*/
		
		//TreeNode logout_node = new DefaultTreeNode(RESOURCE_BUNDLE.getString("logout"), root);
		this.seleccionados=new ArrayList<OpcionMenu>();
		return root;
	}
	
	public TreeNode getNavigTree(){
		
		addedNodes=new HashMap<Integer, TreeNode>();
		String BUNDLE_NAME = "bundle.messages";
		/*ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,
				new Locale(loginUser.getLanguage()
                                           ));*/
		
		root = new DefaultTreeNode("Root", null);
		//TreeNode home_node = new DefaultTreeNode(RESOURCE_BUNDLE.getString("home"), root);
		
		
		for (OpcionMenu om : childList.getResultList()) {
						TreeNode node = loadUserNode(om, root);
			}
		
		//TreeNode logout_node = new DefaultTreeNode(RESOURCE_BUNDLE.getString("logout"), root);
		this.seleccionados=new ArrayList<OpcionMenu>();
		return root;
	}
	
	
	private TreeNode loadUserNode(OpcionMenu om,TreeNode node) {	
		
		TreeNode new_node;
		
	    if(om.getMenuPadre()==null){
	    	new_node = new DefaultTreeNode(om, node);   	
	    }
	    else if(addedNodes.containsKey(om.getMenuPadre().getId())){
	    	new_node = new DefaultTreeNode(om, addedNodes.get(om.getMenuPadre().getId()));

	    }
	    else{
	    	new_node=new DefaultTreeNode(om, loadUserNode(om.getMenuPadre(),node));
	    }
	    
	    new_node.setSelectable(om.getSubMenus().size()==0);
	    new_node.setSelected(seleccionados.contains(om));
	    new_node.setExpanded(true);
	    addedNodes.put(om.getId(), new_node);
		return new_node;
	}
	
	private boolean isContained(OpcionMenu om, Set<OpcionMenu> subMenus){
		for(OpcionMenu sm: subMenus){
			if(sm.getId()==om.getId())
				return true;
		}
		return false;
	}
	
}
