/**
 * User.java
 */

package com.ftm.iamcore.datamodel;

/**
 * The {@code User} is used to differentiate those <i>identities</i> 
 * which have privileges to access the application  
 * @author Favio
 *
 */
public class User extends Identity {	
	
	private String password;

	public User() {	
		super();
	}
	
	public User(String name, String password) {	
		super(name);
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [password=" + password + ", displayName=" + displayName + ", uid=" + uid + ", email=" + email
				+ ", attributes=" + attributes + "]";
	}
}