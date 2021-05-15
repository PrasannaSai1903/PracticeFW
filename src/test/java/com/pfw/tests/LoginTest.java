package com.pfw.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pfw.base.BaseCla;
import com.pfw.pages.MainPage;
import com.pfw.pages.RegisterPage;
import com.pfw.utils.ExcelOper;

public class LoginTest extends BaseCla
{
	MainPage mobj;
	ExcelOper exobj;
	RegisterPage regobj;
	
	public LoginTest()
	{
		super();
		
	}
	
	@BeforeMethod
	public void befMthd()
	{
		initilizeBrow();
		mobj=new MainPage();
		exobj=new ExcelOper();
		regobj=new RegisterPage();
	}
	
	@Test
	public void EnterAccountAndClick(Method m)
	{
		String strmail=exobj.getValue("LoginSuite",m.getName(), "Email");
		mobj.fn_entertMail(strmail);
		regobj=mobj.fn_clickButton();
	}
	
	@AfterMethod
	public void aftMthd()
	{
		driver.close();
		driver.quit();
	}
}
