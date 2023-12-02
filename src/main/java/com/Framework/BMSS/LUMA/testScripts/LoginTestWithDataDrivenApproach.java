package com.Framework.BMSS.LUMA.testScripts;


import com.Framework.BMSS.LUMA.helper.assertion.AssertionHelper;
import com.Framework.BMSS.LUMA.helper.browserConfiguration.config.ObjectReader;
import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
import com.Framework.BMSS.LUMA.pageObject.hubspotLoginPage;
import com.Framework.BMSS.LUMA.testbase.TestBase;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 * @author Bhanu Pratap
 */
public class LoginTestWithDataDrivenApproach extends TestBase {
	private final Logger log = LoggerHelper.getLogger(LoginTestWithDataDrivenApproach.class);

	hubspotLoginPage login;
	
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("testData.xlsx", "LoginData");
		return data;
	}
	@BeforeClass
	public void beforeClass(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		login = new hubspotLoginPage(driver);
	}
	@Test(dataProvider="testData")
	public void loginTest(String userName, String password, String runMode){
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
		login.loginToApplication(userName, password);
		boolean status = login.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
		login.logout();


	}
}
