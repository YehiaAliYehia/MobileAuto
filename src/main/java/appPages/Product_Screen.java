package appPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import testData.Prices;
import testData.Sizes;

public class Product_Screen extends PageBase{

    private By Add_To_Cart = By.id("com.goldenscent.c3po:id/addToCart");
    private By Continue_Shopping = By.id("android:id/button2");
    private By Sizes_CheckBox = By.id("com.goldenscent.c3po:id/item_selected");
    private By ItemSizes_Text_list = By.id("com.goldenscent.c3po:id/tv_qty");
    private By ItemPrice_Text_list = By.id("com.goldenscent.c3po:id/tv_price");
    private By Share_Btn =  By.id("com.goldenscent.c3po:id/share");

    SoftAssert Assert1 = new SoftAssert();

    public  void addItemToCart(MobileDriver<MobileElement> driver) throws InterruptedException {
        waitUntil(driver,Add_To_Cart);
        driver.findElement(Add_To_Cart).click();
        Thread.sleep(2000);
        Assert1.assertEquals(driver.findElement(Continue_Shopping).isEnabled() , true );

    }

    public  void Continue_Shopping_Click(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Continue_Shopping);
        driver.findElement(Continue_Shopping).click();


    }

    public  void selectItemSize(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Sizes_CheckBox);
        driver.findElement(Sizes_CheckBox).click();


    }

    public  void validateEverySizePrice(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Sizes_CheckBox);
        Assert1.assertEquals( driver.findElement(ItemSizes_Text_list).isDisplayed() , true);
        Assert1.assertEquals( driver.findElement(ItemPrice_Text_list).isDisplayed() ,true);


    }

    public  void shareProduct(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Share_Btn);
        Assert1.assertTrue(driver.findElement(Share_Btn).isEnabled() == true);
        driver.findElement(Share_Btn).click();
    }
}
