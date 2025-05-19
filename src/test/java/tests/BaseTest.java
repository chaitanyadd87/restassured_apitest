package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import reports.ExtentManager;

public abstract class BaseTest {
    protected static ExtentReports extent;  // Singleton ExtentReports instance
    protected ExtentTest test;              // Test instance for each test case

    @BeforeAll
    public static void setupExtentReports() {
        extent = ExtentManager.getInstance(); // Initialize ExtentReports
    }

    @AfterAll
    public static void tearDownExtentReports() {
        if (extent != null) {
            extent.flush(); // Generate the HTML report
        }
    }

    // Logging reusable methods
    protected void logRequestAndResponse(String requestPayload, Response response) {
        logInfo("Request Payload: " + requestPayload);
        logInfo("Response Body: " + response.getBody().asString());
        logInfo("Response Headers: " + response.getHeaders().toString());
        logInfo("Status Code: " + response.getStatusCode());
    }
    protected void logPass(String message) {
        test.log(Status.PASS, message);
    }

    protected void logFail(String message) {
        test.log(Status.FAIL, message);
    }

    protected void logInfo(String message) {
        test.log(Status.INFO, message);
    }
    // Attach screenshot to the report
    public void attachScreenshot(String screenshotPath) {
        try {
            test.addScreenCaptureFromPath(screenshotPath);
            logInfo("Screenshot attached: " + screenshotPath);
        } catch (Exception e) {
            logFail("Failed to attach screenshot: " + e.getMessage());
        }
    }

}