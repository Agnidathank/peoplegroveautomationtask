package com.practice.testCases;

import com.practice.pageObject.CareerPage;
import com.practice.pageObject.SignIn;
import com.practice.pageObject.jobtitleanduser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class jobtitleanduserlist extends baseClass{
    public static Logger logger = LogManager.getLogger(jobtitleanduserlist.class);
    @Test(description = "List job titles and recommended users")
    public void listjobtitlesandusers() throws InterruptedException, IOException {
        // Initialize the CareerPage
        signin = new SignIn(driver);
        CareerPage careerPage = new CareerPage(driver);
        jobtitleanduser jobtitleanduser = new jobtitleanduser(driver);

        signin.login(email,password);
        logger.info("Login successful");
        careerPage.clickJobs();
        logger.info("Clicked Jobs menu");
        jobtitleanduser.listJobTitlesAndRecommendedUsers();
        logger.info("job titles and recommended users listed successfully and write to file");
        driver.close();
        logger.info("Browser closed successfully");
}}
