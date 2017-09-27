package HostPlazaBackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.rowset.CachedRowSetImpl;


import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class verConfirmación_TransitosSubidos extends senacFieldsConfiguration{
		private static Statement stmt;
		private static ResultSet rs;
		private static String queryString;
		private static String verFile;


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
		

		
@Test		
			public void dataBaseConnection() throws Exception{
			
			//borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\conexion_BBDDSenac\\attachments\\");
			 String connectionUrl = "jdbc:sqlserver://172.18.130.188:1433;DataBaseName=COVIHONDURAS_QA_TOLLHOST"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
			    stmt = null;
			    rs = null;
		      try {
		         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		         Connection conn = DriverManager.getConnection(connectionUrl, "sa", "lediscet");
		         stmt = conn.createStatement();
		         queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '20170921%' ORDER BY passagetime DESC";
		         //queryString = "SELECT M.PLAZANODE, M.LANENODE, M.MSGSEQ, M.MSGNUMBER, M.MSGTIME, M.MSGTYPE, M.MSGVERSION, M.MSGSTATUS, M.UPLOADSTATUS, M.UPLOADPACK FROM AMESSAGE M  WHERE M.PLAZANODE= '0101' AND ((M.MSGSTATUS IN ('2','3','4') AND M.MSGTIME >= '20170411150608') OR M.MSGSTATUS = '5') UNION   SELECT MP.PLAZANODE, MP.LANENODE, MP.MSGSEQ, MP.MSGNUMBER, MP.MSGTIME, MP.MSGTYPE, MP.MSGVERSION, MP.MSGSTATUS, MP.UPLOADSTATUS, MP.UPLOADPACK FROM AMESSAGEPARAL MP  WHERE MP.PLAZANODE= '0101' AND ((MP.MSGSTATUS IN ('2','3','4') AND MP.MSGTIME >= '20170411150608') OR MP.MSGSTATUS = '5')";
		         rs = stmt.executeQuery(queryString);
		         String PCD;
		       /*  verFile = "verSenacConnectBBDDResultados_";
					File result = new File("E:\\Selenium\\"+verFile+timet+".txt");
					File resultTmp = new File("E:\\workspace\\Mavi_Repository\\conexion_BBDDSenac\\attachments\\"+verFile+timet+".txt");	  						
					FileOutputStream fis = new FileOutputStream(new File(result.toString()));
					FileOutputStream fis2 = new FileOutputStream(new File(resultTmp.toString()));
					PrintStream out = new PrintStream(fis);
					PrintStream out2 = new PrintStream(fis2);  						
					PrintStream old = System.out;
					System.setOut(out);
					System.setOut(out2);*/
		         System.out.print(StringUtils.center("PASSAGETIME",15));
		         System.out.printf(StringUtils.center("ATRASANCTION",25));
		         System.out.println("");
		         for (int a = 1; a <50; a++){
						System.out.print("-");
					}
				while (rs.next()) {
		        	PCD = rs.getString("passagetime");
		        	String PCD1 = rs.getString("transactionid");
		        	System.out.println("");
		        	System.out.printf("%-20s",PCD);
		        	System.out.printf("%-20s",PCD1);
		        	
		         }
					//fis.close();
					//fis2.close();
					//System.setOut(old);
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}		
			
		@After
			public void tearDown() throws Exception{			  
				    //driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
	      	
}