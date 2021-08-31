package appTests;

import appPages.Home_Screen;
import appPages.Product_Screen;
import appPages.Search_Results_Screen;
import appPages.Search_Screen;
import base.TestBase;
import org.testng.annotations.Test;
import testData.Prices;
import testData.ProductsNames;
import testData.Sizes;

public class Test1 extends TestBase {
    @Test(priority = 1)
    public void validateAddProductToCartBtnIsWorking() throws InterruptedException {
        Home_Screen.assertHomePageIsOpened(driver);
        Home_Screen.clickOnSearchBar(driver);
        Search_Screen.SearchForProduct(driver , ProductsNames.Product1);
        Search_Screen.selectSearch_Selection1(driver);
        Thread.sleep(3000);
        Search_Results_Screen.ClickOnProduct(driver , ProductsNames.Product1);
        Thread.sleep(3000);
        Product_Screen.addItemToCart(driver);
    }

    @Test(priority = 2)
    public void validateItemSizeIsSelectable() throws InterruptedException {

        Home_Screen.assertHomePageIsOpened(driver);
        Home_Screen.clickOnSearchBar(driver);
        Search_Screen.SearchForProduct(driver , ProductsNames.Product1);
        Search_Screen.selectSearch_Selection1(driver);
        Thread.sleep(3000);
        Search_Results_Screen.ClickOnProduct(driver , ProductsNames.Product1);
        Thread.sleep(3000);
        Product_Screen.selectItemSize(driver);
    }
    @Test(priority = 3)
    public void validateOnContinueShopping() throws InterruptedException {

        Home_Screen.clickOnSearchBar(driver);
        Search_Screen.SearchForProduct(driver , ProductsNames.Product1);
        Search_Screen.selectSearch_Selection1(driver);
        Thread.sleep(3000);
        Search_Results_Screen.ClickOnProduct(driver , ProductsNames.Product1);
        Thread.sleep(3000);
        Product_Screen.addItemToCart(driver);
        Product_Screen.Continue_Shopping_Click(driver);

    }

    @Test(priority = 3)
    public void validateOnSharingScreen() throws InterruptedException {
        Home_Screen.assertHomePageIsOpened(driver);
        Home_Screen.clickOnSearchBar(driver);
        Search_Screen.SearchForProduct(driver , ProductsNames.Product1);
        Search_Screen.selectSearch_Selection1(driver);
        Thread.sleep(3000);
        Search_Results_Screen.ClickOnProduct(driver , ProductsNames.Product1);
        Thread.sleep(3000);
        Product_Screen.shareProduct(driver);
    }

    @Test(priority = 3)
    public void validateOnPriceAndSizeDisplay() throws InterruptedException {
        Home_Screen.assertHomePageIsOpened(driver);
        Home_Screen.clickOnSearchBar(driver);
        Search_Screen.SearchForProduct(driver , ProductsNames.Product1);
        Search_Screen.selectSearch_Selection1(driver);
        Thread.sleep(3000);
        Search_Results_Screen.ClickOnProduct(driver , ProductsNames.Product1);
        Thread.sleep(3000);
        Product_Screen.validateEverySizePrice(driver );
    }
}

