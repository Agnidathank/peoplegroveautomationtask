package com.practice.testCases;

import com.practice.pageObject.*;
import com.practice.utilities.ReadExcelFile;
import com.practice.utilities.readConfigFile;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class productPageTestCase extends baseClass{


    @Test(dataProvider = "verifyProductDataProvider", enabled = true)
    public void VerifyProduct(String userEmail, String userPwd, String expText) throws IOException, InterruptedException {

        verifyRegisteredUser button = new verifyRegisteredUser(driver);
        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Clicked on sign in button");

        myAccount login = new myAccount(driver);
        login.registeredEmail(userEmail);
        logger.info("Email id entered");
        login.registeredPassword(userPwd);
        logger.info("Password entered");
        login.clickSignIn();
        logger.info("Logged in successfully");

        productPage pg = new productPage(driver);
        pg.searchTerm("T-Shirts");
        logger.info("Search term was entered");
        pg.clickSearchBtn();
        logger.info("Search button is clicked");

        String fetchedTerm = pg.searchTermMatched();
        System.out.println(fetchedTerm);

        if(fetchedTerm.contains(expText)){
            logger.info("VerifyProduct - Passed");
            Assert.assertTrue(true);
            button.signOut();
        }else {
            logger.info("VerifyProduct - Failed");
            captureScreenShot(driver,"VerifyProduct");
            Assert.fail("Text didn't match");
        }
    }

    @Test
    public void verifyBuyProduct() throws IOException, InterruptedException {

        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Clicked on sign in button");

        myAccount login = new myAccount(driver);
        login.registeredEmail(email); // We are reading this email from config file using base class
        logger.info("Email id entered");
        login.registeredPassword(password); // We are reading this password from config file using base class
        logger.info("Password entered");
        login.clickSignIn();
        logger.info("Logged in successfully");

        productPage pg = new productPage(driver);
        pg.searchTerm("Printed Summer Dress");
        logger.info("Search term was entered");
        pg.clickSearchBtn();
        logger.info("Search button is clicked");
        pg.searchProduct();
        logger.info("Scrolled and hover on the product");
        pg.clickMoreButton();
        logger.info("Clicked on more button");

        pg.selectColor();
        logger.info("Color selected of the dress that is in stock");
        pg.increaseQuantity();
        captureScreenShot(driver,"verifyBuyProduct");
        Assert.fail();
        logger.info("Quantity of dress is selected");
        pg.selectDropdown();
        logger.info("Size selected from the dropdown");
        pg.clickAddToCart();
        logger.info("Add to cart button is clicked");
        pg.handleModal();
        logger.info("Proceed to checkout");

        productOrderProcess productPage = new productOrderProcess(driver);
        productPage.proceedToCheckout();
        logger.info("Clicked on Proceed to Checkout button");

        /*
        customerAddress address = new customerAddress(driver);
        address.companyName("XYZ");
        address.completeAddress("Mamura");
        address.cityName("Noida");
        address.selectState();
        address.pinCode("65899");
        address.phoneNumber("8218923382");
        address.mobileNumber("8218923382");
        address.setAddressNickname("My new address");
        logger.info("Complete address is filled by the customer");
        address.clickSaveAddress();
        logger.info("Address filled and clicked on save address");*/

        productPage.afterAddressProceedToCheckout();
        logger.info("Clicked on Proceed to Checkout button on the next page");
        productPage.termOfServices();
        productPage.setShippingButton();
        logger.info("Proceed to checkout button on shipping page is clicked");
        productPage.setPaymentMethod();
        logger.info("Clicked on payment method");
        productPage.setConfirmOrderBtn();
        logger.info("Clicked on confirm order button");

        captureScreenShot(driver,"verifyBuyProduct");
        String cnfrmMsg = productPage.confirmMsg();
        String customerName = productPage.customerName();
        System.out.println("Hi " +customerName+" , " + cnfrmMsg);

        if(cnfrmMsg.equals("Your order on My Shop is complete.")){
            logger.info("verifyBuyProduct - Passed");
            Assert.assertTrue(true);
            productPage.logOut();
            logger.info("Signed Out successfully after ordering");
        }else {
            logger.info("verifyBuyProduct - Failed");
            captureScreenShot(driver,"verifyBuyProduct");
            Assert.fail("Order is not placed till yet - Please re-check and re-order");
        }
    }

    @DataProvider(name = "verifyProductDataProvider")
    public String[][] loginDataProvider1() throws IOException {

        String fileName = System.getProperty("user.dir") + "\\TestData\\TestData 2.xlsx";

        int totalRows = ReadExcelFile.getRowCount(fileName,"LoginTestData");
        int totalColumns = ReadExcelFile.getCellCount(fileName,"LoginTestData");

        String[][] data = new String[totalRows-1][totalColumns];

        for(int i=1; i<totalRows; i++)
        {
            for(int j=0; j<totalColumns; j++)
            {
                data[i-1][j] = ReadExcelFile.getCellValue(fileName,"LoginTestData",i,j);
            }
        }
        return data;
    }
}
