package com.onliner;

import com.onliner.utils.Browser;
import com.onliner.utils.PropertyLoader;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FindItemsTest extends Browser {

    @Test
    @Parameters({"producerName", "price", "screenResolution", "diagonalMin", "diagonalMax"})
    public void findItems(String producerName, String price, String screenResolution, String diagonalMin, String diagonalMax) {
        homePage.openCatalog(PropertyLoader.returnConfigValue("section"));
        Assert.assertTrue(catalogPage.getSectionPageName().startsWith(PropertyLoader.returnConfigValue("section")),
                "Wrong section page name");
        catalogPage.findCategory(PropertyLoader.returnConfigValue("category"),
                PropertyLoader.returnConfigValue("subcategory"),
                PropertyLoader.returnConfigValue("itemtype"));
        Assert.assertEquals(resultsPage.getItemTypePageName(), PropertyLoader.returnConfigValue("itemtype"),
                "Wrong item type page");
        resultsPage.searchItemType(producerName, price, screenResolution, diagonalMin, diagonalMax);
        resultsPage.checkResults(producerName, price, screenResolution, diagonalMin, diagonalMax);
    }
}
