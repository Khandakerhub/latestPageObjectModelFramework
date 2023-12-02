package com.Framework.BMSS.LUMA.pageObject;

import com.Framework.BMSS.LUMA.helper.assertion.AssertionHelper;
import com.Framework.BMSS.LUMA.helper.assertion.VerificationHelper;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.helper.javaScript.JavaScriptHelper;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.helper.wait.WaitHelper;
import com.Framework.BMSS.LUMA.testbase.TestBase;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LumaCreateAccountPage {

  private final WebDriver driver;
  private final Logger log = LoggerHelper.getLogger(LumaCreateAccountPage.class);
  WaitHelper waitHelper;


  @FindBy(xpath = "//a[contains(text(),'Create an Account')]")
  WebElement CreateAccountLink;
  @FindBy(xpath = "//input[@id='firstname']")
  WebElement FirstName;
  @FindBy(xpath = "//input[@id='lastname']")
  WebElement LastName;
  @FindBy(xpath = "//input[@id='email_address']")
  WebElement Email;
  @FindBy(xpath = "//input[@id='password']")
  WebElement NewPassword;
  @FindBy(xpath = "//input[@id='password-confirmation']")
  WebElement ConfirmPassword;
  @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Create an Account')]")
  WebElement CreateAccountBtn;
  @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")
  WebElement NewAccount;


  public LumaCreateAccountPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waitHelper = new WaitHelper(driver);
    waitHelper.waitForElement(CreateAccountLink, ObjectReader.reader.getExplicitWait());
    new TestBase().getNavigationScreen(driver);
    TestBase.logExtentReport("Land Page Object Created");
  }

  public void clickOnCreateAccount() {
    CreateAccountLink.click();
    log.info("Click on Create Account Link");
    TestBase.logExtentReport("Click on Create Account Link");
  }

  public void enterFirstName(String fName) {
    log.info("Entering First Name..." + fName);
    TestBase.logExtentReport("Entering First Name..." + fName);
    FirstName.sendKeys(fName);
  }

  public void enterLastName(String lName) {
    log.info("Entering Last Name..." + lName);
    TestBase.logExtentReport("Entering Last Name..." + lName);
    LastName.sendKeys(lName);
  }

  public void enterEmailAddress(String emailAddress) {
    log.info("entering new Email... " + emailAddress);
    TestBase.logExtentReport("entering new Email... " + emailAddress);
    this.Email.sendKeys(emailAddress);
  }

  public void enterPassword(String newPassword) {
    log.info("entering password...." + newPassword);
    TestBase.logExtentReport("entering password...." + newPassword);
    this.NewPassword.sendKeys(newPassword);
  }

  public void reenterPassword(String newPassword) {
    log.info("entering password...." + newPassword);
    TestBase.logExtentReport("Reentering password...." + newPassword);
    this.ConfirmPassword.sendKeys(newPassword);
  }

  public LumaCustomerAccountPage clickOnCreateAccountButton() {
    log.info("Clicking on Create Account button...");
    TestBase.logExtentReport("Clicking on Create Account button...");
    JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
    javaScriptHelper.scrollDownVertically();
    new JavaScriptHelper(driver).scrollDownVertically();
    CreateAccountBtn.click();
    return new LumaCustomerAccountPage();
  }

  public void CustomerAccountPageVerification() {
    waitHelper.waitForElement(NewAccount, 10, 1000);
    log.info("Looking for new account verification element... ");
    TestBase.logExtentReport("Looking for new account verification element... ");
    VerificationHelper VH = new VerificationHelper(driver);
    String verifyAccount = VH.getText(NewAccount);
    AssertionHelper.verifyText(verifyAccount, "My Account");
  }
}
