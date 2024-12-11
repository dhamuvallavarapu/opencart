package withoutFrameworkProgramms;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; // UI or basic template
    public ExtentReports extent; // Common info of the report
    public ExtentTest test; // Creating test cases entries in the report

    @Override
    public void onStart(ITestContext context) {
        try {
            // Ensure reports directory exists
            File reportDir = new File(System.getProperty("user.dir") + File.separator + "reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs(); // Creates the reports folder if it does not exist
            }

            // Initialize Spark Reporter
            String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "myReport.html";
            sparkReporter = new ExtentSparkReporter(reportPath);

            // Set report configurations
            sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the project
            sparkReporter.config().setReportName("Functional Testing"); // Name of the project
            sparkReporter.config().setTheme(Theme.DARK); // Project theme

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system information to the report
            extent.setSystemInfo("Computer Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester Name", "Damodhar");
            extent.setSystemInfo("OS", "Windows 11");
            extent.setSystemInfo("Browser Name", "Chrome");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during report setup: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.PASS, "Test case PASSED: " + result.getName()); // Update status
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.FAIL, "Test case FAILED: " + result.getName());
        test.log(Status.FAIL, "Cause: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new entry in the report
        test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Write the report to the file
        System.out.println("Report generation completed!");
    }
}
