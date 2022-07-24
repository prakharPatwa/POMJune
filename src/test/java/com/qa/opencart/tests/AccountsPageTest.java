package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	@BeforeClass
	public void AccountPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle = accountsPage.getAccountPageTitle();
		System.out.println("Account page title:"+actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageHeaderDisplayTest() {
		Assert.assertTrue(accountsPage.accountPageHeader());
	}
	
	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test(priority = 4)
	public void accountPageSectiontest() {
		List<String> actualAccountSectionList =  accountsPage.getAccountList();
		Assert.assertEquals(actualAccountSectionList, Constants.getExpectedAccountSectionList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getProductListCount()>0);
		
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iMac","iMac"}
		};
	}
	
	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productType, String productName) {
		searchResultPage = accountsPage.doSearch(productType);
		productInfoPage =  searchResultPage.selectproduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	
}
