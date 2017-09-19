package MCS;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class _MCS_Settingsfields {
	public static String fileError;
		public static String errorLev;
	public static WebDriver driver;		
	public static String BoHostUrl="http://virtualbo-qa/BOQAHostCoviHonduras/web/forms/core/login.aspx";
	public static String BoPlazaUrl="http://virtualbo-qa/BOQAPlazaCoviHonduras/web/forms/core/login.aspx";
	public static String MCSUrl="http://virtualMCS-qa/MCS_CoviHonduras/web/forms/core/login.aspx";
	public boolean acceptNextAlert = true;
	public static Calendar calF; 
	public static Calendar calT;
	public static SimpleDateFormat sft;
	private static int dateMFrom;
	public static int delm;
	public static StringBuffer verificationErrors = new StringBuffer();
	public static int numbering;		
	public static String loginField = "BoxLogin";
	public static String passField = "BoxPassword";
	public static String loginButton = "BtnLogin";
	public static String opIdField = "ctl00_ContentZone_operatorId_box_data";
	public static String nameField = "ctl00_ContentZone_forename_box_data";
	public static String lastNameField = "ctl00_ContentZone_surname_box_data";
	public static String emailField = "ctl00_ContentZone_email_box_data";
	public static String groupOperator = "ctl00_ContentZone_group_cmb_dropdown";
	public static String pwdField = "ctl00_ContentZone_password_box_data";
	public static String repeatpwdField = "ctl00_ContentZone_password2_box_data";	
	public static String submitBtn = "ctl00_ButtonsZone_BtnSubmit";
	public static String BOVersion;	
	public static String [] nameOp = new String[] {"Pilar", "Mavi", "Franklyn", "Gemma", "Fatima", "Marc", "Miguel", "Francisco", "Oscar", "Maria"};
	public static String [] genderS = new String[] {"Femenino", "Femenino", "Masculino", "Femenino", "Femenino", "Masculino", "Masculino", "Masculino", "Masculino", "Femenino"};
	public static String [] sexSelc = new String[] {"Sra", "Sra", "Sr", "Sra", "Sra", "Sr", "Sr", "Sr", "Sr", "Sra"};
	public static String [] addressTec = new String[] {"CALLE SAN MAXIMO, 3","CALLE SAN MAXIMO, 3","Castanyer 29", "CALLE SAN MAXIMO, 3","CALLE SAN MAXIMO, 3","Catanyer 29","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo","Edificio Tecsidel, P.T. de Boecillo"};
	public static String [] cpAdress = new String[]{"28041", "28041", "08022", "28041", "28041","08022","47151","47151","47151","47151"};
	public static String [] townC = new String []{"Madrid", "Madrid", "Barcelona", "Madrid", "Madrid", "Barcelona", "Valladolid","Valladolid","Valladolid","Valladolid"};
	public static String [] workPhone1 = new String []{"913530810","913530810","932922110","913530810","913530810","932922110","983546603","983546603","983546603","983546603"};
	public static String [] lastNameOp = new String[] {"Bonilla", "Garrido", "Garcia", "Arjonilla", "Romano", "Navarro", "Sanchez", "Castro", "Bailon", "Blanco"};
	
	  //Edit buttons icons configuration.	  	  
	  public static Timestamp timest = new Timestamp (System.currentTimeMillis());
	  public static String timet = timest.toString().replace("-", "").replace(" ", "").replace(":", "").substring(0,14);
	  
	  public static void takeScreenShot(String pathS, String fname) throws Exception {
		    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(pathS, fname));
	  }
	  public static void borrarArchivosTemp(String tempPath) throws Exception{
			File imagTmp = new File (tempPath);
			if (imagTmp.exists()){
				if (imagTmp.isDirectory()){
					File [] imaglist = imagTmp.listFiles();				
					if (imaglist.length > 0){
					 for (int i = 0; i< imaglist.length;i++){
							File delimg = imaglist[i];						
							delimg.delete();						
						}				
					}
				}
			}
			Thread.sleep(1000);
		  }	
 
     	public static void elementClick(String byID) {
      			driver.findElement(By.id(byID)).click();
      		}
      	
      	public static void selectDropDown(String by) {
      		Select vDropdown = new Select (driver.findElement(By.id(by)));
      			List<WebElement> dd = vDropdown.getOptions();		
      			Random rand = new Random();
      		int vdd = rand.nextInt(dd.size());
      			if (vdd<0){vdd = 0;}	
      			if (vdd>=dd.size()){vdd=vdd-1;}
      		new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
      		
      	  }
      	
     	public static int ranNumbr(int min, int max) {
   		  Random rand = new Random();
   		  numbering = min+rand.nextInt((max-min)+1);
   		  return numbering;
   		  
   	  }
     	public static void selectDropDownClick(String by)
        {
            Select vDropdown = new Select (driver.findElement(By.id(by)));
            List<WebElement> dd = vDropdown.getOptions();
            Random rand = new Random();
            int vdd = rand.nextInt(dd.size())+1;
            if (vdd < 0) { vdd = 0; }
            	
            if (vdd >= dd.size()) { vdd = dd.size() - 1; }
            new Select(driver.findElement(By.id(by))).selectByIndex(vdd);
        }
     	
     	public static void notEmptyDropDown(String by) throws Exception{
            Select fDropDown = new Select (driver.findElement(By.id(by)));
            List<WebElement> fDsel = fDropDown.getOptions();
            fDsel.size();             
            if (fDsel.size() > 1){
            	selectDropDownClick(by);
            }
            Thread.sleep(1000);
     	}
     	
     	public static void dateSel() throws Exception{
			calF = Calendar.getInstance();
			calT = Calendar.getInstance();
		 sft = new SimpleDateFormat("dd/MM/yyyy");
		calF.set(ranNumbr(2017,2018), dateMFrom, ranNumbr(1,31));
		calT.set(ranNumbr(1972,1979), ranNumbr(1,12), ranNumbr(1,31));

	}

     	/*public static String dniLetra (int dni){
  		  return String.valueOf(dni)+(NIF_STRING_ASOCIATION.charAt(dni % 23));
  	  }*/
      	
}
