/**
 * ValidationIndexTest.java
 */
package com.ftm.iamweb.tests;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test if the message indicating that fields are empty is displayed
 * @author Favio
 *
 */
public class ValidationIndexTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * Setting the configuration
	 * @throws Exception Selenium error
	 */
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testIndex() throws Exception {
		driver.get(baseUrl + "/EPITASpecialization_IAMWeb/faces/Index.xhtml");
		driver.findElement(By.id("j_idt28:j_idt32")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (driver.findElement(By.cssSelector("span.ui-growl-title")).isDisplayed()) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	/**
	 * Closing driver
	 * @throws Exception Selenium error
	 */
	@After
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}