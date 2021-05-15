package com.pfw.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pfw.base.BaseCla;

public class ReusableMthds extends BaseCla 
{
	WebDriver wd;
	public ReusableMthds(WebDriver wd)
	{
		super();
		this.wd=wd;
	}
	
	public void fn_slctList(WebElement eleList,String strValue)
	{
		Select sc=new Select(eleList);
		sc.selectByVisibleText(strValue);
	}
}
