package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	//1. declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By Locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPasswordLink = By.xpath("//form[@method='post']//div//a");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//4. Page Actions
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		//return driver.getTitle();
	}
	
	@Step("Getting login page URL")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		//return driver.getCurrentUrl();
	}
	
	@Step("Checking forgot password link is exist of not")
	public boolean isForgotPwdLinkExists() {
		return eleUtil.doIsDisplayed(forgotPasswordLink);
	}
	
	@Step("Checking register link is exist of not")
	public boolean isRegisterLinkExists() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("Do login with correct username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with Username: "+un);
		eleUtil.doSendKeys(email, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("Do login with incorrect username: {0} and incorrect password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Try to login with wrong Username: "+un+" and Password: "+pwd);
		eleUtil.doSendKeys(email, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		
		String errorMsg = eleUtil.doGetText(loginErrorMessage);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("Login is not done...");
			return false;
		}
		return true;
	}
	
	@Step("Nevagiting to registration page")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
