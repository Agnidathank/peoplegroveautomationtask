package com.practice.testCases;

import com.practice.pageObject.accountCreatingDetails;
import com.practice.pageObject.indexPage;
import com.practice.pageObject.myAccount;
import com.practice.pageObject.verifyRegisteredUser;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_myAccountPageTest extends baseClass {


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

    @Test
    public void verifyLogin() throws IOException, InterruptedException {
        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Sign in button was clicked");

        logger.info("----Verify login execution started----");

        myAccount myAccountPage = new myAccount(driver);

        logger.info("----Email entered----");
        myAccountPage.registeredEmail("tarun.official352@gmail.com");
        logger.info("----Password entered----");
        myAccountPage.registeredPassword("Tarun1234");
        logger.info("----Sign in button clicked----");
        myAccountPage.clickSignIn();

        verifyRegisteredUser userVerify = new verifyRegisteredUser(driver);
        String uName = userVerify.getUsername();

        if(uName.equals("Tarun Chauhan"))
        {
            logger.info("verifyLogin - Passed");
            Assert.assertTrue(true);
        }else{
            logger.info("verifyLogin - Failed");
            captureScreenShot(driver,"verifyLogin");
            Assert.fail("Username is not same as expected");
        }
    }

    @Test
    public void verifySignOut() throws InterruptedException, IOException {

        indexPage page = new indexPage(driver);
        page.signIn();
        logger.info("Sign in button was clicked");

        logger.info("----Verify login execution started----");

        myAccount myAccountPage = new myAccount(driver);

        logger.info("----Email entered----");
        myAccountPage.registeredEmail("tarun.official352@gmail.com");
        logger.info("----Password entered----");
        myAccountPage.registeredPassword("Tarun1234");
        logger.info("----Sign in button clicked----");
        myAccountPage.clickSignIn();

        verifyRegisteredUser userVerify = new verifyRegisteredUser(driver);
        userVerify.signOut();
        logger.info("----Sign out button was clicked----");

        if(page.getPgTitle().equals("Login - My Shop"))
        {
            logger.info("----Page title verified----");
            Assert.assertTrue(true);
        }else {
            logger.info("----Page title doesn't match----");
            captureScreenShot(driver,"verifySignOut");
            Assert.fail();
        }
    }
}
