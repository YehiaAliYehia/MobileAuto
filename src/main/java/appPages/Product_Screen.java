package appPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class Product_Screen extends PageBase{

    private MobileBy Add_To_Cart = (MobileBy) MobileBy.AccessibilityId("Dummy2");
    private MobileBy Increase_Quantity_Btn = (MobileBy) MobileBy.AccessibilityId("Dummy3");
    private MobileBy Sizes_CheckBoxes = (MobileBy) MobileBy.AccessibilityId("DummySize");
    private MobileBy ItemSizes_Text_list = (MobileBy) MobileBy.AccessibilityId("DummyPrice");
    private MobileBy ItemPrice_Text_list = (MobileBy) MobileBy.AccessibilityId("DummyPrice");
    private MobileBy Share_Btn = (MobileBy) MobileBy.AccessibilityId("DummyBtn");
    private MobileBy Product_Count = (MobileBy) MobileBy.AccessibilityId("DummyCount");

    SoftAssert Assert1 = new SoftAssert();

    public  void addItemToCart(MobileDriver<MobileElement> driver) throws InterruptedException {
        waitUntil(driver,Add_To_Cart);
        driver.findElement(Add_To_Cart).click();
        Assert1.assertEquals(driver.findElement(Product_Count) , 1);

    }

    public  void increaseItemQuantityInCart(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Increase_Quantity_Btn);
        driver.findElement(Increase_Quantity_Btn).click();
        Assert1.assertEquals(driver.findElement(Product_Count) , 2);

    }

    public  void selectItemSize(MobileDriver<MobileElement> driver , int Num) throws InterruptedException {
        waitUntil(driver,Sizes_CheckBoxes);
        driver.findElements(Sizes_CheckBoxes).get(Num).click();


    }

    public  void validateEverySizePrice(MobileDriver<MobileElement> driver , int Num) throws InterruptedException {
        waitUntil(driver,Sizes_CheckBoxes);
        Assert1.assertEquals( driver.findElements(ItemSizes_Text_list).get(Num) , "Size");
        Assert1.assertEquals( driver.findElements(ItemPrice_Text_list).get(Num) , "Price");


    }

    public  void shareProduct(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Share_Btn);
        driver.findElement(Share_Btn).click();
        Assert1.assertTrue(driver.findElement(Share_Btn).isEnabled() == false);
    }
}
