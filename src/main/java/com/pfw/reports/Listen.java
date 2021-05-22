package com.pfw.reports;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mongodb.annotations.ThreadSafe;
import com.pfw.base.BaseCla;

import java.awt.event.*;
import java.io.File;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listen extends BaseCla implements ITestListener
{
	ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
		
	public void onStart(ITestContext context)
	{
		ExtendManager ext=new ExtendManager();
		extent=ext.getExtentInstance();	
	}

	public void onTestStart(ITestResult result) 
	{
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().indexOf(".") + 1);
		logger = extent.createTest(className + "_" + result.getMethod().getMethodName(), result.getMethod().getDescription());
		//logger=extent.createTest(result.getMethod().getMethodName());
		test.set(logger);		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Markup mark=MarkupHelper.createLabel(result.getMethod().getMethodName()+" "+"PASS", ExtentColor.GREEN);
		test.get().pass("Passed");
		test.get().pass(mark);		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail(
				"<details><summary><b><font color = red>Exception is thrown! Click here to see</font></b></summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>\n");

		String failureLog = "<b>TEST CASE: " + result.getMethod().getMethodName() + " FAILED</b>";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		test.get().fail(m);
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().skip(
				"<details><summary><b><font color = red>Exception is thrown! Click here to see</font></b></summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
		String failureLog = "<b>TEST CASE: " + result.getMethod().getMethodName() + " SKIPPED</b>";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.BLUE);
		test.get().skip(m);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
