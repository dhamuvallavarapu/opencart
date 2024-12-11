package withoutFrameworkProgramms;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

public class MyListener implements ITestListener {

    public void onStart(ITestContext context) {
        System.out.println(" This is onStartMethod and this is executed only once before test case got executed ");
    }

    public void onTestStart(ITestResult result) {
        System.out.println(" This is onTestStart and this is executed everytime test method executes");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println(" This is onTestSuccess and this is executed when the method method got PASSED ");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println(" This is onTestFailure and this is executed when the method method got FAILED ");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("This is onTestSkipped and this is executed when the method method got SKIPPED ");
    }

    public void onFinish(ITestContext context) {
        System.out.println(" This is onFinish and this is executed only once after test case got executed ");

    }
}

