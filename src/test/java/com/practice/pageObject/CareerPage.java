package com.practice.pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CareerPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = LogManager.getLogger(jobtitleanduser.class);

    // Career dropdown button
    @FindBy(id = "headlessui-popover-button-1")
    WebElement careerMenu;

    // Jobs option inside dropdown
    @FindBy(xpath = "//button//p[normalize-space()='Jobs']")
    WebElement jobsOption;

    @FindBy(xpath = "//*[normalize-space(@class)='ant-card job-card-item']")
    WebElement Firstjobpresent;

    @FindBy(xpath = "//*[contains(@class, 'personDetailsRight')][1]")
    WebElement persondetails;

    @FindBy(xpath = "(//button[normalize-space()='Message'])[1]")
    WebElement messagebutton;


    @FindBy(css = "div.fr-element.fr-view")
    WebElement messagebox;

    @FindBy(xpath = "//*[contains(@class, 'sendMessageBtn')]")
    WebElement sendmessagebutton;

    @FindBy(xpath = "(//button[normalize-space()='Go to Inbox'])")
    WebElement gotoinboxbutton;

    @FindBy(xpath = "//*[@class='inbox__msg inbox__msg-right undefined']")
    WebElement inboxmessage;

    @FindBy(xpath = "(//*[@class='job-role'])[1]")
    WebElement firstJobTitle;

    @FindBy(xpath = "(//*[@class='job-role'])[2]")
    WebElement secondJobTitle;


    @FindBy(xpath = "//*[@class='ant-btn username custom-btn-link']")
    WebElement userName;

    public CareerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        logger.info("CareerPage initialized");
    }

    // Hover Career menu to reveal dropdown
    public void openCareerDropdown() {
        actions.moveToElement(careerMenu).perform();
        wait.until(ExpectedConditions.visibilityOf(jobsOption));
    }

    // Click Jobs option
    public void clickJobs() {
        openCareerDropdown();
        wait.until(ExpectedConditions.elementToBeClickable(jobsOption));
        jobsOption.click();
        logger.info("Clicked on Jobs option");
    }

    public void selectFirstJob() {
        wait.until(ExpectedConditions.visibilityOf(Firstjobpresent));
        Firstjobpresent.click();
        logger.info("Clicked on the first job in the list");
    }

    public void sendmessage(String messagetext){
        wait.until(ExpectedConditions.visibilityOf(persondetails));
        try {
            if (messagebutton.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(messagebutton));
                messagebutton.click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", messagebox);
                logger.info("Message box scrolled into view");
                ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1];", messagebox, messagetext);
                // Trigger input event so Froala updates the state
                ((JavascriptExecutor) driver).executeScript(
                        "var evt = new Event('input', { bubbles: true }); arguments[0].dispatchEvent(evt);",
                        messagebox
                );
                logger.info("Message set and input event triggered");
                Thread.sleep(500);
                wait.until(ExpectedConditions.elementToBeClickable(sendmessagebutton));
                sendmessagebutton.click();
            }
        } catch (Exception e) {
            logger.info("Message button not present, skipping send message step.");
            System.out.println("Message button not present. Skipping sendmessage step.");
        }
    }

    public String verifymessageinbox() {
    wait.until(ExpectedConditions.elementToBeClickable(gotoinboxbutton));
    gotoinboxbutton.click();
    wait.until(ExpectedConditions.visibilityOf(inboxmessage));
    return inboxmessage.getText();
}

}
