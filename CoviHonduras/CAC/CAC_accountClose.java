package CAC;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import coviHondurasSettingFile.Settingsfields_File;


public class CAC_accountClose extends Settingsfields_File {
			 private static boolean accountClosed = false;
			 private static boolean NumbVehC = false;
			 private static int NumbVeh;
	
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
		if (accountClosed){
			System.out.println("No se puede cerrar la cuenta "+accountNumbr.substring(7, 16)+" porque ya está cerrada");
			fail("No se puede cerrar la cuenta "+accountNumbr.substring(7, 16)+" porque ya está cerrada");
		}
		if (NumbVehC){
			System.out.println("No se puede cerrar la cuenta "+accountNumbr.substring(7, 16)+" porque tiene "+NumbVeh+" vehículo/s asignado/s");
			fail("No se puede cerrar la cuenta "+accountNumbr.substring(7, 16)+" porque tiene "+NumbVeh+" vehículo/s asignado/s");
		}
		
		Thread.sleep(1000);
		System.out.println("Se ha cerrado la cuenta "+accountNumbr.substring(7, 16)+" correctamente");
		System.out.println("Se ha probado en la versión del CAC BO: " + BOVersion.substring(1,16)+" y CAC Manager: "+BOVersion.substring(17));
	}

public static void accountClose() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","homeCACCVHPage.jpg");
	BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
	Thread.sleep(2000);					
	action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Seleccionar cuenta")).click();
	Thread.sleep(2000);
	elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
	WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
	List<WebElement> table = tableres.findElements(By.tagName("tr"));
	int selectAccount = ranNumbr(2,table.size());
	takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","accountSearchPage.jpg");
	driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
	Thread.sleep(1000);
	accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
	takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","accountPage.jpg");
	String numberVehicles = driver.findElement(By.id("ctl00_ContentZone_lbl_vehicles")).getText();
	Thread.sleep(1000);
	if(driver.getPageSource().contains("CUENTA CERRADA")){
		accountClosed = true;
		return;
	}
	NumbVeh = Integer.parseInt(numberVehicles);
	if (NumbVeh>0){
		NumbVehC = true;
		return;
	}else{
	elementClick("ctl00_ContentZone_BtnCloseAccount");
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_txtComment")).sendKeys("Esta Cuenta se cerrará");
	takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpg");	
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","accountClosePage.jpg");
	elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
	Thread.sleep(500);
	//driver.switchTo().alert().accept();	
	Thread.sleep(1000);	
	elementClick("ctl00_ButtonsZone_BtnBack_IB_Label");
	takeScreenShot("E:\\Selenium\\","accountClosedPage"+timet+".jpg");	
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountClose\\attachments\\","accountClosedPage.jpg");
	Thread.sleep(1000);
	}
}


}		
	




