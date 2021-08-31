package appPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import testData.ProductsNames;

public class Search_Screen extends PageBase{

    private By Search_Box = By.id("com.goldenscent.c3po:id/search_src_text");
    // I put absolute xpath because , this element hasn't extra locators like id or accessability id
    private By Search_Selection1 = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]");

    public  void SearchForProduct(MobileDriver<MobileElement> driver , ProductsNames productName) throws InterruptedException {
        waitUntil(driver,Search_Box);
        driver.findElement(Search_Box).sendKeys(productName.toString());

    }

    public  void selectSearch_Selection1(MobileDriver<MobileElement> driver ) throws InterruptedException {
        waitUntil(driver,Search_Selection1);
        driver.findElement(Search_Selection1).click();


    }
}
