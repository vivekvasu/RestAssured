package api.restassured;

import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class MasterTest {

	public String testCaseName;
	private static ExtentReports extent;
	public static ExtentTest methods;
	public static  ExtentTest parentTest;

	@BeforeSuite
	public void doInitialSetUp(ITestContext context) {
		PropertyConfigurator.configure("log4j.properties");
		extent = ExtentManager.getInstance();
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	}

	@BeforeTest
	public void beforeTest(XmlTest test) {
		Report.info("-------" + test.getName() + "-------");
		testCaseName = test.getName();
		parentTest = extent.createTest(testCaseName);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		Report.info("-------" + method.getName() + "-------");
		methods = parentTest.createNode(method.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			methods.pass("Test Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			methods.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			methods.skip(result.getThrowable());
		}
	}

	@AfterTest
	public void afterTest(XmlTest method) {
		
	}

	@AfterSuite
	public void quitBrowser() {
		extent.flush();
	}
}
