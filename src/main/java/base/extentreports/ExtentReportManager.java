package base.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports getExtentReports(){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "reports/extentreports/extentreport_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Extent Report | Nhi Tester");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework", "Selenium Java");
        extentReports.setSystemInfo("Author", "Nhi Tester");

        return extentReports;
    }
}
