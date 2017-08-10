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

public class CAC_assignTagToExistingAccount extends _CAC_Settingsfields_ {
			 private static boolean NumbVehC = false;
			 private static boolean TagAssigned = false;
			 private static boolean accountClosed = false;
	
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
	public void assignTagToExistingAccountInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\assigningTagToAccount\\attachments\\");
		assignTagToExistingAccount();
		Thread.sleep(1000);
		
		
		if (accountClosed){
			System.out.println("No se puede asignar un Tag a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
			fail("No se puede asignar un Tag a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
		}
		if (NumbVehC){
			System.out.println("No se puede asignar un Tag a la cuenta "+accountNumbr.substring(7, 16)+" porque no hay vehículo asociado a la cuenta");
			fail("No se puede asignar un Tag a la cuenta "+accountNumbr.substring(7, 16)+" porque no hay vehículo asociado a la cuenta");
		}
		if (TagAssigned){
				System.out.println("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr.substring(7, 16)+", "+confirmationMessage);
				fail("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr.substring(7, 16)+", "+confirmationMessage);
		}
		Thread.sleep(1000);
		System.out.println("Se le asignado el el tag No."+tagIdNmbr+" a la cuenta "+accountNumbr.substring(7, 16)+" correctamente");
		System.out.println("Se ha probado en la versión del CAC BO: " + CACBOVersion.substring(1,16)+" y CAC Manager: "+CACBOVersion.substring(17));
	}

public static void assignTagToExistingAccount() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\assigningTagToAccount\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\assigningTagToAccount\\attachments\\","homeCACCVHPage.jpg");
	CACBOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
	Thread.sleep(2000);					
	action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Seleccionar cuenta")).click();
	Thread.sleep(2000);
	elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
	WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
	List<WebElement> table = tableres.findElements(By.tagName("tr"));
	int selectAccount = ranNumbr(2,table.size());
	driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
	Thread.sleep(1000);
	accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
	Thread.sleep(1000);
	if(driver.getPageSource().contains("CUENTA CERRADA")){
		accountClosed = true;
		return;
	}
	String numberVehicles = driver.findElement(By.id("ctl00_ContentZone_lbl_vehicles")).getText();
	int NumbVeh = Integer.parseInt(numberVehicles);
	if (NumbVeh==0){
		NumbVehC = true;
		return;
	}else{
		driver.findElement(By.id("ctl00_ContentZone_BtnTags")).click();
		takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentMainPage.jpg");
		Thread.sleep(1000);
		int vehCheck = ranNumbr(0,NumbVeh-1);
		driver.findElement(By.id("ctl00_ContentZone_chk_"+vehCheck)).click();
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_btn_token_assignment")).click();
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_txt_pan_token_txt_token_box_data")).sendKeys(ranNumbr(1,99999)+"");
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_btn_init_tag")).click();
		Thread.sleep(1500);
		confirmationMessage = driver.findElement(By.id("ctl00_ContentZone_lbl_information")).getText();
			if (confirmationMessage.contains("ya tiene un título asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
				TagAssigned = true;
				takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentPageErr.jpg");
				return;
			}else{
				int tabVeh = vehCheck+2;
				tagIdNmbr = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr["+tabVeh+"]/td[6]")).getText();
				takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentPage.jpg");
			}
		
	}
	
	
	
	
}


}		
	




