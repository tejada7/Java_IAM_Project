/**
 * JSFUtil.java
 */
package com.ftm.iamweb.utils;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Class that contain util methods to invoke {@code FaceMessages} 
 * in primefaces and display messages to the user
 * @author Favio
 *
 */
public class JSFUtil {

	/**
	 * Invoke {@link addErrorMessage} 
	 * @param ex - exception
	 * @param defaultMsg - String message
	 */
	public static void addErrorMessage(Exception ex, String defaultMsg) {
		String msg = ex.getLocalizedMessage();
		if (msg != null && msg.length() > 0) {
			addErrorMessage(msg);
		} else {
			addErrorMessage(defaultMsg);
		}
	}

	/**
	 * Invokes {@link addErrorMessage} as many times as {@code List<String> messages} elements has  
	 * @param messages - list of messages
	 */
	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

	/**
	 * Invoke {@code Primefaces addMessage} to display an error to the user
	 * @param msg - error message
	 */
	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	/**
	 * Invoke {@code Primefaces addMessage} to display an information to the user
	 * @param msg - title of the message
	 * @param detail - description of the message
	 */
	public static void addInfoMessage(String msg, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	/**
	 * Invoke {@code Primefaces addMessage} to display an error to the user
	 * @param msg - message
	 */
	public static void addSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}
}
