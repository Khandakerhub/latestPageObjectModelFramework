package com.Framework.BMSS.LUMA.testbase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.Framework.BMSS.LUMA.utils.ExtentManager;
import com.Framework.BMSS.LUMA.utils.testExecutionReport;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.BrowserType;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.ChromeBrowser;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.FirefoxBrowser;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.EgdeBrowser;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.PropertyReader;
import com.Framework.BMSS.LUMA.helper.excel.ExcelHelper;
import com.Framework.BMSS.LUMA.helper.javaScript.JavaScriptHelper;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.helper.resource.ResourceHelper;
import com.Framework.BMSS.LUMA.helper.wait.WaitHelper;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

/**
 * @author Bhanu Pratap Singh
 */
public class TestBase {

public static ExtentReports extent;
public static ExtentTest test;
public WebDriver driver;

private static final Logger log = LoggerHelper.getLogger(TestBase.class);
public static File reportDirectery;
int indexSI=1;

@BeforeSuite
public void beforeSuite() {
  extent = ExtentManager.getInstance();
}

@BeforeTest
public void beforeTest() throws Exception {
  ObjectReader.reader = new PropertyReader();
  reportDirectery = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
  setUpDriver(ObjectReader.reader.getBrowserType());
  test = extent.createTest(getClass().getSimpleName());
}


@BeforeMethod
public void beforeMethod(Method method) {
  test.log(Status.INFO, method.getName() + "**************test started***************");
  log.info("**************" + method.getName() + "Started***************");
}

@AfterMethod
public void afterMethod(ITestResult result) throws IOException {
  if (result.getStatus() == ITestResult.FAILURE) {
    test.log(Status.FAIL, result.getThrowable());
    String imagePath = captureScreen(result.getName(), driver);
    test.addScreenCaptureFromPath(imagePath);
  } else if (result.getStatus() == ITestResult.SUCCESS) {
    test.log(Status.PASS, result.getName() + " is **PASS**");
    String imagePath = captureScreen(result.getName(), driver);
    test.addScreenCaptureFromPath(imagePath);
  } else if (result.getStatus() == ITestResult.SKIP) {
    test.log(Status.SKIP, result.getThrowable());
  }
  log.info("**************" + result.getName() + "Finished**************");
  extent.flush();
}

@AfterTest
public void afterTest() {
  if (driver != null) {
    log.info("**************Quit Browser**************");
    driver.quit();
  }
}

public WebDriver getBrowserObject(BrowserType btype) throws Exception {

  try {
    switch (btype) {
      case Chrome:
        // get object of ChromeBrowser class
        ChromeBrowser chrome = ChromeBrowser.class.newInstance();
        ChromeOptions chromeOptions = chrome.getChromeOptions();
        return chrome.getChromeDriver(chromeOptions);
      case Firefox:
        FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
        FirefoxOptions firefoxOptions = firefox.getFirefoxOptions();
        return firefox.getFirefoxDriver(firefoxOptions);
      case Edge:
        EgdeBrowser edge = EgdeBrowser.class.newInstance();
        EdgeOptions edgeOptions = edge.getEdgeOptions();
        return edge.getEdgeDriver(edgeOptions);
				
			/*case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);*/
      default:
        throw new Exception("Driver not Found: " + btype.name());
    }
  } catch (Exception e) {
    log.info(e.getMessage());
    throw e;
  }
}

public void setUpDriver(BrowserType btype) throws Exception {
  driver = getBrowserObject(btype);
  log.info("Initialize Web driver: " + driver.hashCode());
  WaitHelper wait = new WaitHelper(driver);
  wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
  wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
  driver.manage().window().maximize();
}

// Screen Capturing-------------------------------------------------
public String captureScreen(String fileName, WebDriver driver) {
  if (driver == null) {
    log.info("driver is null..");
    return null;
  }
  if (Objects.equals(fileName, "")) {
    fileName = "Capture";
  }
  Reporter.log("captureScreen method called");
  File destFile = null;
  Calendar calendar = Calendar.getInstance();
  SimpleDateFormat formater = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
  File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  try {
    destFile = new File(reportDirectery + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
    Files.copy(screFile.toPath(), destFile.toPath());
    Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='120' width='180'/></a>");
  } catch (Exception e) {
    e.printStackTrace();
  }
  assert destFile != null;
  return destFile.toString();
}


public void getNavigationScreen(WebDriver driver) {
  log.info("capturing ui navigation screen...");
  new JavaScriptHelper(driver).zoomInBy60Percentage();
  String screen = captureScreen("", driver);
  new JavaScriptHelper(driver).zoomInBy100Percentage();
  try {
    test.addScreenCaptureFromPath(screen);
  } catch (Exception e) {
    e.printStackTrace();
  }
}

//Create Extent HTML report-------------------------------------------
public static void logExtentReport(String s1) {
  test.log(Status.INFO, s1);
}

public void getApplicationUrl(String url) {
  driver.get(url);
  logExtentReport("navigating to ..." + url);
}

//Create Test Execution HTML report-------------------------------------
public static void updateReport(int indexSI, String testCaseName, String testCaseStatus, String scriptName) throws IOException {
  String startDateTime=new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss").format(new GregorianCalendar().getTime());
  String userDirector=System.getProperty("user.dir");
  System.out.println(userDirector);
  String resultFile=userDirector+"/src/main/resources/reports/updateReport.html";
  File file=new File(resultFile);
  System.out.println(file.exists());

  if (!file.exists()){
    FileWriter fw=new FileWriter(file.getAbsoluteFile(), true);
    BufferedWriter bw1=new BufferedWriter(fw);
    bw1.write("<html>"+"\n");
    bw1.write("<head><title>" + "Test Update Report" + "</title" + "\n");
    bw1.write("</head>" + "\n");
    bw1.write("<body bgcolor='#a3a3a3'>" + "\n");
    bw1.write("<font face='Tahoma' size='3'>" + "\n");
    bw1.write("<h1 align='center' color='#ccff04' bgcolor='#b6570e'><u>" + "Test Update Report" + "</u></h>" + "\n");
    bw1.flush();
    bw1.close();
  }

  BufferedWriter bw2=new BufferedWriter(new FileWriter(file, true));
  if (indexSI==1){
    bw2.write("<table align='center' border='0' width='90%' height='20'>");
    bw2.write("<tr><td width='90%' </td></tr>");
    bw2.write("<table align='center' border='1' width='90%' height='60'>");
    bw2.write("<tr>");
    bw2.write("<td colspan='2' align='center' bgcolor='#171717'><b><font color='#ccff04' face='Tahoma' size='3'>Script Name:&nbsp;&nbsp;&nbsp;</font></b><font bgcolor='#030303' color='#ec50b2' face='Tahoma' size='3'>" + scriptName + "</font></td>");
    bw2.write("<td colspan='1' align='left' bgcolor='#171717'><b><font bgcolor='#030303' color='#ccff04' face='Tahoma' size='3'>Start Time:&nbsp;</font></b><font color='#ec50b2' face='Tahoma' size='3'>" + startDateTime + "</font></td>");
    bw2.write("</tr>");
    bw2.write("</tr>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>SL No.</font></b></td>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>Test Case ID / Test Case Description</font></b></td>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>Result</font></b></td>");
  }
  bw2.write("<tr>" + "\n");
  bw2.write("<td bgcolor='#ffffdc' align='center'><font color='#000000' face='Tahoma' size='3'>" + indexSI + "</font></td>" + "\n");
  bw2.write("<td bgcolor='#ffffdc' align='left' valign='middle'><font color='#000000' face='Tahoma' size='3'>" + testCaseName + "</font></td>" + "\n");
  if (testCaseStatus == "Pass"){
    bw2.write("<td bgcolor='#ffffdc' align='center' valign='middle'><b><font color='#00ff00' face='Tahoma' size='3'>" + testCaseStatus + "</font></b></td>" + "\n");
  }else {
    bw2.write("<td bgcolor='#ffffdc' align='center' valign='middle'><b><font color='#ff0000' face='Tahoma' size='3'>" + testCaseStatus + "</font></b></td>" + "\n");
  }
  bw2.write("</tr>" + "\n");
  bw2.write("</body>" + "\n");
  bw2.write("</html>");
  bw2.flush();
  bw2.close();

}

//Get Data From Exel Sheet----------------------------------------------
public static Object[][] getExcelData(String excelName, String sheetName) {
  String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configfile/") + excelName;
  log.info("excel location " + excelLocation);
  ExcelHelper excelHelper = new ExcelHelper();
  return excelHelper.getExcelData(excelLocation, sheetName);
}

//Random any length string generator-------------------------------------
public static String randomeString(int length) {

  return (RandomStringUtils.randomAlphanumeric((length)));
}

//Random 16 digit card number generator-----------------------------------
public static String randomCardNumber() {
  Random randNum = new Random();
  int generateNumber = randNum.nextInt(90000000) + 10000000;
  return generateNumber + "12344678";
}

//Random 4 digit card expire date generator--------------------------------
public static String randomExpDate() {
  Random rand = new Random();
  // Generate random 2-digit month number----------------------------------
  int randMonth = rand.nextInt(10) + 2;
  return randMonth + "28";
}

//Random 3 digit card code generator-----------------------------------------
public static String randomCode() {
  Random randCode = new Random();
  int generateCode = randCode.nextInt(900) + 100;
  return String.valueOf(generateCode);
}

// Generate a random 5-digit ZIP code-----------------------------------------
public static String randomZipCode() {
  Random rand = new Random();
  int zipCode = rand.nextInt(90000) + 10000; // Ensuring a valid ZIP code (10000-99999)
  return String.valueOf(zipCode);
}

// Generate a random 10-digit us phone number------------------------------------
public static String randomPhoneNumber() {
  Random rand = new Random();
  // Generate a random 3-digit area code------------------------------------------
  int areaCode = rand.nextInt(800) + 200; // Ensuring a valid area code (200-999)
  // Generate a random 3-digit prefix----------------------------------------------
  int prefix = rand.nextInt(900) + 100; // Ensuring a valid prefix (100-999)
  // Generate a random 4-digit line number-----------------------------------------
  int lineNumber = rand.nextInt(10000);
  // Format the phone number as (XXX) XXX-XXXX-------------------------------------
  return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
}
/*
public static void main(String[] args) {
  getExcelData("testData.xlsx", "LoginData");
}*/
}


