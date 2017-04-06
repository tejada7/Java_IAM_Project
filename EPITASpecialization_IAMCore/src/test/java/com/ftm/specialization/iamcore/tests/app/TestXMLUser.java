/**
 * TestXMLUser.java
 */
package com.ftm.specialization.iamcore.tests.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.ftm.iamcore.datamodel.User;
import com.ftm.iamcore.services.dao.UserDAO;

/**
 * Test the methods defined in {@code XMLUserDAO}
 * test loading the credentials xml file
 * test method authenticate
 * @author Favio
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TestXMLUser {

	@Autowired
	UserDAO userDao;
	
	/**
	 * Test whether the configuration file located in the 
	 * classpath of the project is being loaded
	 */
	@Before 
	public void testLoadXMLFile() {		
		Resource resource = new ClassPathResource("users.xml");
		Assert.isTrue(resource.exists());		
	}
	
	/**
	 * The file users.xml must be in the classpath of the project. However, when running
	 * a multi-module project, the file must be drop in the classpath of the server.
	 * @throws Exception if error during loading the file
	 */
	@Test
	public void testUserDao() throws Exception {
		Assert.isTrue(userDao.authenticate(new User("admin", "Epita")));
	}
}
