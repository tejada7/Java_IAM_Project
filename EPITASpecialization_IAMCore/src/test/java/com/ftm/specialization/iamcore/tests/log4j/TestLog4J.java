/**
 * TestLog4j.java
 */
package com.ftm.specialization.iamcore.tests.log4j;

/**
 * Test loading the log4j configuration file, creating a log file and appending messages to it
 */
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class TestLog4J {

	private final static Logger LOGGER = Logger.getLogger("example logger");
	
	/**
	 * Simple use of addAppender, and creating of a log file
	 */
	@Test
	public void testConfig() {
		try {
			//Searching the configuration file in the classpath
            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
            BasicConfigurator.configure();
            LOGGER.addAppender((Appender) new FileAppender(new PatternLayout(),"test.log", true));
            LOGGER.getAppender("test.log");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TestLog4J.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	}	
}