package com.practice.testCases;

import com.practice.pageObject.SignIn;
import com.practice.utilities.readConfigFile;
import org.apache.commons.io.FileUtils;
import org.apache.poi.wp.usermodel.CharacterRun;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;

public class baseClass {

    readConfigFile readConfig = new readConfigFile();
    public static WebDriver driver;
    public static Logger logger;
    public SignIn signin;
    String url = readConfig.getUrl();
    String browser = readConfig.getBrowser();
    String email = readConfig.getEmail();
    String password = readConfig.getPassword();
    String message = readConfig.getMessage();
    @Parameters("browser")
    @BeforeClass
    public void setup() throws InterruptedException {
        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless=new"); // new headless mode (Chrome 109+)
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "msedge":
                driver = new EdgeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            default:
                driver = null;
                break;
        }

        // Adding an implicit wait of 10 seconds to all elements
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        logger = LogManager.getLogger("FrameworkDevelopment");
        driver.get(url);
        logger.info("URL is opened");


    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }

    public void captureScreenShot(WebDriver driver,String testName) throws IOException
    {
        //step1: convert webdriver object to TakesScreenshot interface
        TakesScreenshot screenshot = ((TakesScreenshot)driver);

        //step2: call getScreenshotAs method to create image file

        File src = screenshot.getScreenshotAs(OutputType.FILE);

        File dest = new File(System.getProperty("user.dir") + "//screenshots//" + testName + ".png");

        //step3: copy image file to destination
        FileUtils.copyFile(src, dest);
    }

}
