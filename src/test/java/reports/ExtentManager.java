package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Use ExtentSparkReporter for generating HTML reports
        	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReports.html");
        	sparkReporter.config().setDocumentTitle("Enhanced API Test Report");
        	sparkReporter.config().setReportName("Login API Test Results");
        	sparkReporter.config().setTheme(Theme.DARK); // Change to DARK theme
            sparkReporter.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}