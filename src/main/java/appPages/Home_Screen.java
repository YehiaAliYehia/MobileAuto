package appPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class Home_Screen extends PageBase{
    SoftAssert Assert1 = new SoftAssert();

    private MobileBy homeScreenFirstTap = (MobileBy) MobileBy.AccessibilityId("اخترنا لك");
    private By searchBar = By.id("com.goldenscent.c3po:id/search_src_text");

    public  void assertHomePageIsOpened(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,homeScreenFirstTap);
        Assert1.assertTrue(driver.findElement(homeScreenFirstTap).isEnabled() == true);
    }

    public  void clickOnSearchBar(MobileDriver<MobileElement> driver) throws InterruptedException {
        waitUntil(driver,searchBar);
        driver.findElement(searchBar).click();


    }
}
