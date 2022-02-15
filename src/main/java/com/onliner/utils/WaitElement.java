package com.onliner.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitElement {

    public static final long TIMEOUT = Long.parseLong(PropertyLoader.returnConfigValue("timeout"));

    public static void waitForElementClickable(WebDriver driver, WebElement webElement) {
        WebDriverWait explicitWaitByElement = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        explicitWaitByElement.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void refreshed(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(webElement)));
    }
}
