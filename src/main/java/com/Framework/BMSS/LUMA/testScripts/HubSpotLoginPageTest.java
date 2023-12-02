package com.Framework.BMSS.LUMA.testScripts;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.pageObject.hubspotLoginPage;
import com.Framework.BMSS.LUMA.testbase.TestBase;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class HubSpotLoginPageTest extends TestBase {

  private final Logger log = LoggerHelper.getLogger(LoginTestWithDataDrivenApproach.class);

  @Test(description="Login test with valid credentials")
  public void testLoginToApplication(){
    getApplicationUrl(ObjectReader.reader.getUrl());

    hubspotLoginPage loginPage=new hubspotLoginPage(driver);
    loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());


  }
}
