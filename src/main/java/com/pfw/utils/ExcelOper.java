package com.pfw.utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pfw.base.BaseCla;
import com.pfw.constants.Const;


public class ExcelOper extends BaseCla
{
	public ExcelOper()
	{
		super();
	}
	
	public String getValue(String wbSheet,String strTCName,String wbColName)
	{
		XSSFWorkbook bk = null;
		int tcRowNum=0;
		String finalVal="";
		try
		{
			bk=new XSSFWorkbook(Const.EXCELPATH);
		}
		catch(Exception e)
		{
			System.out.println("Excel was not present for the TestCase: "+strTCName);
		}
		
		XSSFSheet sh=bk.getSheet(wbSheet);
		
		int rc=sh.getLastRowNum();
		for(int i=0;i<=rc;i++)
		{
			Row objrow=sh.getRow(i);
			Cell objcell=objrow.getCell(0);
			if(objcell.getCellType()==CellType.STRING)
			{
				String strVal="";
				strVal=objcell.getStringCellValue();
				if(strVal.equalsIgnoreCase(strTCName))
				{
					tcRowNum=i;
					break;
				}
			}
		}
		
		if(tcRowNum>0)
		{
			int Colno=0;
			//Veryfing Column Name
			Row objrc=sh.getRow(0);
			for(int j=0;j<=objrc.getLastCellNum();j++)
			{
				Cell objcl=objrc.getCell(j);
				String strClNameVal=objcl.getStringCellValue();
				if(strClNameVal.equalsIgnoreCase(wbColName))
				{
					Colno=j;
					break;
				}
			}
			
			Row finrow=sh.getRow(tcRowNum);
			Cell fincel=finrow.getCell(Colno);
			if(fincel.getCellType()==CellType.STRING)
			{
				finalVal=fincel.getStringCellValue();
			}
			else if(fincel.getCellType()==CellType.NUMERIC)
			{
				finalVal=String.valueOf(fincel.getNumericCellValue());
			}
		}
		else
		{
			System.out.println("Data was not present in Excel for TestCase: "+strTCName);
		}
		return finalVal;	
	}
	
	public Object[][] getDataProviderVals(String sheetName,String strTCName) 
	{
		
		System.out.println("ENetered");
		Object[][] data;
		XSSFWorkbook bk = null;
		int tcRowNum=0;
		String finalVal="";
		try
		{
			bk=new XSSFWorkbook(Const.EXCELPATH);
		}
		catch(Exception e)
		{
			System.out.println("Excel was not present for the TestCase: "+strTCName);
		}
		
		XSSFSheet sh=bk.getSheet(sheetName);
		
		int rc=sh.getLastRowNum();
		Row objrc=sh.getRow(0);
		int cc=objrc.getLastCellNum();
		data=new Object[rc][cc];
		
		for(int i=1;i<=rc;i++)		
		{
			Row rc1=sh.getRow(i);
			for(int j=0;j<rc1.getLastCellNum();j++)
			{
				System.out.println(j);
				if(rc1.getCell(j).getCellType()==CellType.STRING)
				{
					data[i-1][j]=rc1.getCell(j).getStringCellValue();
					System.out.println(rc1.getCell(j).getStringCellValue());
				}
				else if(rc1.getCell(j).getCellType()==CellType.NUMERIC)
				{
					data[i-1][j]=rc1.getCell(j).getNumericCellValue();
					System.out.println(rc1.getCell(j).getNumericCellValue());
				}
				
			}
		}
		try {
		bk.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return data;
	}
}
