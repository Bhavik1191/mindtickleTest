package com.mindtickle.course;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {


    public static WebDriver wd;
    final String reportPath = "target/extent/report.html";
    public static ExtentReports extent;
    public static ExtentTest test;
    UtilsFunctions utilsFunctions = new UtilsFunctions();
    final String inputSheetPath = "src/main/resources";
    final String fileName = "InputDataSheet.xlsx";
    final String sheetName = "Sheet1";
    String browser = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,0);
    String url = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,1);

    public BaseClass() throws IOException {
    }

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getReporter(reportPath);
    }

    @BeforeClass
    public void openBrowser()
    {

        System.out.println(browser);
        if (browser.equals("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver","Executables/chromedriver.exe" );
            wd = new ChromeDriver();
        }
        else if(browser.equals("FF"))
        {
            System.setProperty("webdriver.firefox.driver", "Executables/geckodriver.exe");
            wd= new FirefoxDriver();
        }
        else
        {
            System.out.println("Browser not found. Please add the valid browser - Chrome, Firefox");
        }
        wd.get(url);
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @BeforeMethod()
    public void beforeMethod(Method method)
    {
        test = extent.startTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String path = utilsFunctions.takeScreenshot(wd,result.getName());
        String img = test.addScreenCapture(path);

        if(ITestResult.FAILURE==result.getStatus())
        {
            test.log(LogStatus.FAIL,result.getThrowable()+img);
        }
        else if(ITestResult.SUCCESS==result.getStatus()){
            test.log(LogStatus.PASS, result.getName()+img);
        }
        extent.endTest(test);
        extent.flush();
    }



    @AfterClass
    public void tearDown()
    {
        wd.quit();
    }

    @AfterSuite
    public void closExntent()
    {
        extent.close();
    }
}
