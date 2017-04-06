/**
 * IAMDAOException.java
 */
package com.ftm.iamcore.exceptions;

import org.apache.log4j.Logger;

/**
 * Custom exception that register the message of the log
 * @author Favio
 *
 */
public class IAMDAOException extends Exception{

	private static final long serialVersionUID = 5857082876190444879L;
	
	//logger
	private static final Logger  LOGGER = Logger.getLogger(IAMDAOException.class);

	public IAMDAOException(Throwable cause) {
			LOGGER.error("Error generated " + cause.toString());		
	}
}