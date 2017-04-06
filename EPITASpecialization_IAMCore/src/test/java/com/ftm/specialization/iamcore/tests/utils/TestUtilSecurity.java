/**
 * TestUtilSecurity.java
 */
package com.ftm.specialization.iamcore.tests.utils;

import org.junit.Test;

import com.ftm.iamcore.utils.UtilSecurity;

/**
 * Test the usage of UtilSecurity class
 * @author Favio
 *
 */
public class TestUtilSecurity {

	@Test
	public void testEncrypt() throws Exception {
		String user = "admin";
		String encryptedUser = UtilSecurity.encrypt(user);
		
		System.out.println("Encrypted user: " + encryptedUser);
		
		System.out.println("Decrypted user: " + UtilSecurity.decrypt(encryptedUser));
		
		String password = "Epita";
		String encryptedPassword = UtilSecurity.encrypt(password);
		
		System.out.println("Encrypted password: " + encryptedPassword);
		
		System.out.println("Decrypted password: " + UtilSecurity.decrypt(encryptedPassword));
	}
}
