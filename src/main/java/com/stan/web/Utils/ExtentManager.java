package com.stan.web.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentManager {
    static ExtentReports extent;
    public static WebDriver driver;

    public static synchronized ExtentReports getReporter(){
        Date d=new Date();
        String strDate="_"+ d.toString().replace(":","_").replace(" ","_");
        String reportName="index.html";

        if(extent == null) {
            String path = System.getProperty("user.dir") + "/target/extent-reports/" + reportName;
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
            String branchName = "";

            htmlReporter.config().setDocumentTitle(System.getenv("Environment") + branchName);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName(System.getenv("Environment") + branchName);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setTimeStampFormat("dd/MM/yy HH:mm:ss");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

        }
        return extent;
        }
    public static synchronized String captureScreenshot(){
        File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date d=new Date();
        String strDate="_"+ d.toString().replace(":","_").replace(" ","_");
        String ScreenShotName="Screenshot"+strDate+".jpg";
        try{
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/target/extent-reports/"+ScreenShotName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return ScreenShotName;
    }

}
