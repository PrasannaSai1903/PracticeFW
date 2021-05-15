package com.pfw.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pfw.constants.Const;

public class BaseCla 
{
	public Properties objprop;
	public static WebDriver driver;
  public BaseCla()
  {
	  try
	  {
		FileInputStream fis=new FileInputStream(new File(Const.PROPERPATH));
		objprop=new Properties();
		objprop.load(fis);
	  }
	  catch(FileNotFoundException f)
	  {
		  System.out.println("Property File Not Found ");
	  }
	  catch(IOException e)
	  {
		  System.out.println("Property File can't be load ");
	  }
  }
  
  public void initilizeBrow()
  {
	  String browser=objprop.getProperty("Browser");  
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", objprop.getProperty("ChromeDriverPath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(options);			
		}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", objprop.getProperty("GeckoDriverPath"));
			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("network.negotiate-auth.delegation-uris", "http://,https://");
			ffProfile.setPreference("network.negotiate-auth.trusted-uris", "http://,https://");
			ffProfile.setPreference("network.auth.force-generic-ntlm", true);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("firefox_profile", ffProfile);
			capabilities.setCapability("marionette", true);
			FirefoxOptions ffOptions = new FirefoxOptions(capabilities);
			ffOptions.setBinary(objprop.getProperty("GeckoDriverPath"));
			driver = new FirefoxDriver(ffOptions);
		}
		else if(browser.equalsIgnoreCase("InternetExplorer"))
		{
			System.setProperty("webdriver.ie.driver", objprop.getProperty("InternetExplorerDriverPath"));
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
					UnexpectedAlertBehaviour.IGNORE);
			ieCapabilities.setJavascriptEnabled(true);
			InternetExplorerOptions ieOptions = new InternetExplorerOptions(ieCapabilities);
			driver = new InternetExplorerDriver(ieOptions);
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver", objprop.getProperty("EdgeDriverPath"));
			driver = new EdgeDriver();
		}
		
		String strApplUrl=objprop.getProperty("Url");
		driver.get(strApplUrl);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
  }
  
}
