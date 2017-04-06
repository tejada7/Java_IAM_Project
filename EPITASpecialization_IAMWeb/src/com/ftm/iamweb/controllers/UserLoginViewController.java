/**
 * UserLoginViewController.java
 */
package com.ftm.iamweb.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ftm.iamcore.datamodel.User;
import com.ftm.iamcore.services.dao.UserDAO;
import com.ftm.iamweb.services.UserServices;
import com.ftm.iamweb.utils.JSFUtil;

/**
 * Managed bean that map the user data provided between the UI layer and the business layer
 * @author Favio
 */
@ManagedBean(name="userLoginView")
@SessionScoped
public class UserLoginViewController extends User implements Serializable, UserServices{

	private static final long serialVersionUID = -7717751107527363926L;
	
	/**
	 * DAO Injection
	 */
	@Autowired
	private UserDAO userDao;
	
	/**
	 * Process injection for the given target controller, 
	 * based on the current web application context. 
	 */
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * Invokes the method authenticate from user
	 */
	@Override
	public String login() throws Exception {					
		String redirection = "";
		if(userDao.authenticate(this)) {			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this);
			redirection = "/Welcome?faces-redirect=true";
			JSFUtil.addInfoMessage("Login successful", "Welcome " + getDisplayName());
		} else {
			JSFUtil.addErrorMessage("Invalid credentials");
			setDisplayName("");
			setPassword("");		
		}
		return redirection;
	} 
	
	/**
	 * 
	 */
	@Override
	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}