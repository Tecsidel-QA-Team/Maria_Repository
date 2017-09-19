package CAC;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import CAC._CAC_Settingsfields_;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.*;

public class CAC_LiquidacionParcial extends _CAC_Settingsfields_ {
	
			@Before
			public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); poner esta opción cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opción cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opción cuando se vaya a subir al Git
    				driver.manage().window().maximize();	
    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}

			@After
			public void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
}

@Test
	public void accountCloseInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\");
		accountClose();
		Thread.sleep(1000);	
		System.out.println("Se ha cerrado una Liquidación Parcial correctamente");
		
	}

public static void accountClose() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidacionParcial\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónParcial\\attachments\\","homeCACCVHPage.jpg");
	CACBOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
	Thread.sleep(2000);					
	action.clickAndHold(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Liquidación parcial")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N50000_1")).sendKeys(ranNumbr(1,4)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N10000_1")).sendKeys(ranNumbr(1,4)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N5000_1")).sendKeys(ranNumbr(1,4)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N2000_1")).sendKeys(ranNumbr(1,4)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N1000_1")).sendKeys(ranNumbr(1,10)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N500_1")).sendKeys(ranNumbr(1,20)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N200_1")).sendKeys(ranNumbr(1,50)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N100_1")).sendKeys(ranNumbr(1,100)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C50_1")).sendKeys(ranNumbr(1,200)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C20_1")).sendKeys(ranNumbr(1,500)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C10_1")).sendKeys(ranNumbr(1,1000)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C5_1")).sendKeys(ranNumbr(1,2000)+"");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCH02201")).sendKeys(ranNumbr(1,5)+"");
	action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_NumberCH02202_txt_formated")),ranNumbr(10000,99999)+"").build().perform();
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberCD201")).sendKeys(ranNumbr(1,5)+"");
	action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_NumberCD202_txt_formated")),ranNumbr(10000,99999)+"").build().perform();
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_NumberBD201")).sendKeys(ranNumbr(1,5)+"");
	action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_NumberBD202_txt_formated")),ranNumbr(10000,99999)+"").build().perform();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","LiquidacionParcialPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónParcial\\attachments\\","LiquidacionParcialPage.jpg");
	elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
	Thread.sleep(500);
	driver.switchTo().alert().accept();
	Thread.sleep(6000);
	takeScreenShot("E:\\Selenium\\","LiquidacionInvoice"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónParcial\\attachments\\","LiquidacionInvoice.jpg");
	Thread.sleep(1000);
	}


}		
	




