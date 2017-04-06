/**
 * TestSpring.java
 */
package com.ftm.specialization.iamcore.tests.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.ftm.iamcore.datamodel.Identity;
import com.ftm.iamcore.services.dao.DAO;
import com.ftm.iamcore.services.dao.impl.XMLUserDAO;

/**
 * Simple test to check whether spring autowiring returns a non-null object
 * @author Favio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TestSpring {	
	
	@Autowired
	private DAO<Identity> daoGeneric;
	
	@Autowired
	private XMLUserDAO daoUser;
	
	/**
	 * Check that daos not be null
	 */
	@Test
	public void springSetup(){
		Assert.notNull(daoGeneric);
		Assert.notNull(daoUser);
	}
}
