package com.Framework.BMSS.LUMA.helper.browserConfiguration.config;

import com.Framework.BMSS.LUMA.helper.browserConfiguration.BrowserType;
/**
 * 
 * @author Bhanu Pratap Singh
 *
 */
public interface ConfigReader {
	
	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUserName();
	public String getPassword();
	public String getNewPassword();

}
