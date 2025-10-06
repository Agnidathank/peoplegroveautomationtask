package com.practice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SignIn {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//*[normalize-space()='Sign In']")
    WebElement signInBtn;

    @FindBy(xpath = "//*[@type='email']")
    WebElement emailField;

    @FindBy(xpath = "//*[@type='password']")
    WebElement passwordField;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement submitBtn;

    public SignIn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public void login(String email, String password) {
        clickSignIn();
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }

}



