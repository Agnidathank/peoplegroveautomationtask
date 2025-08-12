package com.practice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v136.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productOrderProcess {

    WebDriver localDriver;

    public productOrderProcess(WebDriver remoteDriver){
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver,this);
    }

    @FindBy(xpath = "//a[contains(@href, \"step=1\")]")
    WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button")
    WebElement proceedToCheckoutBtn2;

    @FindBy(id = "cgv")
    WebElement checkBox;

    @FindBy(xpath = "//button[@name=\"processCarrier\"]")
    WebElement shippingButton;

    @FindBy(xpath = "//a[@title=\"Pay by check.\"]")
    WebElement paymentMethod;

    @FindBy(xpath = "//button[@type=\"submit\" and span=\"I confirm my order\"]")
    WebElement confirmOrderBtn;

    @FindBy(xpath = "//p[@class=\"alert alert-success\"]")
    WebElement successMsg;

    @FindBy(xpath = "//a[@title=\"View my customer account\"]")
    WebElement custName;

    @FindBy(xpath = "//a[@title=\"Log me out\"]")
    WebElement signOutBtn;

    public void proceedToCheckout(){
        proceedToCheckoutBtn.click();
    }

    public void afterAddressProceedToCheckout() throws InterruptedException {
        Thread.sleep(3000);
        proceedToCheckoutBtn2.click();
    }

    public void termOfServices(){
        checkBox.click();
    }

    public void setShippingButton(){
        shippingButton.click();
    }

    public void setPaymentMethod() throws InterruptedException {
        Thread.sleep(3000);
        paymentMethod.click();
    }

    public void setConfirmOrderBtn(){
        confirmOrderBtn.click();
    }

    public String confirmMsg(){
        return successMsg.getText();
    }

    public String customerName(){
        return custName.getText();
    }

    public void logOut() throws InterruptedException {
        Thread.sleep(5000);
        signOutBtn.click();
    }
}
