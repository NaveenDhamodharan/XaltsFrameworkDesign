package XaltsAutomationFrameworkProperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNG {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}