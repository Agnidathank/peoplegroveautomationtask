package com.practice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class verifyRegisteredUser {
    WebDriver localDriver;

    public verifyRegisteredUser(WebDriver remoteDriver){
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    @FindBy(xpath = "//a[@title='View my customer account']")
    WebElement userName;

    @FindBy(xpath = "//a[@class='logout']")
    WebElement signOutBtn;


    public String getUsername(){
        String name = userName.getText();
        return name;
    }

    public void signOut(){
        signOutBtn.click();
    }
}
