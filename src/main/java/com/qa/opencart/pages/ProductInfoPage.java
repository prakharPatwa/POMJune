package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	//1. declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;
	
	//2. page constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By Locators
	private By productHeader = By.xpath("//div[@id='content']//div[@class='col-sm-4']//h1");
	private By productimages = By.xpath("//ul[@class='thumbnails']//li");
	private By productMetaData = By.xpath("//div[@id='content']//div[@class='col-sm-4']//ul[1]//li");
	private By productPriceData = By.xpath("//div[@id='content']//div[@class='col-sm-4']//ul[2]//li");
	private By qty = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	
	//4. Page actions
	public String getProductHeader() {
		String productHeaderText =  eleUtil.doGetText(productHeader);
		System.out.println("Product header text is: "+productHeaderText);
		return productHeaderText;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.visibilityOfAllElements(productimages, Constants.DEFAULT_TIME_OUT).size();
		System.out.println("Number of images: "+imagesCount);
		return imagesCount;
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPricingData();
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e: metaDataList) {
			String text = e.getText();
			String[] meta = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPricingData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exPrice", exPrice);
	}
}










