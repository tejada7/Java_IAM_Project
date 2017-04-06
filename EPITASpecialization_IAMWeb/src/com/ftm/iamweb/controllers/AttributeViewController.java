/**
 * AttributeView.java
 */
package com.ftm.iamweb.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ftm.iamcore.datamodel.Attribute;
import com.ftm.iamweb.utils.JSFUtil;

/**
 * Managed Bean that map the extra attributes data provided between the 
 * <i>UI layer</i> and the <i>business layer</i>
 * @author Favio
 *
 */
@ManagedBean(name="attributeView")
@SessionScoped
public class AttributeViewController implements Serializable{

	/**
	 * Default constructor
	 */
	public AttributeViewController() {
	}

	private static final long serialVersionUID = 1693110975817349984L;

	//logger
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//attribute created
	private Attribute attribute;
	
	//list of attributes
	private List<Attribute> attributes;		
	
	/**
	 * Method invoked just after its instantiation
	 * initializes its fields
	 */
	@PostConstruct
    public void init() {
        attribute = new Attribute();
        attributes = new ArrayList<>();      
    }
     
	/**
	 * Add new attribute to the dataTable in the Create Form
	 * Control creation of duplicated attributes
	 */
    public void createNew() {
        if(!attributes.contains(attribute)) {
            JSFUtil.addInfoMessage("Info","Adding new attribute");
        	attributes.add(attribute);
        	attribute = new Attribute();  
        	LOGGER.info("Adding a new attribute...");
        }         
       reinit();
    }
     
    /**
     * creates a new attribute
     */
    public void reinit() {
    	attribute = new Attribute();
    }       
 
    public Attribute getAttribute() {
        return attribute;
    }
 
    public List<Attribute> getAttributes() {
        return attributes;
    }

	@Override
	public String toString() {
		return "AttributeView [attribute=" + attribute + ", attributes=" + attributes + "]";
	}
}
