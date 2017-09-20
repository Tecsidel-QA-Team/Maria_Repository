package HostPlazaBackOffice;

import static org.junit.Assert.*;
import coviHondurasSettingFile.Settingsfields_File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class BOHost_operatorCreation extends Settingsfields_File {
			 private static String lastcreated ;
			 private static WebElement tableResult;
			 private static List<WebElement> userResults;
			 
	
			@Before
			public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); poner esta opci�n cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opci�n cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opci�n cuando se vaya a subir al Git
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
	borrarArchivosTemp("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\");
	try{
		driver.get(BoHostUrl);
		takeScreenShot("E:\\Selenium\\","loginBOCVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\","loginBOCVHPage.jpg");
		driver.findElement(By.id(loginField)).sendKeys("00001");
		driver.findElement(By.id(passField)).sendKeys("00001");
		driver.findElement(By.id(loginButton)).click();
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","homeBOVHPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\","homeBOVHPage.jpg");
		BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
		Thread.sleep(2000);					
		action.clickAndHold(driver.findElement(By.linkText("Configuraci�n sistema"))).build().perform();
		Thread.sleep(1000);
		action.moveToElement(driver.findElement(By.linkText("Configuraci�n de peaje")));
		action.clickAndHold(driver.findElement(By.linkText("Operadores"))).build().perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Gesti�n de operadores")).click();								
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\","gestionOperadoresPage.jpg");
		Thread.sleep(500);		
		elementClick("ctl00_ContentZone_BtnCreate");
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\","crearOperadoresPage.jpg");
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
		driver.findElement(By.id("ctl00_ContentZone_txt_country_box_data")).sendKeys("Espa�a");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_email_box_data")).sendKeys(nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es@");
		driver.findElement(By.id("ctl00_ContentZone_txt_phone_box_data")).sendKeys(workPhone1[userSel]);
		selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
		Thread.sleep(100);
		selectDropDown("ctl00_ContentZone_cmb_typeDoc_cmb_dropdown");
		Thread.sleep(1000);
		WebElement Docselected = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
		String DocSelectedText = Docselected.getText();
		if (DocSelectedText.equals("TI")){
			driver.findElement(By.id("ctl00_ContentZone_txt_numberDoc_box_data")).sendKeys(String.valueOf(ranNumbr(1000000,90000000)+String.valueOf(ranNumbr(1000000,9000000))));
		}else{
			driver.findElement(By.id("ctl00_ContentZone_txt_numberDoc_box_data")).sendKeys(String.valueOf(ranNumbr(10000000,900000000)+String.valueOf(ranNumbr(1000000,9000000))));
		}
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
		tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		userResults = tableResult.findElements(By.tagName("tr"));
		if (userResults.size()>15){
			elementClick("ctl00_ContentZone_tablePager_BtnLast");
			Thread.sleep(500);
			tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
			userResults = tableResult.findElements(By.tagName("tr"));
		}
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
		takeScreenShot("E:\\workspace\\Maria_Repository\\BOHost_crearOperadores\\attachments\\","userCreatedscreenHome.jpg");		
		System.out.println("Se ha Creado el operador "+lastcreated+" con la contrasea�a: 00001"+ " en el grupo de "+operatorG.substring(04));
		System.out.println("Se ha probado en la versi�n del BO Host: " + BOVersion.substring(1,16)+" y Host Manager: "+BOVersion.substring(17));
	}catch(Exception e){
		e.printStackTrace();
		fail();
	}
}		
	}



