/**
 * TestCreateForm.java
 */
package com.ftm.iamweb.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test the creation of a new Identity in the form Create.xhtml.
 * It uses the chromedriver.exe located in the root file of the project
 * @author Favio
 *
 */
public class TestCreateForm {
	private WebDriver driver;
	private String baseUrl;

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
	 * Test creation of Identity
	 */
	@Test
	public void testCreateForm() {
		try {
			driver.get(baseUrl + "/EPITASpecialization_IAMWeb/faces/Index.xhtml");
			driver.findElement(By.id("j_idt28:username")).click();
			driver.findElement(By.id("j_idt28:username")).clear();
			driver.findElement(By.id("j_idt28:username")).sendKeys("admin");
			driver.findElement(By.id("j_idt28:password")).clear();
			driver.findElement(By.id("j_idt28:password")).sendKeys("Epita");
			driver.findElement(By.id("j_idt28:j_idt32")).click();
			driver.findElement(By.id("IdentityListForm:datalist:createButton")).click();			
			Thread.sleep(500);
			driver.findElement(By.id("IdentityCreateForm:name")).sendKeys("test");
			driver.findElement(By.id("IdentityCreateForm:email")).sendKeys("test@test.com");
			driver.findElement(By.id("IdentityCreateForm:j_idt57")).click();
			driver.findElement(By.xpath("//tbody[@id='IdentityListForm:datalist_data']/tr[2]/td[2]")).click();
			driver.findElement(By.id("IdentityListForm:datalist:viewButton")).click();
			driver.findElement(By.xpath("//div[@id='IdentityViewForm:j_idt61']/h3[2]")).click();			
		}catch(Exception e) {
			
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
	}
}
