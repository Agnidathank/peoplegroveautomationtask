package com.practice.testCases;

import com.practice.pageObject.*;
import com.practice.utilities.ReadExcelFile;
import com.practice.utilities.extentReport;
import lombok.Locked;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(extentReport.class)
public class TC_myAccountPageTestDataDrivenTesting extends baseClass {


    // Click on sign in button and enter email address and click on submit.
    @Test(enabled = false)
    public void verifyRegistrationAndLogin() throws InterruptedException {

        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Sign in button was clicked");

        myAccount myAccountPage = new myAccount(driver);

        myAccountPage.enterEmail("tarun.official152@gmail.com");
        logger.info("Email id was entered");

        myAccountPage.clickSubmit();
        logger.info("Submit button was clicked");

        // Fill all the details for registration process

        accountCreatingDetails accPage = new accountCreatingDetails(driver);

        accPage.selectTitle();
        accPage.enterFirstName("Tarun");
        accPage.enterLastName("Chauhan");
        accPage.enterPassword("123456");
        accPage.selectDay("15");
        accPage.selectMonth("9");
        accPage.selectYear("2002");
        logger.info("All the details were entered");

        accPage.clickSubmit();
        logger.info("Submit button is clicked");

        verifyRegisteredUser userVerify = new verifyRegisteredUser(driver);
        String uName = userVerify.getUsername();

        Assert.assertEquals(uName, "Tarun Chauhan");
    }

    @Test(dataProvider = "loginDataProvider")
    public void verifyLogin(String userEmail, String userPwd, String expUsername) throws IOException, InterruptedException {
        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Sign in button was clicked");

        logger.info("----Verify login execution started----");

        myAccount myAccountPage = new myAccount(driver);

        logger.info("----Email entered----");
        myAccountPage.registeredEmail(userEmail);
        logger.info("----Password entered----");
        myAccountPage.registeredPassword(userPwd);
        logger.info("----Sign in button clicked----");
        myAccountPage.clickSignIn();
        captureScreenShot(driver,"loginFailed");

        verifyRegisteredUser userVerify = new verifyRegisteredUser(driver);
        String uName = userVerify.getUsername();
        System.out.println(uName);

        if(uName.equals(expUsername))
        {
            logger.info("verifyLogin - Passed");
            Assert.assertTrue(true);
            //userVerify.signOut();
        }else{
            logger.info("verifyLogin - Failed");
            captureScreenShot(driver,"verifyLogin");
            Assert.fail("Username is not same as expected");
        }

        userVerify.signOut();
        logger.info("----Sign out button was clicked----");

        //System.out.println(page.getPgTitle());
        if(page.getPgTitle().equals("Login - My Shop"))
        {
            logger.info("----Page title verified----");
            Assert.assertTrue(true);
        }else {
            logger.info("----Page title doesn't match----");
            captureScreenShot(driver,"verifySignOut");
            Assert.fail();
        }
        tearDown();
    }

    /*@Test(dataProvider = "loginDataProvider")
    public void verifySignOut(String userEmail, String userPwd, String expUsername) throws InterruptedException, IOException {

        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Sign in button was clicked");

        logger.info("----Verify login execution started----");

        myAccount myAccountPage = new myAccount(driver);

        logger.info("----Email entered----");
        myAccountPage.registeredEmail(userEmail);
        logger.info("----Password entered----");
        myAccountPage.registeredPassword(userPwd);
        logger.info("----Sign in button clicked----");
        myAccountPage.clickSignIn();

        verifyRegisteredUser userVerify = new verifyRegisteredUser(driver);
        userVerify.signOut();
        logger.info("----Sign out button was clicked----");

        //System.out.println(page.getPgTitle());
        if(page.getPgTitle().equals("Login - My Shop"))
        {
            logger.info("----Page title verified----");
            Assert.assertTrue(true);
        }else {
            logger.info("----Page title doesn't match----");
            captureScreenShot(driver,"verifySignOut");
            Assert.fail();
        }
    }*/

    @DataProvider(name = "loginDataProvider")
    public String[][] loginDataProvider() throws IOException {

            String fileName = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";

            int totalRows = 2;//ReadExcelFile.getRowCount(fileName,"LoginTestData");
            int totalColumns = ReadExcelFile.getCellCount(fileName,"LoginTestData");

            String data[][] = new String[totalRows-1][totalColumns];

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
