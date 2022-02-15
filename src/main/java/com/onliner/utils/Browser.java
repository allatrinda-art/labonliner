package com.onliner.utils;

import com.onliner.CatalogPage;
import com.onliner.HomePage;
import com.onliner.ResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Browser {

    public WebDriver driver;
    public HomePage homePage;
    public CatalogPage catalogPage;
    public ResultsPage resultsPage;

    @BeforeMethod
    public void setUpBrowser() {
        long baseTimeout = Long.parseLong(PropertyLoader.returnConfigValue("baseTimeout"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(baseTimeout));
        driver.get(PropertyLoader.returnConfigValue("url"));

        homePage = new HomePage(driver);
        catalogPage = new CatalogPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Closing browsing", enabled = true)
    public void closeBrowser() {
        if (driver != null)
            driver.quit();
    }

}
