package com.practice.testCases;

import com.practice.pageObject.CareerPage;
import com.practice.pageObject.SignIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
public class OpenJobsMenuSendText extends baseClass {
    public static Logger logger = LogManager.getLogger(jobtitleanduserlist.class);
    @Test(description = "Verify message sent is listed under user's chat")
    public void verifymessagesendlistedunderusereschat() {
        signin = new SignIn(driver);
        CareerPage careerPage = new CareerPage(driver);
        signin.login(email,password);
        logger.info("Login successful");
        // Hover Career and click Jobs
        careerPage.clickJobs();
        logger.info("Clicked Jobs menu");
        // Select the first job from the list
        careerPage.selectFirstJob();
        logger.info("Selected the first job from the list");
        // Send a message to the recruiter
        careerPage.sendmessage(message);
        logger.info("Message sent to the recruiter successfully");
        // Verify the message in the inbox
        String actualMessage = careerPage.verifymessageinbox();
        Assert.assertTrue(actualMessage.contains(message),"Message send not listed under users chat");
        logger.info("Verified the message is listed under user's chat");
        driver.close();
        logger.info("Browser closed successfully");
    }
}
