/**
 * IdentityFacade.java
 */
package com.ftm.iamweb.controllers;

import java.io.Serializable;

import com.ftm.iamcore.datamodel.Identity;
import com.ftm.iamweb.services.AbstractFacade;

/**
 * Facade class to create link the Data layer with UI layer
 * @author Favio
 *
 */
public class IdentityFacade extends AbstractFacade<Identity> implements Serializable{
	
	private static final long serialVersionUID = 1259979490291205890L;

	public IdentityFacade() {
		this.entityClass = Identity.class;
	}

}