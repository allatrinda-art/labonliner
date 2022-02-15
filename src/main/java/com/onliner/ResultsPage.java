package com.onliner;

import com.onliner.utils.SeleniumUtils;
import com.onliner.utils.WaitElement;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends BasePage {

    public static final String PRODUCER = "//li/label[contains(@class, 'schema-filter')]/span[text()='%s']";
    public static final String PRICE = "//div/div[contains(@class, 'control_input')][2]//input[contains(@class, 'input_price')]";
    public static final String RESOLUTION = "//li/label[contains(@class, 'schema-filter')]/span[text()='%s']";
    public static final String DIAGONAL_FROM = "//select[contains(@class, 'schema-filter-control')]/ancestor::div/div[contains(@class, 'schema-filter-control')][1]";
    public static final String DIAGONAL_MIN = "//div[contains(@class, 'schema-filter')][1]/select[contains(@class, 'filter-control')]/option[contains(text(),'%s')]";
    public static final String DIAGONAL_TO = "//select[contains(@class, 'schema-filter-control')]/ancestor::div/div[contains(@class, 'schema-filter-control')][2]";
    public static final String DIAGONAL_MAX = "//div[contains(@class, 'schema-filter')][2]/select[contains(@class, 'filter-control')]/option[contains(text(),'%s')]";
    public static final String ITEM_TYPE_PAGE_NAME = "//h1";
    public static final String ITEM_GROUP = "//div[@class = 'schema-product__group']";
    public static final String ITEM_NAME = "//div[@class='schema-product__title']";
    public static final String ITEM_PRICE = "//span[contains(@data-bind, 'minPrice')]";
    public static final String ITEM_RESOLUTION_DIAGONAL = "//span[contains(@data-bind, 'description')]";

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filter search results")
    public void searchItemType(String producerName, String price, String screenResolution, String diagonalMin,
                               String diagonalMax) {
        WebElement producer = driver.findElement(By.xpath(String.format(PRODUCER, producerName)));
        WaitElement.waitForElementClickable(driver, producer);
        SeleniumUtils.clickUsingJs(driver, producer);
        driver.findElement(By.xpath(String.format(PRICE))).sendKeys(price);
        WebElement resolution = driver.findElement(By.xpath(String.format(RESOLUTION, screenResolution)));
        SeleniumUtils.clickUsingJs(driver, resolution);
        WaitElement.waitForElementClickable(driver, driver.findElement(By.xpath(DIAGONAL_FROM)));
        driver.findElement(By.xpath(DIAGONAL_FROM)).click();
        driver.findElement(By.xpath(String.format(DIAGONAL_MIN, diagonalMin))).click();
        WaitElement.waitForElementClickable(driver, driver.findElement(By.xpath(DIAGONAL_TO)));
        driver.findElement(By.xpath(DIAGONAL_TO)).click();
        driver.findElement(By.xpath(String.format(DIAGONAL_MAX, diagonalMax))).click();
    }

    @Step("Get item type page")
    public String getItemTypePageName() {
        String itemTypePageName = driver.findElement(By.xpath(ITEM_TYPE_PAGE_NAME)).getText();
        return itemTypePageName;
    }

    @Step("Check results")
    public void checkResults(String producerName, String price, String screenResolution, String diagonalMin, String diagonalMax) {
        WaitElement.refreshed(driver, driver.findElement(By.xpath(ITEM_GROUP)));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkProducer(producerName));
        softAssert.assertTrue(checkPrice(Integer.parseInt(price)));
        softAssert.assertTrue(checkScreenResolution(screenResolution));
        softAssert.assertTrue(checkDiagonal(diagonalMin, diagonalMax));
        softAssert.assertAll();
    }

    private boolean checkProducer(String producerName) {
        List<WebElement> producerNameList = driver.findElements(By.xpath(ITEM_NAME));
        return producerNameList.stream().allMatch(element -> element.getText().contains(producerName));
    }

    private boolean checkPrice(int price) {
        List<WebElement> priceListString = driver.findElements(By.xpath(ITEM_PRICE));
        List<Integer> priceListInt = new ArrayList<>();
        for (WebElement element : priceListString) {
            int priceElement = Integer.parseInt(StringUtils.substringBefore(element.getText(), ","));
            priceListInt.add(priceElement);
        }
        return priceListInt.stream().allMatch(element -> element <= price);
    }

    private boolean checkScreenResolution(String screenResolution) {
        List<WebElement> screenResolutionList = driver.findElements(By.xpath(ITEM_RESOLUTION_DIAGONAL));
        return screenResolutionList.stream().allMatch(element -> element.getText().contains(screenResolution));
    }

    private boolean checkDiagonal(String diagonalMin, String diagonalMax) {
        double diagonalMinDouble = Double.parseDouble(diagonalMin);
        double diagonalMaxDouble = Double.parseDouble(diagonalMax);
        List<WebElement> diagonalStringList = driver.findElements(By.xpath(ITEM_RESOLUTION_DIAGONAL));
        List<Double> diagonalIntList = new ArrayList<>();
        for (WebElement element : diagonalStringList) {
            double diagonalElement = Double.parseDouble(StringUtils.substringBefore(element.getText(), "\""));
            diagonalIntList.add(diagonalElement);
        }
        return diagonalIntList.stream().allMatch(element -> element >= diagonalMinDouble && element <= diagonalMaxDouble);
    }
}
