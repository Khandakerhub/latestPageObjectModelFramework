package com.Framework.BMSS.LUMA.helper.browserConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EgdeBrowser {


  public EdgeOptions getEdgeOptions() {
    EdgeOptions edgeOptions = new EdgeOptions();
    String optionValue = null;
    edgeOptions.setCapability("someOption", (String) null);
    boolean isEnabled = false;
    edgeOptions.setCapability("anotherOption", isEnabled);

    return edgeOptions;
  }


  public WebDriver getEdgeDriver(EdgeOptions edgeOptions) {
    WebDriverManager.edgedriver().setup();
    return new EdgeDriver(edgeOptions);
  }

  public static void main(String[] args) {
    EgdeBrowser obj = new EgdeBrowser();
    WebDriver driver = obj.getEdgeDriver(obj.getEdgeOptions());
    driver.get("https://www.google.com/");
  }
}
