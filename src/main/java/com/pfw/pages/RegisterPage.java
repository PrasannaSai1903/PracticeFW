package com.pfw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pfw.base.BaseCla;
import com.pfw.utils.ReusableMthds;

public class RegisterPage extends BaseCla
{
	ReusableMthds reusMthObj;
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement eleFirstName;
	
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement eleLastName;
	
	
	@FindBy(xpath="//textarea[@ng-model='Adress']")
	WebElement eleAddress;
	
	@FindBy(xpath="//input[@ng-model='EmailAdress']")
	WebElement eleEmailAd;
	
	@FindBy(xpath="//input[@ng-model='Phone']")
	WebElement elePhoneNum;
	
	@FindBy(xpath="//input[@value='Male']")
	WebElement eleMale;
	
	@FindBy(xpath="//input[@value='FeMale']")
	WebElement eleFemale;
	
	@FindBy(xpath="//input[@id='checkbox1']")
	WebElement eleCricket;
	
	@FindBy(xpath="//input[@id='checkbox2']")
	WebElement eleMovies;
	
	@FindBy(xpath="//input[@id='checkbox3']")
	WebElement eleHockey;
	
	@FindBy(id="msdd")
	WebElement eleLanguage;
	
	@FindBy(xpath="//select[@id='Skills']")
	WebElement eleSkills;
	
	@FindBy(xpath="//select[@id='countries']")
	WebElement eleCountries;
	
	@FindBy(xpath="//select[@id='country']")
	WebElement eleSlctCountries;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement eleSearch;
	
	//input[@type='search']
	public RegisterPage()
	{
		PageFactory.initElements(driver,this);
		reusMthObj=new ReusableMthds(driver);
	}
	
	public void fn_enterFirstName(String strFirstName)
	{
		eleFirstName.sendKeys(strFirstName);
	}
	
	public void fn_enterLastName(String strLastName)
	{
		eleLastName.sendKeys(strLastName);
	}
	
	public void fn_enterAddress(String strAddress)
	{
		eleAddress.sendKeys(strAddress);
	}
	
	public void fn_enterMailID(String strEmailID)
	{
		eleEmailAd.sendKeys(strEmailID);
	}
	
	public void fn_phoneNumber(String strPhoneNumber)
	{
		elePhoneNum.sendKeys(strPhoneNumber);		
	}
	
	public void fn_slctGender(String Gender)
	{
		if(Gender.toUpperCase().equals("MALE"))
		{
			eleMale.click();
		}
		else if(Gender.toUpperCase().equals("FEMALE"))
		{
			eleFemale.click();
		}
	}
	
	public void fn_slctHobbies(String strHobbies)
	{
		if(strHobbies.toUpperCase().equals("CRICKET"))
		{
			eleCricket.click();
		}
		else if(strHobbies.toUpperCase().equals("MOVIES"))
		{
			eleMovies.click();
		}
		else if(strHobbies.toUpperCase().equals("HOCKEY"))
		{
			eleHockey.click();
		}
	}
	
	public void fn_slctLanguage(String strLanguage)
	{
		eleLanguage.click();
		driver.findElement(By.xpath("//li[@class='ng-scope']//a[text()='"+strLanguage+"']")).click();
	}
	
	public void fn_slctSkills(String strSkiills)
	{
		reusMthObj.fn_slctList(eleSkills,strSkiills);
	}
	
	public void fn_slctCountry(String strCountry)
	{
		reusMthObj.fn_slctList(eleCountries,strCountry);
	}
	
	public void fn_slctMultiCountr(String strCountries)
	{
		String str[]=strCountries.split("%%");
		for(int i=0;i<str.length;i++)
		{
			eleSlctCountries.click();
			try {
			Thread.sleep(3000);}
			catch(Exception e)
			{e.printStackTrace();}
			eleSearch.sendKeys(str[i]);
		}
	}
	
	
}
