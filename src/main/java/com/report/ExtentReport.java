package com.report;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	private ExtentHtmlReporter htmlreporter;

	protected ExtentReports extent;
	protected ExtentTest test;

	@BeforeTest
	public void before() {
		htmlreporter = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "./target/ExtendsTestNGReport.html"));
		htmlreporter.loadXMLConfig(new File(System.getProperty("user.dir") + "./extent-config.xml"), true);
		htmlreporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.setSystemInfo("Testing environment ", "QA");
		extent.setSystemInfo("User Name ", "QA");
		extent.setSystemInfo("user", System.getProperty("user.name"));
		extent.attachReporter(htmlreporter);
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		String testName = method.getName();
		test = extent.createTest(testName);
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("1.png").build());
		
	}

	@AfterMethod
	public void aftermethod(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(MarkupHelper.createLabel(result.getName() + "Test Failed", ExtentColor.RED));
			test.log(Status.FAIL, "The test status::" + result.getStatus() + ":: Test FAIL");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + "Test Passed", ExtentColor.GREEN));
			test.log(Status.PASS, "The test status::" + result.getStatus() + ":: Test Passed");

		} else {
			test.skip(MarkupHelper.createLabel(result.getName() + "Test Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
			test.log(Status.SKIP, "The test status::" + result.getStatus() + ":: Test SKIP");

		}

	}

	@AfterTest
	public void sfterSuite() {
		extent.flush();

	}

}
