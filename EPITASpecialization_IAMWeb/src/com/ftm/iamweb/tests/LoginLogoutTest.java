/**
 * LoginLogoutTest.java
 */
package com.ftm.iamweb.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test the page redirection when trying to login with the correct credentials.
 * Likewise, test the button logout, redirecting to the Index.xhtml
 * It uses the chromedriver.exe located in the root file of the project
 * @author Favio
 *
 */
public class LoginLogoutTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * Setting the configuration
	 * @throws Exception driver not found
	 */
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Test login and logout
	 * @throws Exception Selenium error
	 */
	@Test
	public void testLoginLogout() throws Exception {
		driver.get(baseUrl + "/EPITASpecialization_IAMWeb/faces/Index.xhtml");
		driver.findElement(By.id("j_idt28:username")).click();
		driver.findElement(By.id("j_idt28:username")).clear();
		driver.findElement(By.id("j_idt28:username")).sendKeys("admin");
		driver.findElement(By.id("j_idt28:password")).clear();
		driver.findElement(By.id("j_idt28:password")).sendKeys("Epita");
		driver.findElement(By.id("j_idt28:j_idt32")).click();
		try {
			assertTrue(isElementPresent(By.id("j_idt10")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("j_idt10")).click();
		try {
			assertTrue(isElementPresent(By.id("j_idt28:j_idt32")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	/**
	 * Closing driver
	 * @throws Exception Selenium error
	 */
	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	/**
	 * Method that find element in the DOM given an ID
	 * @param by - id
	 * @return true if element found
	 * @return false otherwise
	 */
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}