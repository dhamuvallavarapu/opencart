package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; // UI or basic template
    public ExtentReports extent; // Common info of the report
    public ExtentTest test; // Creating test cases entries in the report
String repName;
    @Override
    public void onStart(ITestContext context) {
        try {
            String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// timestamp
            repName="Test-Report-"+timestamp+".html";

            // Initialize Spark Reporter
            sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);//specify location of path

            // Set report configurations
            sparkReporter.config().setDocumentTitle("OPENCART Automation Report"); // Title of the project
            sparkReporter.config().setReportName("OPENCART Functional Testing"); // Name of the project
            sparkReporter.config().setTheme(Theme.DARK); // Project theme

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system information to the report
            extent.setSystemInfo("Application ", "opencart");
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester Name", "Damodhar");
            extent.setSystemInfo("OS", "Windows 11");
            extent.setSystemInfo("Browser Name", "Chrome");


//            String browser = testcontext.getCurrentXmlTest().getParameter("os");
//            extent.setSystemInfo("Operating System",os);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during report setup: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName()); // Create a new entry in the report
       test.assignCategory(result.getMethod().getGroups());//to display groups in report
        test.log(Status.PASS, "Test case PASSED: " + result.getName()); // Update status
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName()); // Create a new entry in the report
        test.log(Status.FAIL, "Test case FAILED: " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.INFO, "Cause: " + result.getThrowable().getMessage());
    try{
        String imgPath=new BaseClass().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imgPath);
    }catch (IOException e1 ){
        e1.printStackTrace();
    }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
        test.log(Status.INFO, "Cause: " + result.getThrowable().getMessage());

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Write the report to the file
        System.out.println("Report generation completed!");
        String pathOfExtentReport = System.getProperty(("user.dir")+"\\reports\\"+repName);
        File extentReport = new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
