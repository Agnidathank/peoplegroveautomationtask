package com.practice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

import static com.practice.testCases.baseClass.driver;

public class productPage {

    WebDriver localDriver;

    public productPage(WebDriver remoteDriver)
    {
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver, this);
    }

    @FindBy(id = "search_query_top")
    WebElement searchBox;

    @FindBy(xpath = "//button[@name=\"submit_search\"]")
    WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a")
    WebElement productText;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")
    WebElement buyProduct;

    @FindBy(xpath = "//a[@class=\"button lnk_view btn btn-default\" and contains(@href, \"id_product=6\")]")
    WebElement moreButton;

    @FindBy(id = "color_8")
    WebElement colorSelect;

    @FindBy(xpath = "//a[@data-field-qty=\"qty\"]//i[@class=\"icon-plus\"]")
    WebElement plusQuantity;

    @FindBy(id = "group_1")
    WebElement sizeDropdown;

    @FindBy(xpath = "//button[@name=\"Submit\"]")
    WebElement addToCart;

    @FindBy(id = "layer_cart")
    WebElement modalBox;

    @FindBy(xpath = "//a[@title=\"Proceed to checkout\"]")
    WebElement checkoutBtn;


    public void searchTerm(String searchBoxTerm) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        searchBox.sendKeys(searchBoxTerm);
    }

    public void clickSearchBtn()
    {
        searchBtn.click();
    }

    public void searchProduct(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buyProduct);
        Actions action = new Actions(driver);
        action.moveToElement(buyProduct).perform();
    }

    public String searchTermMatched() {
        // Re-locate element freshly
        WebElement resultText = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Faded Short Sleeve T-shirts')]")));
        return resultText.getText();
    }

    public void clickMoreButton(){
        moreButton.click();
    }

    public void selectColor(){
        colorSelect.click();
    }
    public void increaseQuantity() throws InterruptedException {
        Thread.sleep(3000);
        plusQuantity.click();
    }

    public void selectDropdown(){
        Select select = new Select(sizeDropdown);
        select.selectByVisibleText("M");
    }

    public void clickAddToCart(){
        addToCart.click();
    }

    public void handleModal(){
        modalBox.click();
        checkoutBtn.click();
    }
}
