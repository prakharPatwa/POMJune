package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
		//1. declare private driver
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		//2. page constructor
		public SearchResultPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		
		//3. By Locators
		private By productResult = By.xpath("//div[@class='caption']//h4//a");
		
		//4. Page actions
		public int getProductListCount() {
			int resultCount = eleUtil.visibilityOfAllElements(productResult, Constants.DEFAULT_TIME_OUT).size();
			System.out.println("Search product count is "+resultCount);
			return resultCount;
		}
		
		public ProductInfoPage selectproduct(String mainProductName) {
			System.out.println("Main product name is: "+mainProductName);
			List<WebElement> searchList =  eleUtil.visibilityOfAllElements(productResult, Constants.DEFAULT_TIME_OUT);
			for(WebElement e : searchList) {
				String text = e.getText();
				if(text.equals(mainProductName)) {
					e.click();
					break;
				}
			}
			return new ProductInfoPage(driver);
		}
}

















