package com.practice.testCases;

import com.practice.pageObject.CareerPage;
import com.practice.pageObject.SignIn;
import com.practice.pageObject.careerpath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class recommendedcareer extends baseClass{
    public static Logger logger = LogManager.getLogger(recommendedcareer.class);
    @Test(description = "Verify the recommended career list and list is revered based on the activity")
    public void verifyrecommendedlistrevised() {
        signin = new SignIn(driver);
        CareerPage careerPage = new CareerPage(driver);
        careerpath Careerpath  = new careerpath(driver);
        signin.login(email,password);
        logger.info("Login successful");
        careerPage.openCareerDropdown();
        Careerpath.opencareerpath();
        logger.info("Opened Career Path page");
//      Careerpath.handlePopupIfVisible();
        Careerpath.selectingInspirationSection();
        List<String> clickedTitles = Careerpath.clickTop3Careers();
        logger.info("Clicked career titles: " + clickedTitles);
        List<String> recommendedTitles = Careerpath.getRecommendedCareerTitles();
        logger.info("Recommended career titles: " + recommendedTitles);

        List<String> reversedClickedTitles = new ArrayList<>(clickedTitles);
        Collections.reverse(reversedClickedTitles);
        Assert.assertEquals(recommendedTitles, reversedClickedTitles, "Recommended career titles are not in reverse order of the activity");
        logger.info("Verified the recommended career list is in reverse order based on the activity");
        driver.close();
        logger.info("Browser closed successfully");
}
}
