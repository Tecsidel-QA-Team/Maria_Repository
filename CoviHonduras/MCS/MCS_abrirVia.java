package MCS;

import static org.junit.Assert.*;
import coviHondurasSettingFile.Settingsfields_File;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class MCS_abrirVia extends Settingsfields_File{
	
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
public void abrirVia() throws Exception {
	borrarArchivosTemp("E:\\workspace\\Maria_Repository\\MCS_application\\attachments\\");
	try{
		driver.get(MCSUrl);
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","loginMCSCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_application\\attachments\\","loginMCSCVHPage.jpg");
		String mcsVer = driver.findElement(By.id(mcsVersion)).getText();
		driver.findElement(By.id("txt_login")).sendKeys("00001");
		driver.findElement(By.id("txt_password")).sendKeys("00001");
		driver.findElement(By.id("btn_login")).click();
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","homeMCSCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_application\\attachments\\","homeMCSCVHPage.jpg");	
		Thread.sleep(2000);
		//driver.switchTo().frame(1);
		driver.findElement(By.id("lane_name_link_26")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='lyr_menu']/div[2]")).click();
		Thread.sleep(600);
		driver.findElement(By.linkText("Abrir la vía")).click();
		Thread.sleep(600);
		if (isAlertPresent()){
			driver.switchTo().alert().accept();
		}	
		takeScreenShot("E:\\Selenium\\","DetalleViaPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_application\\attachments\\","DetalleViaPage.jpg");
		String operationWindow = driver.findElement(By.id("titlebar")).getText();//driver.findElement(By.xpath("//*[@id='lbl_alert_title']")).getText();
		operationWindow = operationWindow.trim();
		if (operationWindow.equals("Error")){
			String errormessage = driver.findElement(By.id("lbl_message")).getText();
			System.out.println(operationWindow+": "+errormessage);
			fail(errormessage);
			return;
		}			
		Thread.sleep(1000);
		String confirmMessage = driver.findElement(By.id("lbl_message")).getText();		
		takeScreenShot("E:\\Selenium\\","abrirViaResults"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_application\\attachments\\","abrirViaResults.jpg");
		Thread.sleep(1000);		
		System.out.println(confirmMessage);		
		System.out.println("Pruebas hechas en la versión del MCS de CoviHonduras: "+mcsVer);
		Thread.sleep(1000);					
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
		fail();
	}
}	
		public static boolean isAlertPresent() throws Exception{
			try{
				driver.switchTo().alert();
				return true;
			}catch (Exception e){
				return false;
			}
		
		}
		
}
		
