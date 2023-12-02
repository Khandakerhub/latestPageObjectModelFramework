package com.Framework.BMSS.LUMA.helper.browserConfiguration;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenBrowser extends PropertyReader {

  static WebDriver driver;


  //Method for Opening Browser-------------------------------------------
  public static void initialize() throws InterruptedException {
    String brName=OR.getProperty("Browser");

    if(brName.equals("firefox")){
      WebDriverManager.firefoxdriver().setup();
      driver=new FirefoxDriver();
    }else if(brName.equals("edge")){
      WebDriverManager.edgedriver().setup();
      driver=new EdgeDriver();
    }else{
      WebDriverManager.chromedriver().setup();
      driver=new ChromeDriver();
    }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    //driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

    driver.get(OR.getProperty("URL"));
    Thread.sleep(2000);
  }
}
