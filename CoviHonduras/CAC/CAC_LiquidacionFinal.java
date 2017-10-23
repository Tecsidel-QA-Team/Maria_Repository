package CAC;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import coviHondurasSettingFile.Settingsfields_File;

public class CAC_LiquidacionFinal extends Settingsfields_File {
	
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
	public void accountLiquidacionParcialInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\LiquidacionFinal\\attachments\\");
		accountLiquidacionFinal();
		Thread.sleep(1000);	
		System.out.println("Se ha cerrado una Liquidación Final correctamente");
		
	}

public static void accountLiquidacionFinal() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidacionFinal\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónFinal\\attachments\\","homeCACCVHPage.jpg");
	BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
	Thread.sleep(2000);					
	action.clickAndHold(driver.findElement(By.linkText("Gestión de cobrador"))).build().perform();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Liquidación final")).click();
	Thread.sleep(2000);
	selectDropDown("ctl00_ContentZone_cmb_shiftGroup_cmb_dropdown");
	Thread.sleep(500);
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
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónFinal\\attachments\\","LiquidacionParcialPage.jpg");
	elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
	Thread.sleep(500);
	if (isAlertPresent()){
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
	}
	if (isAlertPresent()){
		driver.switchTo().alert().accept();
		Thread.sleep(12000);
	}
	takeScreenShot("E:\\Selenium\\","LiquidacionInvoice"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\LiquidaciónFinal\\attachments\\","LiquidacionInvoice.jpg");
	Thread.sleep(1000);
	}

	public static boolean isAlertPresent(){
		driver.switchTo().alert();
		return true;
	}

}		
	




