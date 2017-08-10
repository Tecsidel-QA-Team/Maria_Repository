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

public class CAC_accountCreationWithVehicle extends _CAC_Settingsfields_ {
			 
	
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
	public void accountCreationWithVehicleInit() throws Exception {
		Thread.sleep(1000);
		
	}

public static void accountCreationWithVehicle() throws Exception {
		Thread.sleep(2000);
		elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
		Thread.sleep(2000);
		elementClick("ctl00_ContentZone_BtnVehicles");
		Thread.sleep(1000);		
		takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationVehicle\\attachments\\","vehiclePage.jpg");
		elementClick("ctl00_ContentZone_BtnCreate");
		Thread.sleep(1000);
		int matNum = ranNumbr(5000,9999);
		int matlet = ranNumbr(1,matletT.length());
		int matlet1 = ranNumbr(1,matletT.length());
		int matlet2 = ranNumbr(1,matletT.length());
		matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
		selectDropDown("ctl00_ContentZone_cmb_vehicle_type");
		Thread.sleep(1000);
		WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
		String  vehtypeT = vehtype.getText();
		if (vehtypeT.equals("Liviano")){
			carSel = ranNumbr(0,3);
			carModel = ranNumbr(1,2);
				if (cocheModels[0][carSel].equals("Seat")){
					carModelSel = 0;
				}
				if (cocheModels[0][carSel].equals("Volkswagen")){
					carModelSel = 1;
				}
				if (cocheModels[0][carSel].equals("Ford")){
					carModelSel = 2;
				}
				if (cocheModels[0][carSel].equals("Fiat")){
					carModelSel = 3;
				}
				vehtypeModel = String.valueOf(cocheModels[0][carSel]);
				vehtypeKind = String.valueOf(cocheModels[carModel][carModelSel]);	
		}
		else{
			carSel = ranNumbr(0,1);
			carModel = ranNumbr(1,2);
				if (camionModels[0][carSel].equals("Mercedes-Benz")){
					carModelSel = 0;
				}
				if (camionModels[0][carSel].equals("Scania")){
					carModelSel = 1;
				}
				vehtypeModel = String.valueOf(camionModels[0][carSel]);
				vehtypeKind = String.valueOf(camionModels[carModel][carModelSel]);
		}
		vehicleFieldsfill(matriNu,vehtypeModel,vehtypeKind,colorS[ranNumbr(0,colorS.length-1)]);
		takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\accountCreationVehicle\\attachments\\","vehicleDataFilledPage.jpg");
		Thread.sleep(2000);												
		driver.findElement(By.id("ctl00_ButtonsZone_BtnSubmit_IB_Label")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("ctl00_ButtonsZone_BtnBack_IB_Label")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("ctl00_ButtonsZone_BtnValidate_IB_Label")).click();
		Thread.sleep(2500);
	}
	public static void vehicleFieldsfill(String Matricul, String vehtypeM, String vehtypeK, String ColorT) throws Exception{
			Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ContentZone_text_plate_number")).sendKeys(Matricul);
			driver.findElement(By.id("ctl00_ContentZone_text_make")).sendKeys(vehtypeM);
			driver.findElement(By.id("ctl00_ContentZone_text_model")).sendKeys(vehtypeK);
			driver.findElement(By.id("ctl00_ContentZone_text_colour")).sendKeys(ColorT);			
}

}		
	




