/**
 * TestHibernate.java
 */
package com.ftm.specialization.iamcore.tests.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.ftm.iamcore.datamodel.Identity;
import com.ftm.iamcore.services.dao.DAO;

/**
 * TestCase of {@code HibernateDAO} to:
 * <ul>
 * <li>Persist a generic Object to a database</li>
 * <li>Read the table 's rows and store it in a generic list</li>
 * <li>Delete a row or rows in a table given a criteria</li>
 * <li>Search a row or rows in a database table given a criteria</li>
 * </ul>
 * @author Favio
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TestHibernate {
	
	@Autowired
	private DAO<Identity> dao;
		
	/**
	 * Test whether {@code DAO<Identity>} persist a new Identity in the database
	 * Create a new {@code Identity}, persist it, and check in the database if there is only one occurrence
	 * @throws ParseException possible format error when setting the date
	 */
	@Test
	public void testCreate() throws ParseException {
		//Creating extra attributes in a map
		Map<String, String> attributes = new HashMap<>();
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/1992");
		attributes.put("address", "C. Reseguin #2250");
		attributes.put("gender", "masculin");
		attributes.put("phoneNumber", "(+591)72526696");
		
		//Creating new object Identity
		Identity identity = new Identity("Favio", "Tejada", "faviotejada7@gmail.com", birthDate, attributes);
		
		//Persisting Identity just created using dao object
		dao.create(identity);
		
		//Listing the identities persisted in the database
		List<Identity> listIdentities = dao.readAll(Identity.class);
		
		//Asserting whether the object was successfully inserted in the database
		Assert.isTrue(listIdentities.size() == 1);
	}
	
	/**
	 * Test whether {@code DAO<Identity>} search method find elements in the database
	 * Create a new Identity object and persist in the database, and search that element 
	 * using the method search
	 * @throws ParseException possible format error when setting the date
	 */
	@Test
	public void testSearch() throws ParseException {
		//Creating a new Identity Object
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1991");
		Identity identityPersist = new Identity("Mario", "mario@hotmail.com", birthDate);
		
		//Persisting the new Identity in the database
		dao.create(identityPersist);		
		
		//Creating a new Identity with no similar field to be searched
		birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("02/05/1991");
		Identity identitySearch = new Identity("test", "test@hotmail.com", birthDate);
		
		//Searching the second Identity just inserted - should return no occurrences
		Assert.isTrue(dao.search(identitySearch).size() == 0);	
		
		//Changing one field of identitySearch to match with one field of identityPersist
		identitySearch.setDisplayName("Mario");
		
		//Searching again but with the right name, this time it should return 1
		Assert.isTrue(dao.search(identitySearch).size() == 1);
	}
	
	/**
	 * Test whether {@code DAO<Identity>} delete one element in the database
	 * Create a new Identity object and persist in the database, and then delete that element  
	 */
	@Test
	public void testDelete() {
		//Creating new Identity Object
		Map<String, String> attributes = new HashMap<>();
		attributes.put("attribute1", "value1");
		attributes.put("attribute2", "value2");
		Identity identity = new Identity("testDelete", "test@delete.fr", new Date(), attributes);
		
		//Persisting the new Identity in the database
		dao.create(identity);		
		
		//Searching the Identity just inserted
		Identity found = (Identity) dao.searchUnique(new Identity("testDelete", "", new Date()));
		
		//Deleting the Identity found
		dao.delete(found);
	}
	
	/**
	 * Test whether {@code DAO<Identity>} update one element in the database
	 * Create a new Identity object and persist in the database, and then delete that element 
	 */
	@Test
	public void testUpdate() {
		//Creating new Identity Object
		Map<String, String> attributes = new HashMap<>();
		attributes.put("Weight", "80 Kg");
		attributes.put("Height", "1.78 cm");
		Identity identity = new Identity("testUpdate", "test@delete.fr", new Date(), attributes);
		
		//Persisting new Identity in the database
		dao.create(identity);
		
		//Searching the element just inserted in the database
		Identity tobeUpdatet = new Identity("testUpdate", "", new Date());
		Identity searchResult = dao.searchUnique(tobeUpdatet);
		
		//Changing the name
		searchResult.setDisplayName("Modified name");
		//Changing one attribute
		Map<String, String> attributesToBeUpdated = searchResult.getAttributes();
		attributesToBeUpdated.put("Weight", "90 Kg");
		attributesToBeUpdated.put("Height", "1.8 cm");
		searchResult.setAttributes(attributesToBeUpdated);
		
		//Updating the Identity
		dao.update(searchResult);
		
		//Verifying that the fields have changed		
		Assert.isTrue(dao.searchUnique(new Identity("Modified name"))
				.getAttributes().get("Weight").equals("90 Kg"));
	}
	
	/**
	 * Test whether {@code DAO<Identity>} delete one element in the database
	 * This method is invoked as soon as a test is performed to clean the database table
	 */
	@After
	public void readAllandDelete() {
		List<Identity> listIdentities = dao.readAll(Identity.class);
		for (Identity identity : listIdentities) {
			dao.delete(identity);
		}
	}
}