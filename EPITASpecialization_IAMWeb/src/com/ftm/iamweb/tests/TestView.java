/**
 * TestView.java
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
 * Test the view display form of an already created Identity
 * It uses the chromedriver.exe located in the root file of the project
 * @author Favio
 */
public class TestView {
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

	/**
	 * Access the system, then selects the last Identity found in the dataTable
	 * and click in view, so a view form should be displayed
	 * @throws Exception Selenium error
	 */
	@Test
	public void testView() throws Exception {
		driver.get(baseUrl + "/EPITASpecialization_IAMWeb/faces/Index.xhtml");
	    driver.findElement(By.id("j_idt28:username")).click();
	    driver.findElement(By.id("j_idt28:username")).clear();
	    driver.findElement(By.id("j_idt28:username")).sendKeys("admin");
	    driver.findElement(By.id("j_idt28:password")).clear();
	    driver.findElement(By.id("j_idt28:password")).sendKeys("Epita");
	    driver.findElement(By.id("j_idt28:j_idt32")).click();
	    driver.findElement(By.xpath("//tbody[@id='IdentityListForm:datalist_data']/tr/td[2]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("IdentityListForm:datalist:viewButton")).click();
	    Thread.sleep(1000);
	    assertTrue(isElementPresent(By.xpath("//table[@id='IdentityViewForm:j_idt59:j_idt61']/tbody/tr/td")));
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

	/**
	 * Test the existence an element in the DOM given an ID
	 * @param by - id
	 * @return true whether the element is found
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