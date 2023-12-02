
package com.Framework.BMSS.LUMA.helper.browserConfiguration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-popup-blocking");

		if (System.getProperty("os.name").contains("Linux")) {
			chromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return chromeOptions;
	}

	public WebDriver getChromeDriver(ChromeOptions chromeOptions) {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOptions);
	}

	public static void main(String[] args) {
		ChromeBrowser obj = new ChromeBrowser();
		WebDriver driver = obj.getChromeDriver(obj.getChromeOptions());
		driver.get("https://www.zomato.com/");
	}
}



