package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
		//1. declare private driver
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		//2. page constructor
		public RegisterPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		
		//3. By Locators
		By firstName = By.id("input-firstname");
		By lastName = By.id("input-lastname");
		By email = By.id("input-email");
		By telephone = By.id("input-telephone");
		
		By password = By.id("input-password");
		By confirmPassword = By.id("input-confirm");
		
		By subscribeYes = By.xpath("//fieldset//div//label//input[@name='newsletter' and @value='1']");
		By subscribeNo = By.xpath("//fieldset//div//label//input[@name='newsletter' and @value='0']");
		
		By privacyPolicy = By.xpath("//input[@name='agree']");
		By registerConfirm = By.xpath("//input[@value='Continue']");
		
		By successMessage = By.xpath("//div[@id='content']//h1");
		By logOut = By.linkText("Logout");
		By registerLink = By.linkText("Register");
		
		//4. Page Actions
		
		public boolean accountRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe) throws InterruptedException {
			eleUtil.doSendKeys(this.firstName, firstName);
			eleUtil.doSendKeys(this.lastName, lastName);
			eleUtil.doSendKeys(this.email, email);
			eleUtil.doSendKeys(this.telephone, telephone);
			eleUtil.doSendKeys(this.password, password);
			eleUtil.doSendKeys(this.confirmPassword, password);;
			if(subscribe.equals("yes")) {
				eleUtil.doClick(subscribeYes);
			}else {
				eleUtil.doClick(subscribeNo);
			}
			eleUtil.doClick(privacyPolicy);
			eleUtil.doClick(registerConfirm);
			String message = eleUtil.waitForElementToBeVisible(successMessage, 10).getText();
			if(message.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
				Thread.sleep(6000);
				eleUtil.doClick(logOut);
				Thread.sleep(6000);
				eleUtil.doClick(registerLink);
				return true;
			}
			return false;
		}
		
}
