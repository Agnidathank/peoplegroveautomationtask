package com.practice.pageObject;

import com.sun.jna.platform.win32.COM.Wbemcli;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class customerAddress {

    WebDriver localDriver;

    public customerAddress(WebDriver remoteDriver){
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    @FindBy(id = "company")
    WebElement company;

    @FindBy(id = "address1")
    WebElement address1;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "id_state")
    WebElement state;

    @FindBy(id = "postcode")
    WebElement postCode;

    @FindBy(id = "phone")
    WebElement phoneValue;

    @FindBy(id = "phone_mobile")
    WebElement mobileNumber;

    @FindBy(id = "alias")
    WebElement addressNickname;

    @FindBy(id = "submitAddress")
    WebElement saveAddress;


    public void companyName(String cName) throws InterruptedException {
        Thread.sleep(3000);
        company.sendKeys(cName);
    }

    public void completeAddress(String address){
        address1.sendKeys(address);
    }

    public void cityName(String cityname){
        city.sendKeys(cityname);
    }

    public void selectState(){
        Select select = new Select(state);
        select.selectByValue("25");
    }

    public void pinCode(String pin){
        postCode.sendKeys(pin);
    }

    public void phoneNumber(String phone){
        phoneValue.sendKeys(phone);
    }

    public void mobileNumber(String number){
        mobileNumber.sendKeys(number);
    }

    public void setAddressNickname(String nickname){
        addressNickname.sendKeys(nickname);
    }

    public void clickSaveAddress(){
        saveAddress.click();
    }
}
