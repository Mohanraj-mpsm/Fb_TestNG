package com.fb;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FbAccountCreation {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
		
		@BeforeClass
		public static void launch() {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
			
		
		@AfterClass (enabled = false) 
		public static void close() {
			driver.quit();
		}
		
		@DataProvider(name="fullname")
		public Object [][] naming(){
			return new Object[][] {{"moh","mee"}};
		}

		@Test (priority=1)
		public void maximizetheWindow() {		
			driver.manage().window().maximize();		
		}
		
		
		@Test (priority=2)
		public void launchURL() {
			driver.get("https://www.facebook.com");
		}
		
		@Test (priority=3)
		public void signupButton() {
			wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			WebElement createAcc = driver.findElement(By.xpath("//a[contains(text(),'Create new account')]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Create new account')]")));
			createAcc.click();
		}
		
		@Test (priority=4 , dataProvider = "fullname")
		public void namingDetail(String first , String last) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
			WebElement fname = driver.findElement(By.xpath("//input[@name='firstname']"));
			fname.click();
			fname.sendKeys(first);
			
			WebElement sname = driver.findElement(By.xpath("//input[@name='lastname']"));
			sname.click();
			sname.sendKeys(last);
		
		}

	
		@Test (priority=5)
		public void mobNumber() {
			WebElement mob = driver.findElement(By.xpath("//input[@name='reg_email__']"));
			mob.click();
			mob.sendKeys("9526781526");
		
		}
		
		@Test (priority=6)
		public void passCode() {
			WebElement pass = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
			pass.click();
			pass.sendKeys("MeeMoh#1521");
			
		}
		
		@Test (priority=7)
		public void dateofBirth() {
			Select year = new Select(driver.findElement(By.id("year")));
			year.selectByVisibleText("1996");
		
		}
		
		@Test (priority=8)
		public void submit() {
			WebElement gen = driver.findElement(By.xpath("(//input[@type='radio'])[2]"));
			gen.click();
			
			WebElement signup = driver.findElement(By.xpath("//button[@name='websubmit']"));
			signup.click();
		
		}


}
