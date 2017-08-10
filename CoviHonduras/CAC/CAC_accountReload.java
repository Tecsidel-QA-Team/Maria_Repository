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

public class CAC_accountReload extends _CAC_Settingsfields_ {
			 private static boolean accountClosed = false;
			 private static String Saldo;
			 
	
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
	public void accountReloadInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\");
		accountReload();
		Thread.sleep(1000);
		if (accountClosed){
			System.out.println("No se puede hacer Recarga a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
			fail("No se puede hacer Recarga a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
		}
				
		Thread.sleep(1000);
		System.out.println("Se Recargado la cuenta "+accountNumbr.substring(7, 16)+" correctamente y posee un saldo de: "+Saldo);
		System.out.println("Se ha probado en la versión del CAC BO: " + CACBOVersion.substring(1,16)+" y CAC Manager: "+CACBOVersion.substring(17));
	}

public static void accountReload() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","homeCACCVHPage.jpg");
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
	takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","accountSearchPage.jpg");
	driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
	Thread.sleep(1000);
	accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
	takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","accountPage.jpg");
	Thread.sleep(1000);
	if(driver.getPageSource().contains("CUENTA CERRADA")){
		accountClosed = true;
		return;
	}	
	elementClick("ctl00_ContentZone_BtnLoads");
	Thread.sleep(1000);	
	int optionclick = ranNumbr(0,3);
	elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+optionclick);
	int optionclick1 = ranNumbr(0,1);
	if (optionclick1==1){
		elementClick("ctl00_ContentZone_CtType_chk_present");
	}
	Thread.sleep(1000);
	driver.findElement(By.id("ctl00_ContentZone_CtType_text_total_txt_formated")).click();
	driver.findElement(By.id("ctl00_ContentZone_CtType_text_total_txt_formated")).sendKeys(ranNumbr(100000,900000)+"");
	Thread.sleep(500);
	takeScreenShot("E:\\Selenium\\","accountReloadPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","accountReloadPage.jpg");
	elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
	Thread.sleep(3000);
	takeScreenShot("E:\\Selenium\\","accountReloadConfirmationPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","accountReloadConfirmationPage.jpg");
	switch (optionclick){
		case 0:				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
							break;
		case 1:				driver.findElement(By.id("ctl00_ContentZone_CtbyCard_BoxAuthCode_box_data")).sendKeys(ranNumbr(100000,999999)+"");
							Thread.sleep(500);
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
							break;
		case 2:				driver.findElement(By.id("ctl00_ContentZone_CtbyCheque_txt_number_box_data")).sendKeys(ranNumbr(10000000,9999999)+"");
							Thread.sleep(500);
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
							break;
		case 3:				driver.findElement(By.id("ctl00_ContentZone_CtbyDepoBancario_BoxReference_box_data")).sendKeys("REF. "+ranNumbr(100000,99999));
							Thread.sleep(500);
							elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
							break;
	}
	Thread.sleep(3000);
	takeScreenShot("E:\\Selenium\\","accountReloadInvoicePage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\accountReload\\attachments\\","accountReloadInvoicePage.jpg");
	elementClick("ctl00_ButtonsZone_BtnBack_IB_Label");
	Thread.sleep(2000);
	Saldo = driver.findElement(By.id("ctl00_ContentZone_ctrlAccountNotes_label_balance_pounds")).getText();
	
	}
}


		
	




