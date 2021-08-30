package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import appPages.Product_Screen;
import io.appium.java_client.*;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import testData.OS;

public class TestBase extends TestListenerAdapter {

    static String scrShotDir = "bugsrecorder/screenshots";
    File scrFile;
    static File scrShotDirPath = new java.io.File("./" + scrShotDir + "//");
    String destFile;
    protected MobileDriver<MobileElement> driver;
    public static AppiumDriverLocalService service;

    protected String lastPackageName = "com.exa.nanamarket.staging";
    protected DesiredCapabilities capabilities;
    public static OS executionOS = OS.ANDROID;

    protected Product_Screen Product_Screen;
    private Object installApp;


    public InteractsWithFiles Capabilities(boolean installApp) throws MalformedURLException {
        capabilities = new DesiredCapabilities();

        switch (executionOS) {

            // to run on android
            case ANDROID:

                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "appium");
// if application isn't installed , will install it.
                if (installApp ) {
                    String appPath = System.getProperty("user.dir")
                            + "/Application/Test.apk";
                    capabilities.setCapability(MobileCapabilityType.APP, appPath);
                } else {
                    capabilities.setCapability("appPackage", "com.exa.nanamarket.staging");
                    // An activity name for the Android activity you want to run from your package.
                    capabilities.setCapability("appActivity", "com.exa.nanamarket.login.SplashActivity");
                }


                capabilities.setCapability("newCommandTimeout", "100000");
                capabilities.setCapability("adbExecTimeout", "10000000");


                capabilities.setCapability("platformName", "Android");
                // when close the app will not clear the user data
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);


                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            // to run on iOS
            case IOS:
                String appPath = System.getProperty("user.dir")
                        + "/Application/Test1.ipa";
                capabilities.setCapability(MobileCapabilityType.APP, appPath);
                capabilities.setCapability("platformName", "ios");
                capabilities.setCapability("deviceName", "iPhone 7");
                capabilities.setCapability("automationName", "XCUITest");
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;

        }

        return driver;
    }
    // Objects


    @BeforeClass
    public void initializeClassTestData() throws InterruptedException, IOException {
        stopServer();
        startServer();



	driver = (MobileDriver<MobileElement>) Capabilities(true);

        // Initialize Pages objects

        Product_Screen = new Product_Screen();


        // Wait loading screen
        Thread.sleep(4000);
    }


    @BeforeMethod
    public void initializeClassTestCaseData()  {

        // Open App while it login so it will open on stores list screen

        //	driver.close();
        try {
            driver.activateApp(lastPackageName);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    @AfterMethod
    public void clearTestData(ITestResult result)  {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/bugsrecorder/screenshots";
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            // driver.terminateApp(driver.getCurrentPackage());
            driver.closeApp();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @AfterClass
    public void clearTestClassTestData() throws InterruptedException {

        resetDriver();
        // close driver
        driver.quit();
    }


    public  void resetDriver() {
        capabilities.setCapability("noReset", false);
        try {
            driver = (MobileDriver<MobileElement>) Capabilities(true);

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public AppiumDriverLocalService startServer()
    {
        //
        boolean flag=	checkIfServerIsRunnning(4723);
        if(!flag)
        {

            service=AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;

    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String ReadScreenTexts() throws TesseractException {
        String imgName = takeScreenShot();
        String result = null;
        File imageFile = new File(scrShotDirPath, imgName);
        System.out.println("Image name is :" + imageFile.toString());
        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

        instance.setDatapath(tessDataFolder.getAbsolutePath());

        result = instance.doOCR(imageFile);
        System.out.println(result);
        return result;
    }

    public String takeScreenShot(){

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        new File(scrShotDir).mkdirs();
        destFile = dateFormat.format(new Date()) + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile));

        } catch (IOException e) {
            System.out.println("Image not transfered to screenshot folder");
            e.printStackTrace();
        }
        return destFile;
    }
}
