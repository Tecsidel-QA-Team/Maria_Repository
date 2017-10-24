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
import org.openqa.selenium.support.ui.Select;
import coviHondurasSettingFile.Settingsfields_File;



public class CAC_ReloadCreationAndAccountAssignment extends Settingsfields_File {
			 private static boolean accountClosed = false;
			 private static boolean reloadCreated = false;		 
			 private static String applicationType;
			 private static String applicationTypeText;
			 public static String reloadDescription;
			 private static int optionclick;
			 
			 
	
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
	public void reloadCreationInit() throws Exception {
		Thread.sleep(1000);
		borrarArchivosTemp("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\");
		accountReload();
		Thread.sleep(1000);
		if (accountClosed){
			System.out.println("No se puede asignar un Recargo a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
			fail("No se puede asignar un Recargo a la cuenta "+accountNumbr.substring(7, 16)+" porque está cerrada");
		}
		if (reloadCreated){
			System.out.println("Se ha creado un Recargo para "+applicationType+" y se ha aplicado a la cuenta "+accountNumbr.substring(7, 16)+" correctamente");
			Thread.sleep(1000);
			return;
		
		}else{
			fail("El recargo se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr.substring(7, 16)+" por un error");
			System.out.println("El recargo se ha creado pero no se ha podido aplicar a la cuenta "+accountNumbr.substring(7, 16)+" por un error, verificar pantallazo o log de error");
		}
		
	}

public static void accountReload() throws Exception {
	Actions action = new Actions(driver);
	driver.get(CaCUrl);
	takeScreenShot("E:\\Selenium\\","loginCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","loginCACCVHPage.jpg");
	driver.findElement(By.id(loginField)).sendKeys("00001");
	driver.findElement(By.id(passField)).sendKeys("00001");
	driver.findElement(By.id(loginButton)).click();
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","homeCACCVHPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","homeCACCVHPage.jpg");
	BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
	Thread.sleep(2000);					
	action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
	Thread.sleep(1000);
	action.moveToElement(driver.findElement(By.linkText("Configuraciones Globales")));
	action.clickAndHold(driver.findElement(By.linkText("Parámetros de cuenta"))).build().perform();
	Thread.sleep(1500);
	driver.findElement(By.linkText("Recargos")).click();
	Thread.sleep(2000);
	takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPage.jpg");
	elementClick("ctl00_ContentZone_BtnCreate");
	Thread.sleep(1000);
	takeScreenShot("E:\\Selenium\\","ReloadCreatioPage"+timet+".jpg");
	takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadCreatioPage.jpg");
	Thread.sleep(500);
	selectDropDown("ctl00_ContentZone_cmb_type_cmb_dropdown");
	new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).selectByIndex(ranNumbr(0,3));
	Thread.sleep(500);
	applicationType = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_type_cmb_dropdown"))).getFirstSelectedOption().getText();	
	applicationTypeText = applicationType+"-"+timet.substring(4, 14);
	Thread.sleep(500);
	driver.findElement(By.id("ctl00_ContentZone_txt_name_box_data")).sendKeys(applicationTypeText);
	Thread.sleep(2000);
	reloadDescription = "Recargo para "+applicationType;			
	driver.findElement(By.id("ctl00_ContentZone_txt_description_box_data")).sendKeys(reloadDescription);
	if (!applicationType.equals("Creación de cuenta")){
		selectDropDown("ctl00_ContentZone_cmb_applicationType_cmb_dropdown");
	}
	action.sendKeys(driver.findElement(By.id("ctl00_ContentZone_money_amount_txt_formated")),ranNumbr(10000,20000)+"").build().perform();
	Thread.sleep(3000);
	elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
	Thread.sleep(3000);
	switch (applicationType){
		case "Creación de cuenta":				accountCreation();
												Thread.sleep(500);
												break;
		case "Actualización de cuenta":			accountUpdate();
												Thread.sleep(500);
												break;
		case "Creación de vehículo":			vehicleCreation();
												Thread.sleep(500);
												break;
		case "Pérdida de Tag":					tagMissed();
												Thread.sleep(500);
												break;
		}

	
	}

	public static void accountCreation() throws Exception{
		Actions action = new Actions(driver);
		Thread.sleep(2000);					
		action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
		Thread.sleep(1000);
		action.moveToElement(driver.findElement(By.linkText("Seleccionar cuenta")));
		action.clickAndHold(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Prepago")).click();								
		Thread.sleep(1000);
		accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
		takeScreenShot("E:\\Selenium\\","accountCreationPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountCreation.jpg");
		elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPage.jpg");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_btn_add");
		Thread.sleep(500);
		elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
		Thread.sleep(100);
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
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","dataFilled.jpg");
		elementClick("ctl00_ButtonsZone_BtnSave_IB_Label");
		reloadConfirmation();
}

	public static void accountUpdate() throws Exception{
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Seleccionar cuenta")).click();
		Thread.sleep(2000);
		elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
		WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		List<WebElement> table = tableres.findElements(By.tagName("tr"));
		int selectAccount = ranNumbr(2,table.size());
		takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountSearchPage.jpg");
		driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
		Thread.sleep(1000);
		accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
		takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountPage.jpg");				
		Thread.sleep(1000);
		if(driver.getPageSource().contains("CUENTA CERRADA")){
			accountClosed = true;
			return;
		}
		elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
		Thread.sleep(500);
		elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPage.jpg");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_btn_add");
		Thread.sleep(500);
		elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
		Thread.sleep(1000);
		driver.findElement(By.id(RUCid)).clear();
		driver.findElement(By.id(RUCid)).sendKeys(ranNumbr(10000000,90000000)+""+ranNumbr(100000,9000000)+"");
		Thread.sleep(1000);		
		takeScreenShot("E:\\Selenium\\","dataChangeded"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","dataChanged.jpg");
		elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
		reloadConfirmation();
		
	}
	
	public static void vehicleCreation() throws Exception{
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Seleccionar cuenta")).click();
		Thread.sleep(2000);
		elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
		WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		List<WebElement> table = tableres.findElements(By.tagName("tr"));
		int selectAccount = ranNumbr(2,table.size());
		takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountSearchPage.jpg");
		driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
		Thread.sleep(1000);
		accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
		takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountPage.jpg");				
		Thread.sleep(1000);
		if(driver.getPageSource().contains("CUENTA CERRADA")){
			accountClosed = true;
			return;
		}
		elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
		Thread.sleep(500);
		elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPage.jpg");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_btn_add");
		Thread.sleep(500);
		elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
		Thread.sleep(2000);
		CAC.CAC_accountCreationWithVehicle.accountCreationWithVehicle();
		reloadConfirmation();
	}
	
	public static void tagMissed() throws Exception{
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Seleccionar cuenta")).click();
		Thread.sleep(2000);
		elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
		WebElement tableres = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		List<WebElement> table = tableres.findElements(By.tagName("tr"));
		int selectAccount = ranNumbr(2,table.size());
		takeScreenShot("E:\\Selenium\\","accountSearchPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountSearchPage.jpg");
		driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selectAccount+"]/td[1]/a")).click();
		Thread.sleep(1000);
		accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
		takeScreenShot("E:\\Selenium\\","accountPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountPage.jpg");				
		Thread.sleep(1000);
		if(driver.getPageSource().contains("CUENTA CERRADA")){
			accountClosed = true;
			return;
		}
		elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
		Thread.sleep(500);
		elementClick("ctl00_ContentZone_BtnFees"); //botón Recargos;
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","ReloadPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPage.jpg");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("ctl00_ContentZone_list_all_fees"))).selectByVisibleText(applicationTypeText);
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_btn_add");
		Thread.sleep(500);
		elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
		Thread.sleep(2000);

		String numberVehicles = driver.findElement(By.id("ctl00_ContentZone_lbl_vehicles")).getText();
		int NumbVeh = Integer.parseInt(numberVehicles);
		if (NumbVeh==0){
			CAC.CAC_accountCreationWithVehicle.accountCreationWithVehicle();
		}else{
			driver.findElement(By.id("ctl00_ButtonsZone_BtnValidate_IB_Label")).click();
			Thread.sleep(1500);
		}
		elementClick("ctl00_ContentZone_BtnTags");
		takeScreenShot("E:\\Selenium\\","tagAssignmentMainPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","tagAssignmentMainPage.jpg");
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_chk_0");
		String tagid = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_m_table_members']/tbody/tr[2]/td[6]")).getText();
		if (tagid.equals("")){
			elementClick("ctl00_ContentZone_btn_token_assignment");
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_txt_pan_token_txt_token_box_data")).sendKeys(ranNumbr(1,99999)+"");
			Thread.sleep(500);
			elementClick("ctl00_ContentZone_btn_init_tag");
			Thread.sleep(500);
			elementClick("ctl00_ContentZone_chk_0");
		}
		elementClick("ctl00_ContentZone_btn_token_stolen");
		Thread.sleep(1000);
		elementClick("ctl00_ContentZone_btn_stolen");
		Thread.sleep(1500);
		reloadConfirmation();		
		
	}
	
	
	public static void reloadConfirmation() throws Exception{
		Thread.sleep(3000);
		String nextPage = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
		Thread.sleep(3000);
		assertEquals("Detalles del pago", nextPage);
		WebElement tablereload = driver.findElement(By.id("ctl00_ContentZone_CtNumbers_m_table_fees"));
		List<WebElement> tablere = tablereload.findElements(By.tagName("tr"));		
		takeScreenShot("E:\\Selenium\\","PayDetailPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","PayDetailPage.jpg");
		if (tablere.size()>1){
			String reload = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_CtNumbers_m_table_fees']/tbody/tr[2]/td[1]")).getText();
			if (reload.contains(reloadDescription)){
				reloadCreated = true;
			}
			else{
				reloadCreated = false;
				return;
			}
		}else{
			reloadCreated = false;
			return;
		}
		elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
		Thread.sleep(1000);		
		if (!applicationTypeText.equals("Pérdida de Tag")){
			optionclick = ranNumbr(0,3);
		}else{
			optionclick = ranNumbr(0,2);
		}		
		elementClick("ctl00_ContentZone_CtType_radioButtonList_payBy_"+optionclick);
		int optionclick1 = ranNumbr(0,1);
		if (optionclick1==1){
			elementClick("ctl00_ContentZone_CtType_chk_present");
		}
		Thread.sleep(1000);		
		takeScreenShot("E:\\Selenium\\","ReloadPageDetail"+timet+".jpeg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","ReloadPageDetail.jpeg");
		elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
		Thread.sleep(3000);
		takeScreenShot("E:\\Selenium\\","accountReloadConfirmationPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadCreation\\attachments\\","accountReloadConfirmationPage.jpg");
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
			case 3:				driver.findElement(By.id("ctl00_ContentZone_CtbyDepoBancario_BoxReference_box_data")).sendKeys("REF. "+ranNumbr(1000000,9999999));
								Thread.sleep(500);
								elementClick("ctl00_ButtonsZone_BtnExecute_IB_Label");
								break;
		}
		Thread.sleep(4000);
		takeScreenShot("E:\\Selenium\\","accountReloadInvoicePage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Maria_Repository\\ReloadPage\\attachments\\","accountReloadInvoicePage.jpg");
		elementClick("ctl00_ButtonsZone_BtnBack_IB_Label");
		Thread.sleep(2000);
		
	}
}
	
	
	



		
	




