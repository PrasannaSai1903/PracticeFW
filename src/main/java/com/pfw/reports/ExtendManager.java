package com.pfw.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pfw.base.BaseCla;

public class ExtendManager extends BaseCla
{
	private static ExtentHtmlReporter htmlReport;
	
	public ExtentReports getExtentInstance()
	{
		htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"Report"+File.separator+getReportHTMLName()+".html");
		htmlReport.config().setDocumentTitle(objprop.getProperty("DocumentTitle"));
		htmlReport.config().setReportName(objprop.getProperty("ReportName"));
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Tester",objprop.getProperty("Tester"));
		extent.setReportUsesManualConfiguration(true);
		return extent;
	}
	
	public static String getReportHTMLName()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date d=new Date();
		String time=sdf.format(d).replace("/", "-").replace(" ", "_").replace(":","-");
		return "Report_"+time;
		
	}
	
}
