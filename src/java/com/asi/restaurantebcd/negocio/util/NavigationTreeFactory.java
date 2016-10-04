package com.asi.restaurantebcd.negocio.util;

import com.asi.restaurantbcd.modelo.OpcionMenu;
import com.asi.restaurantebcd.controller.seguridad.MenuChildList;
import com.asi.restaurantebcd.controller.seguridad.MenuParentList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;



@SuppressWarnings("unused")
@ConversationScoped
public class NavigationTreeFactory implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private MenuParentList parentList;
	
	@Inject
	private MenuChildList childList;
	
	
	private TreeNode root;
	
	private List<OpcionMenu> menusUsuario = new ArrayList<OpcionMenu>();
	
	public NavigationTreeFactory(){
		
	}

	public TreeNode getRoot() {
		return root;
	}
	

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	@Produces @Named @ConversationScoped
	public TreeNode getNavigationTree() {

		root = new DefaultTreeNode("Root", null);
		for (OpcionMenu om : parentList.getResultList()) {
			TreeNode node = loadParentNode(om, root);
		}
		return this.root;
	}

	private TreeNode loadParentNode(OpcionMenu om, TreeNode node) {
		TreeNode new_node = new DefaultTreeNode(om, node);
		new_node.setSelectable(false);
		for (OpcionMenu sm : om.getSubMenus()) {
			if (sm.getSubMenus().size() == 0) {
				TreeNode sub_node = new DefaultTreeNode(sm, new_node);
				sub_node.setSelectable(true);
				
			} else {
				TreeNode sub_node = (loadParentNode(sm, new_node));
				sub_node.setSelectable(false);
			}
		}
		return node;
	}
	
	
}
