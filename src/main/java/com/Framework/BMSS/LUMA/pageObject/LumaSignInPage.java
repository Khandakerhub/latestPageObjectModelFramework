package com.Framework.BMSS.LUMA.pageObject;

import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.helper.wait.WaitHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LumaSignInPage {

  private WebDriver driver;
  private final Logger log = LoggerHelper.getLogger(LumaCreateAccountPage.class);
  WaitHelper waitHelper;

  @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
  WebElement SignInLink;
  @FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[2]/a")
  WebElement EnterEmail;
  @FindBy(xpath = "//button[@id='loginBtn']")
  WebElement EnterPassword;
  @FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[2]/a")
  WebElement SignInBtn;
  @FindBy(xpath="//span[contains(text(),'Create an Account')]")
  WebElement CreateAccountBtn;
}
