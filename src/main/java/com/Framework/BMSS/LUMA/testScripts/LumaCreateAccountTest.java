package com.Framework.BMSS.LUMA.testScripts;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.pageObject.LumaCreateAccountPage;
import com.Framework.BMSS.LUMA.testbase.TestBase;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class LumaCreateAccountTest extends TestBase {
int indexSI=1;
  private final Logger log = LoggerHelper.getLogger(LumaCreateAccountTest.class);

  @Test(description = "LUMA-Create Account test with valid information")
  public void testLumaCreateAccount() throws IOException {
    getApplicationUrl(ObjectReader.reader.getUrl());

    LumaCreateAccountPage CAP = new LumaCreateAccountPage(driver);
    CAP.clickOnCreateAccount();
    CAP.enterFirstName(randomeString(5));
    CAP.enterLastName(randomeString(8));
    CAP.enterEmailAddress(randomeString(8) + "@gmail.com");
    CAP.enterPassword(ObjectReader.reader.getNewPassword());
    CAP.reenterPassword(ObjectReader.reader.getNewPassword());
    CAP.clickOnCreateAccountButton();
    String myAccountPage = captureScreen("My_Account", driver);
    log.info("'LUMA Customer Account Page' Screen Capture Path= " + myAccountPage);
    TestBase.logExtentReport("'LUMA Customer Account Page' Screen Capture Path= " + myAccountPage);
    CAP.CustomerAccountPageVerification();

    updateReport(indexSI++, "LUMA-Create Account test with valid information", "PASS", "Page Verification");
  }
}
