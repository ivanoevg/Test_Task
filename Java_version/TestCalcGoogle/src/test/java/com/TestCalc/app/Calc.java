package com.TestCalc.app;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Calc {
	WebDriver driver;
	String expression = "1*2-3+1";
	String expectedCalcResult = "0";
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://google.com");
		
		
		
		Thread.sleep(000);
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Калькулятор");
		List<WebElement> findElement = driver.findElements(By.xpath("//input[@name='btnK']"));
		findElement.get(1).click();
		
		Thread.sleep(3000);
		WebElement calc = driver.findElement(By.xpath("//div[@class='jlkklc']"));
		calc.sendKeys(expression);
		calc.sendKeys(Key.ENTER);

	}

	@Test(priority = 1)
	public void CheckExpression() throws InterruptedException {
		
		
		
		String calcExpression = driver.findElement(By.xpath("//span[@class='vUGUtc']")).getText();
		calcExpression = calcExpression.replace(" ", "");
		calcExpression = calcExpression.replace("=", "");
		calcExpression = calcExpression.replace("×", "*");
		
		System.out.println("Calc expression is: " + calcExpression);
		Assert.assertEquals(expression, calcExpression);
		System.out.print("TEST 1 is finished");
		 
	}
	
	@Test(priority = 2)
	public void CheckResult() throws InterruptedException {
		
		String actualCalcResult = driver.findElement(By.id("cwos")).getText();
		System.out.println("Calc result is: " + actualCalcResult);
		Assert.assertEquals(actualCalcResult, expectedCalcResult);
		System.out.println("TEST 2 is finished");
	}
	

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
}
