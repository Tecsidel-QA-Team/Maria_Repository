package CAC;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import coviHondurasSettingFile.Settingsfields_File;

public class CAC_accountCreationAssigningTag extends Settingsfields_File {	 
	
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
	public void accountCreationAssigningTagInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\");
		CAC.CAC_accountCreationOnly.accountCreation();
		Thread.sleep(200);
		CAC.CAC_accountCreationWithVehicle.accountCreationWithVehicle();
		Thread.sleep(500);
		accountCreationAssigningTag();
		Thread.sleep(1000);
		if (errorTagAssignment){
			System.out.println("ERROR AL ASIGNAR TAG a la cuenta: "+accountNumbr.substring(7, 16)+", "+confirmationMessage);
			fail("Tag Invalido: No se puede asignar un Tag al Vehiculo "+matriNu+" de la cuenta "+accountNumbr.substring(7, 16));
			return;
		}
		System.out.println("Se ha creado la cuenta: "+accountNumbr.substring(7, 16)+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
		Thread.sleep(3000);
		System.out.println("Se ha probado en la versión del CAC BO: " + getVersion("BO")+" y CAC Manager: "+getVersion("HM"));
	}

public static void accountCreationAssigningTag() throws Exception {
		Thread.sleep(2000);
		takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentMainPage.jpg");
		driver.findElement(By.id("ctl00_ContentZone_BtnTags")).click();
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_chk_0")).click();
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_btn_token_assignment")).click();
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_txt_pan_token_txt_token_box_data")).sendKeys(ranNumbr(1,99999)+"");
		Thread.sleep(500);
		driver.findElement(By.id("ctl00_ContentZone_btn_init_tag")).click();
		Thread.sleep(500);
		confirmationMessage = driver.findElement(By.id("ctl00_ContentZone_lbl_operation")).getText();
			if (confirmationMessage.contains("ya tiene un tag asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado") || confirmationMessage.contains("Luhn incorrecto")){
				errorTagAssignment = true;
				takeScreenShot("E:\\Selenium\\","tagAssignmentPageErr"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentPageErr.jpg");
				
			}else{
				tagIdNmbr = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]")).getText();
				takeScreenShot("E:\\Selenium\\","tagAssignmentPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationAssigningTag\\attachments\\","tagAssignmentPage.jpg");
			}
		Thread.sleep(1000);
	
	}


}		
	




