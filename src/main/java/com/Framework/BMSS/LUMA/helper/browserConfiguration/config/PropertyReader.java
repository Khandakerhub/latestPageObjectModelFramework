package com.Framework.BMSS.LUMA.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.BrowserType;
import com.Framework.BMSS.LUMA.helper.resource.ResourceHelper;
/**
 * 
 * @author Bhanu Pratap Singh
 *
 */
public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
			
			String filePath1 = ResourceHelper.getResourcePath("src/main/resources/configfile/config1.properties");
			file = new FileInputStream(new File(filePath1));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImpliciteWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageLoadTime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}
	public String getNewPassword() {
		if(System.getProperty("newPassword")!=null){
			return System.getProperty("newPassword");
		}
		return OR.getProperty("newPassword");
	}

}
