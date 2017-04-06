/**
 * UserServices.java
 */
package com.ftm.iamweb.services;

/**
 * Interface that contains behavior corresponding user actions to access the program
 * @author Favio
 *
 */
public interface UserServices {

	/**
	 * Redirect to the welcome page
	 */
	public String login() throws Exception;
	
	/**
	 * Invalidate current session
	 */
	public void logout();
}
