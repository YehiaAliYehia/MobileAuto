package appTests;

import appPages.Product_Screen;
import base.TestBase;
import org.testng.annotations.Test;

public class Test1 extends TestBase {
    @Test(priority = 1)
    public void validateAddProductToCart() throws InterruptedException {

        Product_Screen.addItemToCart(driver);
    }

    @Test(priority = 2)
    public void validateIncreaseProductQuatity() throws InterruptedException {

        Product_Screen.addItemToCart(driver);
        Product_Screen.increaseItemQuantityInCart(driver);
    }
    @Test(priority = 3)
    public void validateSelectingItemSize() throws InterruptedException {

        Product_Screen.selectItemSize(driver , 1);
        Product_Screen.addItemToCart(driver);
        Product_Screen.increaseItemQuantityInCart(driver);
    }

    @Test(priority = 3)
    public void validateOnSharingScreen() throws InterruptedException {
        Product_Screen.shareProduct(driver);
    }

    @Test(priority = 3)
    public void validateEverySizeHasTheCorrectPrice() throws InterruptedException {
        Product_Screen.validateEverySizePrice(driver , 1);
        Product_Screen.validateEverySizePrice(driver , 2);
        Product_Screen.validateEverySizePrice(driver , 3);
    }
}

