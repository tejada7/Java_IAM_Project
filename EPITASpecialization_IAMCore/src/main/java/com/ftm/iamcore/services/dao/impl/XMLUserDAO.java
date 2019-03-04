/**
 * XMLUserDAO.java
 */
package com.ftm.iamcore.services.dao.impl;

import java.io.Serializable;
/**
 * XMLUserDAO.java
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ftm.iamcore.datamodel.User;
import com.ftm.iamcore.services.dao.UserDAO;
import com.ftm.iamcore.utils.UtilSecurity;

/**
 * The {@code XMLUserDAO} class implements the User authenticate method for {@link User}.
 * 
 * @author Favio
 *
 */
@Service
public class XMLUserDAO implements UserDAO, Serializable{			
		
	private static final long serialVersionUID = -5315509877225904232L;

	//logger
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	// Root of the XML.
	private Document document;
	
	/**
	 * When invoked, it is assumed that the file 'users.xml' 
	 * <b>must have been created</b> and <b>inserted</b> a user with
	 * his password.
	 */
	public XMLUserDAO() {
		try {
			// Parse the XML into DOM standard.
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();								
			LOGGER.info("Reading credentials...");
			Resource resource =  new ClassPathResource("users.xml");						
			document = db.parse(resource.getInputStream());
		} catch (Exception e) {					
			LOGGER.log(Level.SEVERE, "An error reading the credentials ocurred ", e);			
		}
	}
	
	/**
	 * Traverses over the XML file and asks if both <i>userName</i> and <i>password</i> match.
	 * {@inheritDoc}
	 */
	@Override
	public boolean authenticate(User user) {	
		LOGGER.info("Authenticating user...");
		return ("VNirfpDRfTo=".equals(UtilSecurity.encrypt(user.getDisplayName()))
				&& "tVJciMxKmwN1vqMQk7kmqw==".equals(UtilSecurity.encrypt(user.getPassword())));
	}

	private boolean bypassXml(User user) {
		NodeList identitiesList = document.getElementsByTagName("user");
		int length = identitiesList.getLength();
		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);
			NodeList properties = identity.getElementsByTagName("property");
			int propertiesLength = properties.getLength();
			int control=0;
			for (int j = 0; j < propertiesLength; j++) {
				Element property = (Element) properties.item(j);
				if (property.getTextContent().trim().equals(UtilSecurity.encrypt(user.getDisplayName()))
					||	property.getTextContent().trim().equals(UtilSecurity.encrypt(user.getPassword()))) {
					control++;
				}
				if (control == 2) {
					LOGGER.info("Credentials correct");
					return true;
				}
			}
		}
		LOGGER.info("Credentials incorrect");
		return false;
	}
}