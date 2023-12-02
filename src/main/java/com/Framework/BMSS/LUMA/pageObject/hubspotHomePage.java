package com.Framework.BMSS.LUMA.pageObject;

import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.helper.wait.WaitHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class hubspotHomePage {
  private WebDriver driver;
  private final Logger log = LoggerHelper.getLogger(hubspotHomePage.class);

  WaitHelper waitHelper;

  public hubspotHomePage(WebDriver driver){

  }

}
