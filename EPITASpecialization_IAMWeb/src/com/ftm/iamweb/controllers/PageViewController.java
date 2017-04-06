/**
 * PageViewController.java
 */
package com.ftm.iamweb.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;

import com.ftm.iamcore.datamodel.User;

/**
 * Cross controller in charge of verifying that the session
 *  is valid and depending on that, redirect to Index page
 * @author Favio
 *
 */
@ManagedBean(name="pageController")
@ViewScoped
public class PageViewController implements Serializable {

	private static final long serialVersionUID = 2621504416333799364L;

	//Default constructor
	public PageViewController() {
	}

	/**
	 * Verify that the session contain a user, otherwise, redirect to the Index page
	 * @param event base class for System Event
	 */
	public void verifyUser(ComponentSystemEvent event) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			User user = (User) externalContext.getSessionMap().get("user");
			if(user == null) {
				//redirects to the index.xhtml
				externalContext.redirect("Index.xhtml");
			}
		} catch (Exception e) {		
		}
	}
}
