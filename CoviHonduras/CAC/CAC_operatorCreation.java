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

public class CAC_operatorCreation extends _CAC_Settingsfields_ {
			 private static String lastcreated ;
	
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
public void crearOperadores() throws Exception {
	Actions action = new Actions(driver);
	borrarArchivosTemp("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\");
	try{
		driver.get(CaCUrl);
		takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","loginCACCVHPage.jpg");
		driver.findElement(By.id(loginField)).sendKeys("00001");
		driver.findElement(By.id(passField)).sendKeys("00001");
		driver.findElement(By.id(loginButton)).click();
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","homeCACCVHPage.jpg");
		CACBOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
		
		Thread.sleep(2000);					
		action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
		Thread.sleep(1000);
		action.moveToElement(driver.findElement(By.linkText("Parámetros de cuenta")));
		action.clickAndHold(driver.findElement(By.linkText("Operadores"))).build().perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Gestión de operadores")).click();								
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","gestionOperadoresPage.jpg");
		Thread.sleep(500);		
		elementClick("ctl00_ContentZone_BtnCreate");
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","crearOperadoresPage.jpg");
		int userSel = ranNumbr(0,nameOp.length-1);
		new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelc[userSel]);
		Thread.sleep(100);
		new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderS[userSel]);
		driver.findElement(By.id("ctl00_ContentZone_forename_box_data")).sendKeys(nameOp[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_surname_box_data")).sendKeys(lastNameOp[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_address_box_data")).sendKeys(addressTec[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_postcode_box_data")).sendKeys(cpAdress[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_city_box_data")).sendKeys(townC[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_country_box_data")).sendKeys("España");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_email_box_data")).sendKeys(nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es@");
		driver.findElement(By.id("ctl00_ContentZone_txt_phone_box_data")).sendKeys(workPhone1[userSel]);
		selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
		Thread.sleep(1000);
		WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
		String operatorG = operatorGroup.getText();		
		dateSel();
		driver.findElement(By.id("ctl00_ContentZone_dt_birth_box_date")).clear();
		driver.findElement(By.id("ctl00_ContentZone_dt_birth_box_date")).sendKeys(sft.format(calT.getTime()));
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_ContentZone_password_box_data")).sendKeys("00001");
		driver.findElement(By.id("ctl00_ContentZone_password2_box_data")).sendKeys("00001");
		Thread.sleep(5000);
		takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","allfilleddata.jpg");
		elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
		Thread.sleep(2000);
		takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","userCreated.jpg");
		WebElement tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		List<WebElement> userResults = tableResult.findElements(By.tagName("tr"));
		for (int i = 1; i <= userResults.size(); i++){
			if (i == userResults.size()){
				lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]")).getText();
			}	
		}
		elementClick("ctl00_BtnLogOut");
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.id(loginField)).sendKeys(lastcreated);
		driver.findElement(By.id(passField)).sendKeys("00001");
		driver.findElement(By.id(loginButton)).click();
		Thread.sleep(2000);
		takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_crearOperadores\\attachments\\","userCreatedscreenHome.jpg");		
		System.out.println("Se ha Creado el "+lastcreated+" con la contraseaña: 00001"+ " en el grupo de "+operatorG.substring(04));
		System.out.println("Se ha probado en la versión del CAC BO: " + CACBOVersion.substring(1,16)+" y CAC Manager: "+CACBOVersion.substring(17));
	}catch(Exception e){
		e.printStackTrace();
		fail();
	}
}		
	}




