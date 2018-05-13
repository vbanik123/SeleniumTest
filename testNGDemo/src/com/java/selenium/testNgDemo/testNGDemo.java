package com.java.selenium.testNgDemo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class testNGDemo {
	
	public int frMmonTHVal;
	public int frMdTvAL;
	public int reTrnmonTHVal;
	public int reTrndTvAL;
	public String loginName;
	public String psWWrd;
	public int cNtr = 0;
	public WebDriver driver;
	
	@Test (dataProvider = "testData", groups = {"loginData"}, priority = 1)
	public void Addition(String val1, String val2){
		loginName = val1;
		psWWrd = val2;
		System.out.println(loginName);
		System.out.println(psWWrd);
	}	
	
	
	@DataProvider (name = "testData")
	public Object [][] readExcel() throws BiffException, IOException {
		// TODO Auto-generated method stub
		String inputData[][] = null;
		try{
			System.out.println("Excel Parse");
			File f = new File("C:/Users/user/Desktop/testData_TestNG.xls");
			Workbook w = Workbook.getWorkbook(f);
			Sheet s = w.getSheet(0);
			int rows = s.getRows();
			int cols = s.getColumns();
			inputData = new String [rows][cols];
			for(int i = 0; i<rows; i++){
				for(int j=0; j<cols; j++){
					Cell c = s.getCell(j, i);
					inputData [i][j] = c.getContents();
					//System.out.println(inputData [i][j]);
				}
			}
			//return inputData;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return inputData;
	}
	
	@Test(groups = {"login"}, priority = 2)
	
	public void loginPortal()
	{
		try
		{
			driver = new FirefoxDriver();
			driver.get("http://newtours.demoaut.com/");
			driver.manage().window().maximize();
			driver.findElement(By.name("userName")).sendKeys(loginName);
			driver.findElement(By.name("password")).sendKeys(psWWrd);
			driver.findElement(By.name("login")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Login Successfull...");
			driver.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
//	@DataProvider(name="data")
//	public Object[][] getData(){
//		Object[][] obJ = new Object[2][2];
//		
//		obJ[0][0] = 8;
//		obJ[0][1] = 25;
//		
//		obJ[1][0] = 10;
//		obJ[1][1] = 12;
//		
//		return obJ;
//	}
//	@Test(dataProvider="data")
//	public void getBookinDT(int moNth, int dT)
//	{
//		cNtr = cNtr + 1;
//		if(cNtr == 1){
//			frMmonTHVal = moNth;
//			frMdTvAL = dT;
//		}else if (cNtr == 2) {
//			reTrnmonTHVal = moNth;
//			reTrndTvAL = dT;
//		}
//		
//	}
//	@Test
//	public void printResult()
//	{
//		System.out.println(frMmonTHVal);
//		System.out.println(frMdTvAL);
//		System.out.println(reTrnmonTHVal);
//		System.out.println(frMmonTHVal);
//	}
	
	@BeforeTest(dependsOnMethods = "Addition")
	public void Login()
	{
		System.out.println("Login Successfull...");
	}
	
	@AfterTest
	public void Logout()
	{
		System.out.println("Logout....");
	}
}
