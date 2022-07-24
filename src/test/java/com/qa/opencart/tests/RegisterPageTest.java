package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.goToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "gojays"+randomGenerator.nextInt(1000)+"@mail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationtest(String firstName, String lastName, String telephone, String password, String subscribe) throws InterruptedException {
		Assert.
			assertTrue(registerPage.accountRegistration
					(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}
