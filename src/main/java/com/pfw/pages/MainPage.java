package com.pfw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pfw.base.BaseCla;

public class MainPage extends BaseCla
{
	@FindBy(id="email")
	WebElement eleEmailID;
	
	@FindBy(id="enterimg")
	WebElement eleButton;
	
	public MainPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public void fn_entertMail(String strMailID)
	{
		eleEmailID.sendKeys(strMailID);
	}
	
	public RegisterPage fn_clickButton()
	{
		eleButton.click();
		System.out.println("Main Page");
		return new RegisterPage();
	}
}
