package com.practice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.apache.xmlbeans.XmlBeans.getTitle;

public class indexPage {

    WebDriver localDriver;

    public indexPage(WebDriver remoteDriver)
    {
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    // Find all the web elements from which we want to interact
    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    WebElement signIn;

    // Make a method to implement actions on the web element
    public void signIn() throws InterruptedException {
        Thread.sleep(2000);
        signIn.click();
    }

    public String getPgTitle(){
        return (localDriver.getTitle());
    }
}
