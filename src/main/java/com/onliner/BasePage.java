package com.onliner;

import com.onliner.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public static final long BASETIMEOUT = Long.parseLong(PropertyLoader.returnConfigValue("baseTimeout"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(BASETIMEOUT));
    }
}
