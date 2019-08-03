package com.BriteERP.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;
    //We need these objects for building reports

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;


    @BeforeTest
    public void testSetup() {
        //we are creating actual reporter
        report = new ExtentReports();
        //this is a path to the report itself
        String pathToReport = System.getProperty("user.dir") + "/test-output/report.html";

        htmlReporter = new ExtentHtmlReporter(pathToReport);

        report.attachReporter(htmlReporter);

        report.setSystemInfo("OS", System.getProperty("os.name"));
        htmlReporter.config().setDocumentTitle("VyTrack test automation");

    }

    @BeforeMethod
    public void setup() {

        driver = Driver.getDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }


    //ITestResult describes the result of a test.
    //we can determine if test failed, passed ignored
    @AfterMethod
    public void teardown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
//            //We are creating object to take a screenshot
//            TakesScreenshot screenshot = (TakesScreenshot) driver;
//            //call method to take a screenshot
//            File src = screenshot.getScreenshotAs(OutputType.FILE);
//            try {
//                //getName() - will return name of the test method
//                //and save screenshot under project > screenshots with date/time/test name tag
//                FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/screenshots/" + LocalDateTime.now() +"_"+ result.getName() + ".png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //if test failed get a screenshot and save the location to the image
            String pathToTheScreenShot = SeleniumUtils.getScreenshot(result.getName());

            extentLogger.fail(result.getName());
            try {
                //to add screenshot into report
                extentLogger.addScreenCaptureFromPath(pathToTheScreenShot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //to add thrown exception into report
            extentLogger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            //if test skip, this information will appear on the report

            extentLogger.skip("Test case skipped" + result.getName());
        }
        Driver.closeDriver();
    }

    @AfterTest
    public void teardownTest() {
        report.flush();
    }


}
}