package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void productInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage =  searchResultPage.selectproduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultPage = accountsPage.doSearch("iMac");
		productInfoPage =  searchResultPage.selectproduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	@Test(priority = 3)
	public void productInfoType() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage =  searchResultPage.selectproduct("MacBook Pro");
		Map<String, String> actualPronductInfoMap = productInfoPage.getProductInfo();
		actualPronductInfoMap.forEach((k,v)->System.out.println(k+" : "+v));
		softAssert.assertEquals(actualPronductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actualPronductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualPronductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
}
