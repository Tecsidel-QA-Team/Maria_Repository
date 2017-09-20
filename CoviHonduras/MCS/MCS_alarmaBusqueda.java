package MCS;

import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;
import coviHondurasSettingFile.Settingsfields_File;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class MCS_alarmaBusqueda extends Settingsfields_File{
	
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
public void hostTransacciones() throws Exception {
	borrarArchivosTemp("E:\\workspace\\Maria_Repository\\MCS_alarmaBusqueda\\attachments\\");
	try{
		driver.get(MCSUrl);
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","loginMCSCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_alarmaBusqueda\\attachments\\","loginMCSCVHPage.jpg");
		String mcsVer = driver.findElement(By.id(mcsVersion)).getText();
		driver.findElement(By.id("txt_login")).sendKeys("00001");
		driver.findElement(By.id("txt_password")).sendKeys("00001");
		driver.findElement(By.id("btn_login")).click();
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","homeMCSCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_alarmaBusqueda\\attachments\\","homeMCSCVHPage.jpg");	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@onclick=\"dropdownmenu(this, event, menu3, '250px')\"]")).click();
		driver.findElement(By.linkText("Ver alarmas")).click();
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		takeScreenShot("E:\\Selenium\\","verAlarmasPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_alarmaBusquedas\\attachments\\","verAlarmasPage.jpg");
		Thread.sleep(500);
		new Select(driver.findElement(By.id("cbDia1"))).selectByVisibleText("01");
		new Select(driver.findElement(By.id("cbMes1"))).selectByVisibleText("ene");
		selectDropDown("cmb_type");
		Thread.sleep(1000);		
		driver.findElement(By.id("btn_search")).click();
		Thread.sleep(2000);
		takeScreenShot("E:\\Selenium\\","verAlarmasResults"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\MCS_alarmaBusquedas\\attachments\\","verAlarmasResults.jpg");
		Thread.sleep(1000);
		String elementsFound = driver.findElement(By.id("lbl_showing")).getText();				
		Thread.sleep(1500);
		System.out.println("Busqueda Completa: "+ elementsFound);
		System.out.println("Pruebas hechas en la versión del MCS de CoviHonduras: "+mcsVer);
		Thread.sleep(1000);					
	}catch(Exception e){
		e.printStackTrace();
		fail();
	}
}		
 
      	
}
