package com.onliner;

import com.onliner.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogPage extends BasePage {

    public static final String CATEGORY = "//span[contains(text(), '%s')]/ancestor::li";
    public static final String SUBCATEGORY = "//div[contains(text(), '%s')]";
    public static final String ITEMTYPE = "//a[1]//span[contains(text(), '%s')]";
    public static final String SECTION_NAME = "//*[@class='catalog-navigation__title']";

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open category")
    public void findCategory(String categoryName, String subcategoryName, String itemTypeName) {
        driver.findElement(By.xpath(String.format(CATEGORY, categoryName)));
        driver.findElement(By.xpath(String.format(SUBCATEGORY, subcategoryName)));
        WebElement element = driver.findElement(By.xpath(String.format(ITEMTYPE, itemTypeName)));
        SeleniumUtils.clickUsingJs(driver, element);
    }

    @Step("Get section page")
    public String getSectionPageName() {
        String sectionPageName = driver.findElement(By.xpath(SECTION_NAME)).getText();
        return sectionPageName;
    }
}
