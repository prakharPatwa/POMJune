package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] loginWrongTestData(){
		return new Object[][] {
			{"test1#2@gmai.com","goyatmaloo"},
			{"prakharqa7@gmail.com","goyatmaloo"},
			{"test1#2@gmai.com",""},
			{"test#$%@mcom.com","test@1234567$#$"},
			{"",""},
		};
	}
	
	@Test(priority = 1, dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String un, String pwd) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pwd));
	}
	
	
}
