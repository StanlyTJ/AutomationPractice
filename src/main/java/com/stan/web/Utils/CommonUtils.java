package com.stan.web.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CommonUtils {

    ExtentReports extent =ExtentManager.getReporter();
    ExtentTest test;


    public static WebDriver driver;

    String Cpath=System.getenv("ChromePath");
    String MURL=System.getenv("URL");
    String BrowserType=System.getenv("Browser");


    @Step("Launch Browser")
    public  void BrowserStart(){
        initializeBrowser(Cpath,MURL,BrowserType);
        test.info("Chrome Browser Selected");
        test.pass("Launch browser Step Passed");
    }

    public  void initializeBrowser(String path,String URL,String Browser){
        switch(Browser){
            case "CHROME":
                System.setProperty("webdriver.chrome.driver",path);
                driver=new ChromeDriver();
                driver.get(URL);
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver",path);
                driver=new ChromeDriver();
                driver.get(URL);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        test=extent.createTest("New Test");

    }
    @Step("Navigate to URL")
    public void NavigateURL(){
        test.pass("Navigate to URL step passed");

    }
    @Step("Create new user")
    public void createNewuser(){
        test.pass("Create new user step passed");

    }
    @Step("Close Browser")
    public void closeBrowser(){
        driver.quit();
        test.pass("Close Browser step passed");
        extent.flush();
    }
    @Step("hello")
    public void hello(){
        test=extent.createTest("seond test");
        test.pass("Pass");

    }

}
