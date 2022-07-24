package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	//1. declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. page constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By Locators
	private By header = By.xpath("//div[@id='logo']//a//img");
	private By accountSections = By.xpath("//div[@id='content']//h2");
	private By searchField = By.xpath("//div[@id='search']//input");
	private By searchButton = By.xpath("//div[@id='search']//span//button");
	private By logoutLink = By.linkText("Logout");
	
	//4. Page Action
	public String getAccountPageTitle() {
		return eleUtil.waitForTitlePresent(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean accountPageHeader() {
		return eleUtil.doIsDisplayed(header);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	public List<String> getAccountList() {
		List<WebElement> accSecList = eleUtil.visibilityOfAllElements(accountSections, Constants.DEFAULT_TIME_OUT);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e:accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
	
	public boolean isSearchexist() {
		return eleUtil.doIsDisplayed(searchField);
	}
	
	public SearchResultPage doSearch(String productName) {
		System.out.println("searching the product "+productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
		
}













