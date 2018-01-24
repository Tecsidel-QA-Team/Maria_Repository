package CAC;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import coviHondurasSettingFile.Settingsfields_File;

public class CAC_accountCreationOnly extends Settingsfields_File {
	
			@Before
			public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "E:\\workspace\\Maria_Repository\\lib\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); //poner esta opción cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); //poner esta opción cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opción cuando se vaya a subir al Git
    				//driver.manage().window().maximize();	
    				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			public void accountCreationInit() throws Exception {				
				accountCreation();
				Thread.sleep(1000);
				System.out.println("Se ha creado la cuenta: "+accountNumbr.substring(7,16)+" correctamente");
				System.out.println("Se ha probado en la versión del CAC BO y HM: " + getVersion("BO")+" y CAC Manager: "+getVersion("HM"));
			}

		public static void accountCreation() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Maria_Repository\\CAC_accountCreationAlone\\attachments\\");
			try{
				driver.get(CaCUrl);
				takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_accountCreationAlone\\attachments\\","loginCACCVHPage.jpg");
				driver.findElement(By.id(loginField)).sendKeys("00001");
				driver.findElement(By.id(passField)).sendKeys("00001");
				driver.findElement(By.id(loginButton)).click();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_accountCreationAlone\\attachments\\","homeCACCVHPage.jpg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(1000);						
				//action.contextClick().build().perform();
				action.moveToElement(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
				action.moveToElement(driver.findElement(By.linkText("Crear cuenta"))).build().perform();;
				Thread.sleep(1000);
				driver.findElement(By.linkText("Prepago")).click();								
				Thread.sleep(1000);
				accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
				takeScreenShot("E:\\Selenium\\","accountCreationPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_accountCreationAlone\\attachments\\","accountCreation.jpg");
				//Thread.sleep(500);	
				int selOpt = ranNumbr(0,1);
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
				if (selOpt==0){
					driver.findElement(By.id(infoCuenta0)).click();
					Thread.sleep(1000);
					driver.findElement(By.id(RUCid)).sendKeys(ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000)+"");
					new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
					driver.findElement(By.id(namef)).sendKeys(nameOp[selOp]);
					driver.findElement(By.id(surnamef)).sendKeys(lastNameOp[selOp]);
					driver.findElement(By.id(addressf)).sendKeys(addressTec[selOp]);
					driver.findElement(By.id(cpf)).sendKeys(cpAdress[selOp]);			
					driver.findElement(By.id(countryf)).sendKeys("España");
					driver.findElement(By.id(emailf)).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
					driver.findElement(By.id(phoneCel)).sendKeys(ranNumbr(600000000,699999999)+"");
					driver.findElement(By.id(workPhone)).sendKeys(workPhone1[selOp]);
					driver.findElement(By.id(perPhone)).sendKeys(ranNumbr(900000000,999999999)+"");
					driver.findElement(By.id(faxPhone)).sendKeys(workPhone1[selOp]);				
					Thread.sleep(4000);
				}else{
					driver.findElement(By.id(infoCuenta1)).click();
					driver.findElement(By.id(RUCid)).sendKeys(ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000)+"");
					Thread.sleep(1000);
					driver.findElement(By.id(companyf)).sendKeys("Tecsidel, S.A");
					driver.findElement(By.id(contactf)).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
					driver.findElement(By.id(addressf)).sendKeys(addressTec[2]);
					driver.findElement(By.id(cpf)).sendKeys(cpAdress[2]);
					driver.findElement(By.id(countryf)).sendKeys("España");
					driver.findElement(By.id(emailf)).sendKeys("info@tecsidel.es");
					driver.findElement(By.id(phoneCel)).sendKeys(ranNumbr(600000000,699999999)+"");
					driver.findElement(By.id(workPhone)).sendKeys(workPhone1[2]);
					driver.findElement(By.id(perPhone)).sendKeys(ranNumbr(900000000,999999999)+"");
					driver.findElement(By.id(faxPhone)).sendKeys(workPhone1[selOp]);
					Thread.sleep(1000);								
				}
				selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_groupFare_cmb_dropdown");
				Thread.sleep(1000);		
				takeScreenShot("E:\\Selenium\\","dataFilled"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Maria_Repository\\CAC_accountCreationAlone\\attachments\\","dataFilled.jpg");
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Label");
				Thread.sleep(3000);
				String nextPage = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
				Thread.sleep(3000);
				assertEquals("Detalles del pago", nextPage);
				elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
				Thread.sleep(2000);
				
			}catch(Exception e){
				e.printStackTrace();
				fail();
			}
		}		
			}
