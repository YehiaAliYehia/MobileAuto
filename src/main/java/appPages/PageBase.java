package appPages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import static java.time.Duration.ofSeconds;

public class PageBase {

    public void waitUntil(MobileDriver<MobileElement> driver, By element) {
        FluentWait wait = new FluentWait(driver).withTimeout(ofSeconds(30)).pollingEvery(ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void scroll(MobileDriver<MobileElement> driver ,int startx, int starty, int endx, int endy) {

        TouchAction touchAction = new TouchAction(driver);

        touchAction.longPress(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy))
                .release()
                .perform();

    }

    public void swipeRight(MobileDriver<MobileElement> driver) {

        //The viewing size of the device
        Dimension size = driver.manage().window().getSize();

        //Starting x location set to 5% of the width (near left)
        int startx = (int) (size.width * 0.05);
        //Ending x location set to 95% of the width (near right)
        int endx = (int) (size.width * 0.95);
        //y position set to mid-screen vertically
        int starty = size.height / 2;

        scroll(driver , startx, starty, endx, starty);

    }
}
