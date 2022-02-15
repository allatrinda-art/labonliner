package com.onliner;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public static final String CATALOG = "//h2/a[contains(text(), '%s')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open catalog page")
    public void openCatalog(String section) {
        driver.findElement(By.xpath(String.format(CATALOG, section))).click();
    }
}
