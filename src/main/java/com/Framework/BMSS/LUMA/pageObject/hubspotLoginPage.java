package com.Framework.BMSS.LUMA.pageObject;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.testbase.TestBase;
import com.Framework.BMSS.LUMA.helper.assertion.VerificationHelper;
import com.Framework.BMSS.LUMA.helper.javaScript.JavaScriptHelper;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.helper.wait.WaitHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class hubspotLoginPage {

  private WebDriver driver;
  private final Logger log = LoggerHelper.getLogger(hubspotLoginPage.class);

  WaitHelper waitHelper;
  hubspotHomePage hubspotHomePage;

  @FindBy(xpath = "//input[@id='username']")
  WebElement emailAddress;

  @FindBy(xpath = "//input[@id='password']")
  WebElement password;

  @FindBy(xpath = "//button[@id='loginBtn']")
  WebElement login;
  @FindBy(xpath="//*[@id='center_column']/p")
  WebElement successMsgObject;
  @FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[2]/a")
  WebElement logout;

  public hubspotLoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waitHelper = new WaitHelper(driver);
    waitHelper.waitForElement(emailAddress, ObjectReader.reader.getExplicitWait());
    new TestBase().getNavigationScreen(driver);
    TestBase.logExtentReport("Login Page Object Created");
  }

  public void enterEmailAddress(String emailAddress){
    log.info("entering email address...."+emailAddress);
    TestBase.logExtentReport("entering email address...."+emailAddress);
    this.emailAddress.sendKeys(emailAddress);
  }

  public void enterPassword(String password){
    log.info("entering password...."+password);
    TestBase.logExtentReport("entering password...."+password);
    this.password.sendKeys(password);
  }

  public hubspotHomePage clickOnLoginButton(){
    log.info("clicking on login button...");
    TestBase.logExtentReport("clicking on login button...");
    JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
    javaScriptHelper.scrollDownVertically();
    new JavaScriptHelper(driver).scrollDownVertically();
    login.click();
    return new hubspotHomePage(driver);
  }


  public void loginToApplication(String userName, String password) {
enterEmailAddress(userName);
enterPassword(password);
clickOnLoginButton();
  }

  public boolean verifySuccessLoginMsg(){
    return new VerificationHelper(driver).isDisplayed(successMsgObject);
  }
  public void logout(){
    logout.click();
    log.info("clicked on logout link");
    waitHelper.waitForElement(login, ObjectReader.reader.getExplicitWait());
  }

  /*public void logExtentReport(String s1){
    TestBase.test.log(Status.INFO, s1);
  }*/
}
