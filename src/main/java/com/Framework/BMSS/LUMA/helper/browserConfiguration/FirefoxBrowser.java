
package com.Framework.BMSS.LUMA.helper.browserConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();

		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);

		firefoxOptions.setCapability("marionette", true);
		firefoxOptions.setCapability(FirefoxOptions.FIREFOX_OPTIONS, profile);

		// Linux
		if (System.getProperty("os.name").contains("Linux")) {
			firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}

		return firefoxOptions;
	}

	public WebDriver getFirefoxDriver(FirefoxOptions firefoxOptions) {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(firefoxOptions);
	}
	
	public static void main(String[] args) {
		FirefoxBrowser obj = new FirefoxBrowser();
		WebDriver driver = obj.getFirefoxDriver(obj.getFirefoxOptions());
		driver.get("https://www.google.com/");
	}


}
