/**
 * IdentityViewController.java
 */
package com.ftm.iamweb.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ftm.iamcore.datamodel.Attribute;
import com.ftm.iamcore.datamodel.Identity;
import com.ftm.iamweb.utils.JSFUtil;
import com.ftm.iamweb.utils.PersistAction;

/**
 * Main controller that performs the CRUD operations using Facade pattern to access Identity 
 * {@code DAO} injection with Spring
 * @author Favio
 *
 */
@ManagedBean(name="identityController")
@SessionScoped
public class IdentityViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	//Facade to interact with the DAO
	@Autowired
	private IdentityFacade facade;
	
	//List to map the main dataTable
	private List<Identity> identities;
	
	//Identity to manage the events to edit, view and delete
	private Identity selected; 
	
	//List to manage the result of filtering the dataTable
	private List<Identity> filteredIdentities;
	
	//Using another managed bean to manage the extra attributes
	@ManagedProperty(value = "#{attributeView}")
	private AttributeViewController attributeView;
	
	
	//Getters and setters
	 
	public AttributeViewController getAttributeView() {
		return attributeView;
	}

	public void setAttributeView(AttributeViewController attributeView) {
		this.attributeView = attributeView;
	}

	public IdentityFacade getFacade() {
		return facade;
	}

	public void setFacade(IdentityFacade facade) {
		this.facade = facade;
	}

	public Identity getSelected() {
		return selected;
	}

	public void setSelected(Identity selected) {
		this.selected = selected;
	}

	public Identity prepareCreate() {        
		selected = new Identity();
		return selected;
	}
	
	/**
	 * Process @Autowired injection for the given target controller, 
	 * based on the current web application context. 
	 */
	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);	
	}

	/**
	 * Use facade to get all the Identities from the database
	 * @return list of identities
	 */
	public List<Identity> getIdentities() {
		identities = getFacade().findAll();
		return identities;
	}
	
	/**
	 * Map the filtered identities from the datatable
	 * @return list of filtered identities
	 */
	public List<Identity> getFilteredIdentities() {
		return filteredIdentities;
	}

	public void setFilteredIdentities(List<Identity> filteredIdentities) {
		this.filteredIdentities = filteredIdentities;
	}

	/**
	 * Persist an Identity to the database, this method is invoked from the button Create in the UI 
	 */
	public void create() {						
		persist(PersistAction.CREATE, "Identity created");			
	}
	
	/**
	 * Delete an Identity from the database, this method is invoked from the button Delete in the UI
	 */
	public void delete() {
		persist(PersistAction.DELETE, "Identity deleted");		
	}
	
	/**
	 * Update an Identity to the database, this method is invoked from the button Update in the UI
	 */
	public void update() {
		persist(PersistAction.EDIT, "Identity updated");
	}

	/**
	 * Convert a List of attributes into a Map<String, String> to map the Identity's attributes field
	 * @return a Map of attributes in format <String, String>
	 */
	private Map<String, String> getAttributesMap() {
		List<Attribute> attributesList= attributeView.getAttributes();
		Map<String, String> attributesMap = null;
		if (attributesList != null) {
			attributesMap = new HashMap<>();
			for (Attribute attribute : attributesList) {
				attributesMap.put(attribute.getName(), attribute.getValue());
			}
		}
		return attributesMap;
	}

	/**
	 * Method that uses facade bean to invoke dao methods and persist the new objects in the database
	 * @param action
	 * @param successMessage
	 */
	private void persist(PersistAction action, String successMessage) {
		switch (action) {
		case CREATE:
			selected.setAttributes(getAttributesMap());
			getFacade().create(selected);
			JSFUtil.addSuccessMessage(successMessage);
			attributeView.init();
			break;
		case EDIT:
			if (selected != null) {
				if (getAttributesMap() != null) {
					selected.getAttributes().putAll(getAttributesMap());
					attributeView.init();
				}
				getFacade().edit(selected);
				selected = null;
				JSFUtil.addSuccessMessage(successMessage);
			} else {
				JSFUtil.addErrorMessage("Select one row");
			}
			break;
		case DELETE:
			if (selected != null) {
				getFacade().remove(selected);
				selected = null;
				JSFUtil.addSuccessMessage(successMessage);
			} else {
				JSFUtil.addErrorMessage("Select one row");
			}
			break;
		}
	}
}