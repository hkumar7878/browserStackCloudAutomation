package com.assignment.TestCases;

import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.appPages.Page_Home;
import com.assignment.basesetup.BaseSetUp_BrowserStack;

public class TS_03_Search_Functionality extends BaseSetUp_BrowserStack{
	Page_Home homePgObj;
//	private String itemTxt = prop.getProperty("Item_Name");
	
	/* 
	 * Method Name - beforeMethod
	 * Test Objective: To initialize configuration file and setting up browser from base setup class before every test method
	 */
	
	@BeforeMethod
	public void beforeMethod() {
		//initializeTestBaseSetup();
	}
	
	/*
	 * Test Case Name - verifySearchItem 
	 * Test Objective: To verify whether system is able to search an existing item
	 */
	
	@DataProvider(name="browserStackTestData",parallel=true)
	public Object [][] getData()
	{
		Object [][] testdata=new Object[][] {
			{Platform.MAC,"chrome","62.0"},
			//{Platform.WINDOWS,"firefox","57"}
		};
		
		return testdata;
	}
	
	@Test(dataProvider = "browserStackTestData")
	public void verifySearchItem(Platform platform,String browserName,String browserVersion) throws InterruptedException
	{
		initializeTestBaseSetup(platform,browserName,browserVersion);
		homePgObj = new Page_Home(driver);
		String actualCurrURL= driver.getCurrentUrl();
		String expCurrURL="https://www.thewarehouse.co.nz/";
		Assert.assertEquals(actualCurrURL, expCurrURL,"Both url are not matching");
		homePgObj.enterSearchItem("Garage Women's PU Leather Jacket");
		homePgObj.clickSearchBtn();
		String itemTxt=homePgObj.getItemText("Searchable Item");
		Assert.assertEquals(itemTxt, "Garage Women's PU Leather Jacket");
	}
	
	/*
	 * Test Case Name - verifySearch_NonExistentItem 
	 * Test Objective: To verify whether system should not able to search non-existing item
	 */
	
	//@Test
	public void verifySearch_NonExistentItem() throws InterruptedException
	{
		homePgObj = new Page_Home(driver);
		homePgObj.enterSearchItem("test123");
		homePgObj.clickSearchBtn();
		String itemTxt=homePgObj.getItemText("Non Searchable Item");
		Assert.assertTrue(itemTxt.contains("We're sorry, no products were found for your search:"));
	}
	/* 
	 * Method Name - afterMethod
	 * Test Objective: To quite the browser after every test method
	 */

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
