package com.practice.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class jobtitleanduser {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = LogManager.getLogger(jobtitleanduser.class);

    public jobtitleanduser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        logger.info("jobtitleanduser page initialized");
    }

    // Job titles
    @FindBy(xpath = "//*[@class='job-role']")
    List<WebElement> jobTitles;

    // Recommended users (all jobs)
    @FindBy(xpath = "//*[@class='ant-btn username custom-btn-link']")
    List<WebElement> recommendedUsers;

    // Next connection buttons (all jobs)
    @FindBy(xpath = "//*[@aria-label='Next connection']")
    List<WebElement> nextConnectionButtons;

    public void listJobTitlesAndRecommendedUsers() throws InterruptedException, IOException {
        logger.info("Starting to list job titles and recommended users");
        int userStartIndex = 1; // XPath indexing starts at 1

        // Create file in project directory
        File file = new File("Jobtitle_recommandeduser.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // append mode

        // Iterate first 2 job titles
        for (int j = 0; j < 2; j++) {
            String jobTitle = jobTitles.get(j).getText();
            logger.info("Processing job title: " + jobTitle);

            List<String> allUsers = new ArrayList<>();
            Set<String> seenUsers = new HashSet<>();

            // XPath for next button dynamically based on job index
            By nextButtonBy = By.xpath("(//*[@aria-label='Next connection'])[" + (j + 1) + "]");

            // Identify first user for this job
            List<WebElement> users = driver.findElements(By.xpath(
                    "//*[@class='ant-btn username custom-btn-link']"
            ));
            if (users.isEmpty()) {
                logger.info("No users found for job: " + jobTitle);
                writer.write(jobTitle + " – No recommended users found");
                writer.newLine();
                continue;
            }

            String firstUser = users.get(userStartIndex - 1).getText().trim();

            while (true) {
                users = driver.findElements(By.xpath(
                        "//*[@class='ant-btn username custom-btn-link']"
                ));

                for (int k = userStartIndex - 1; k < users.size(); k++) {
                    String name = users.get(k).getText().trim();
                    if (!name.isEmpty() && !seenUsers.contains(name)) {
                        allUsers.add(name);
                        seenUsers.add(name);
                        logger.debug("User: " + name);
                    }
                }

                // Click the correct next connection button for this job
                WebElement nextButton = driver.findElement(nextButtonBy);
                if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {
                    break;
                }
                nextButton.click();
                Thread.sleep(500);

                // Stop if first user repeats
                users = driver.findElements(By.xpath(
                        "//*[@class='ant-btn username custom-btn-link']"
                ));
                String currentFirstUser = users.get(userStartIndex - 1).getText().trim();
                if (currentFirstUser.equals(firstUser)) {
                    System.out.println("No more connections found for job: " + jobTitle);
                    break;
                }
            }

            // Print and write to file
            String jobWithUsers = jobTitle + " – " + String.join(", ", allUsers);
            logger.info("Job and users: " + jobWithUsers);

            writer.write(jobWithUsers);
            writer.newLine();

            // Update starting index for next job
            userStartIndex += allUsers.size();
        }

        writer.close();
        logger.info("Job users written to: " + file.getAbsolutePath());
    }
}
