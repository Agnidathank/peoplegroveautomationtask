package com.practice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class myAccount {

    WebDriver localDriver;

    public myAccount(WebDriver remoteDriver)
    {
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    //New user registered element
    @FindBy(id = "email_create")
    WebElement emailId;

    @FindBy(id = "SubmitCreate")
    WebElement submitBtn;

    //Already registered user element
    @FindBy(id = "email")
    WebElement registeredEmail;

    @FindBy(id = "passwd")
    WebElement registeredPassword;

    @FindBy(id = "SubmitLogin")
    WebElement signInButton;


    //New user registered method
    public void enterEmail(String email)
    {
        emailId.sendKeys(email);
    }

    public void clickSubmit()
    {
        submitBtn.click();
    }

    //Already registered user element
    public void registeredEmail(String regEmail) throws InterruptedException {
        Thread.sleep(2000);
        registeredEmail.sendKeys(regEmail);
    }

    public void registeredPassword(String regPassword)
    {
        registeredPassword.sendKeys(regPassword);
    }

    public void clickSignIn()
    {
        signInButton.click();
    }

}
