package com.mindtickle.course;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);

            extent.loadConfig(new File("src/main/resources/ExtentMindTickle.xml"));
            extent
                    .addSystemInfo("Host Name", "Bhavik Shah")
                    .addSystemInfo("Environment", "Live");
        }
        return extent;
    }

}
