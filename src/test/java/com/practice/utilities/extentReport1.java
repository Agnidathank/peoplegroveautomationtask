package com.practice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class extentReport1 implements ITestListener {

    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public void configureReport(){

        readConfigFile readConfig = new readConfigFile();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
        String reportName = "Framework report - " + timeStamp + ".html";
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + reportName);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        //add system information/environment info to reports
        reports.setSystemInfo("Machine","Lenovo");
        reports.setSystemInfo("OS", "Windows 10");
        reports.setSystemInfo("Browser", readConfig.getBrowser());
        reports.setSystemInfo("UserName","Tarun");

        //configuration to change look and feel of report
        htmlReporter.config().setDocumentTitle("Extent report");
        htmlReporter.config().setReportName("FrameworkDevelopment report");
        htmlReporter.config().setTheme(Theme.DARK);
    }
    //Triggered Before the test suite (or test class) starts.
    //It runs once per suite or once per test tag, depending on where it's implemented.
    public void onStart(ITestContext Result )//ITestContext is an interface in TestNG that gives you contextual information about the current test.
    {
        configureReport();
        System.out.println("On start method invoked");
    }

    //onFinish method is called after all Tests are executed.
    /*ITestResult is an interface in TestNG that gives information about the result of a single test method —
    whether it passed, failed, skipped, how long it took, what exception occurred, etc.
    */

    public void onFinish(ITestContext result){
        System.out.println("On finish method invoked");
        reports.flush(); //it is mandatory to call flush method to ensure information is written to the started reporter.
    }

    public void onTestFailure(ITestResult Result)
    {
        System.out.println("Name of test method failed:" + Result.getName() );
        test = reports.createTest(Result.getName());//create entry in html report
        test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName() ,ExtentColor.RED));

        String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + Result.getName() + ".png";

        File screenShotFile = new File(screenShotPath);

        if(screenShotFile.exists())
        {
            test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));

        }
    }

    public void onTestSkipped(ITestResult result){
        System.out.println("Name of the test method skipped: "+ result.getName());
        test = reports.createTest(result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Name of the test case skipped: "+result.getName(), ExtentColor.ORANGE));
    }

    //Triggered Before each individual @Test method is executed.
    public void onTestStart(ITestResult result){
        System.out.println("Name of the test method started: "+ result.getName());
    }

    public void onTestSuccess(ITestResult result){
        System.out.println("Name of the test method Succeeded: "+ result.getName());
        test = reports.createTest(result.getName());
        test.log(Status.PASS,MarkupHelper.createLabel("Name of the test case succeeded: "+ result.getName(),ExtentColor.GREEN));
    }

    //This method is part of the ITestListener interface and it is triggered when a test fails
    //but is still considered successful because it falls within the allowed success percentage.
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }
}
