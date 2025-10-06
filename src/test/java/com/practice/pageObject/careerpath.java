package com.practice.pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class careerpath {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = LogManager.getLogger(jobtitleanduser.class);

    @FindBy(id = "headlessui-popover-button-1")
    WebElement careerMenu;

    @FindBy(xpath = "//button//p[normalize-space()='Career Paths']")
    WebElement CareerPathbtn;

    @FindBy(xpath = "//*[contains(text(),'Inspiration for')]")
    WebElement InspirationSection;

    @FindBy(xpath = "//*[@class='careers-row-body careers-mobile careers-row-multiple']//a[contains(@class, 'title')]")
    List<WebElement> InspirationSectionTitles;

    @FindBy(xpath = "//*[@aria-current='location']")
    WebElement currentLocation;

    @FindBy(xpath = "//*[contains(@class, 'Modal-module_surface')]")
    WebElement Popup;

    @FindBy(xpath = "//*[@aria-label='close']")
    WebElement Closebtn;

    @FindBy(xpath = "//*[contains(text(), 'Recommended Careers based on your')]")
    WebElement RecommendedCareerSection;

    @FindBy(xpath = "((//*[contains(@class, 'Carousel-module_slider')])[1]//a)")
    WebElement RecommendedCareerTitles;

    public careerpath(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        logger.info("CareerPath initialized");
    }


    public void opencareerpath(){
        wait.until(ExpectedConditions.elementToBeClickable(CareerPathbtn)).click();
        logger.info("Clicked on Career Path button");
    }
    public void selectingInspirationSection(){
        handlePopupIfVisible();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", InspirationSection);
        wait.until(ExpectedConditions.visibilityOf(InspirationSection));
        logger.info("Inspiration Section is visible");

    }

    public List<String> clickTop3Careers() {
        List<String> titleTexts = new ArrayList<>();

        // Step 1: Ensure titles are visible
        wait.until(ExpectedConditions.visibilityOfAllElements(InspirationSectionTitles));

        // Step 2: Store first 3 titles in a list (only names, not WebElements)
        int count = Math.min(InspirationSectionTitles.size(), 3);
        for (int i = 0; i < count; i++) {
            titleTexts.add(InspirationSectionTitles.get(i).getText().trim());
        }
        logger.info("Collected first " + count + " titles: " + titleTexts);

        // Step 3: Iterate through stored titles
        for (int i = 0; i < count; i++) {
            String titleText = titleTexts.get(i);
            logger.info("Clicking on career: " + titleText);

            // re-locate the title before clicking (DOM may have refreshed)
            WebElement titleToClick = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//*[@class='careers-row-body careers-mobile careers-row-multiple']//a[contains(@class, 'title')])[" + (i + 1) + "]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleToClick);
            titleToClick.click();

            // wait for page to load (can be replaced with a more specific condition)
            wait.until(ExpectedConditions.visibilityOf(currentLocation));
            logger.info("Page loaded for: " + titleText);

            // navigate back
            driver.navigate().back();

            // wait until Inspiration section reappears
            wait.until(ExpectedConditions.visibilityOf(InspirationSection));
            logger.info("Navigated back to Career Path page");

            // handle popup if visible after navigating back
            handlePopupIfVisible();
        }

        logger.info("Completed clicking 3 career paths: " + titleTexts);
        return titleTexts;
    }

    public void handlePopupIfVisible(){
        try {
            wait.until(ExpectedConditions.visibilityOf(Popup));
            if (Popup.isDisplayed()) {
                logger.info("Popup appeared, closing it.");
                wait.until(ExpectedConditions.elementToBeClickable(Closebtn)).click();
                wait.until(ExpectedConditions.invisibilityOf(Popup));
                logger.info("Popup closed.");
            }
        } catch (Exception e) {
            logger.info("No popup appeared");
        }
    }

    public List<String> getRecommendedCareerTitles() {
        List<String> recommendedTitles = new ArrayList<>();

        try {
            driver.navigate().refresh();
            handlePopupIfVisible();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", RecommendedCareerSection);
            wait.until(ExpectedConditions.visibilityOf(RecommendedCareerSection));
            logger.info("Scrolled to Recommended Career section");

            // Wait for the titles to be visible
            wait.until(ExpectedConditions.visibilityOfAllElements(RecommendedCareerTitles));

            // Grab first 3 titles (or less if not enough)
            List<WebElement> titles = driver.findElements(By.xpath("((//*[contains(@class, 'Carousel-module_slider')])[1]//a)"));
            int count = Math.min(titles.size(), 3);
            for (int i = 0; i < count; i++) {
                recommendedTitles.add(titles.get(i).getText().trim());
            }

            logger.info("Collected Recommended Career titles: " + recommendedTitles);

        } catch (Exception e) {
            logger.error("Error while fetching recommended career titles", e);
        }

        return recommendedTitles;
    }
}


