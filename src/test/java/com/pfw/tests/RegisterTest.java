package com.pfw.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pfw.base.BaseCla;
import com.pfw.pages.MainPage;
import com.pfw.pages.RegisterPage;
import com.pfw.utils.ExcelOper;

public class RegisterTest extends BaseCla
{
	MainPage mobj;
	ExcelOper exobj;
	RegisterPage regobj;
	
	public RegisterTest()
	{
		super();
		exobj=new ExcelOper();
	}
	
	@BeforeMethod
	public void befMthd()
	{
		initilizeBrow();
		mobj=new MainPage();		
		regobj=new RegisterPage();
	}
	
	
	@Test(dataProvider="getRegisVals",priority=0)
	public void RegisterDetails(Method m,String FN,String LN,String Address,String EmailId,String PN,String Gender,String hobbies,String Languag,String Skills,String Country,String MultCountr)
	{
		String strmail=exobj.getValue("LoginSuite",m.getName(), "Email");
		mobj.fn_entertMail(strmail);
		regobj=mobj.fn_clickButton();
			
		regobj.fn_enterFirstName(FN);
		regobj.fn_enterLastName(LN);
		regobj.fn_enterAddress(Address);
		regobj.fn_enterMailID(EmailId);
		String strPN=String.valueOf(PN);
		regobj.fn_phoneNumber(strPN);
		regobj.fn_slctGender(Gender);
		regobj.fn_slctHobbies(hobbies);
		regobj.fn_slctLanguage(Languag);
		regobj.fn_slctSkills(Skills);
		regobj.fn_slctCountry(Country);
		regobj.fn_slctMultiCountr(MultCountr);
		logger.info("Details in Register Page was displayed ase expected");
	}
	
	@DataProvider
	public Object[][] getRegisVals(Method m)
	{
		return exobj.getDataProviderVals("RegisterDetails",m.getName());
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
}
