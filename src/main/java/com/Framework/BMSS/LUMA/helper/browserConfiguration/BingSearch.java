package com.Framework.BMSS.LUMA.helper.browserConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BingSearch {
public static String randomeString(int length) {
  return (RandomStringUtils.randomAlphanumeric((length)));
}

public static void main(String[] args) throws InterruptedException {
  WebDriverManager.edgedriver().setup();
  WebDriver driver = new EdgeDriver();
  driver.get("https://rewards.bing.com/");
  driver.manage().window().maximize();
  Thread.sleep(2000);
  String account = driver.findElement(By.xpath("//h1[@class='c-heading-2 ellipsis ng-binding c-heading']")).getText();
  System.out.println("Account Name: " + account);
  String point = driver.findElement(By.xpath("//*[@id=\"balanceToolTipDiv\"]/p/mee-rewards-counter-animation/span")).getText();
  System.out.println("Previous Point: " + point);

  driver.navigate().to("https://bing.com/");

  for (int i = 0; i<20; i++) {
    driver.findElement(By.xpath("//textarea[@id='sb_form_q']")).clear();
    driver.findElement(By.xpath("//textarea[@id='sb_form_q']")).sendKeys(randomeString(8), Keys.ENTER);
    Thread.sleep(2000);
  }
  driver.navigate().to("https://rewards.bing.com/");
  Thread.sleep(2000);
  String point2 = driver.findElement(By.xpath("//*[@id=\"balanceToolTipDiv\"]/p/mee-rewards-counter-animation/span")).getText();
  System.out.println("New Total Point: " + point2);

  driver.close();
  driver.quit();
}
}
