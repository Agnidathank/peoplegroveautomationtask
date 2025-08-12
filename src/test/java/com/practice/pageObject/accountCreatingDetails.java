package com.practice.pageObject;

import com.practice.testCases.baseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.IDataProviderAnnotation;

public class accountCreatingDetails extends baseClass {

    WebDriver localDriver;

    public accountCreatingDetails(WebDriver remoteDriver){
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    @FindBy(id = "id_gender1")
    WebElement title;

    @FindBy(id = "customer_firstname")
    WebElement custFirstName;

    @FindBy(id = "customer_lastname")
    WebElement custLastName;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "days")
    WebElement days;

    @FindBy(id = "months")
    WebElement months;

    @FindBy(id = "years")
    WebElement years;

    @FindBy(id = "submitAccount")
    WebElement submitButton;

    public void selectTitle()
    {
        title.click();
    }

    public void enterFirstName(String firstName){
        custFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        custLastName.sendKeys(lastName);
    }

        public void enterPassword(String Password){
        password.sendKeys(Password);
    }

    public void selectDay(String Days){
        Select day = new Select(days);
        day.selectByValue(Days);
    }

    public void selectMonth(String Months){
        Select month = new Select(months);
        month.selectByValue(Months);
    }

    public void selectYear(String Years){
        Select year = new Select(years);
        year.selectByValue(Years);
    }

    public void clickSubmit(){
        submitButton.click();
    }
}
