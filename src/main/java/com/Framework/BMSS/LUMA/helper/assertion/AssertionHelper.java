package com.Framework.BMSS.LUMA.helper.assertion;


import com.Framework.BMSS.LUMA.testbase.TestBase;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.Framework.BMSS.LUMA.helper.logger.LoggerHelper;
/**
 * 
 * @author Bhanu Pratap Singh
 *
 */
public class AssertionHelper {
	
	private static final Logger log = LoggerHelper.getLogger(AssertionHelper.class);


	public static void verifyText(String s1, String s2){
		log.info("Verifying test: "+ s1 + " with "+ s2);
		TestBase.logExtentReport("Verifying test: "+ s1 +" with "+ s2);
		Assert.assertEquals(s1, s2);
	}
	
	public static void markPass(){
		log.info("making script PASS..");
		TestBase.logExtentReport("making script PASS..");
		Assert.assertTrue(true);
	}
	
	public static void markPassWithMessage(String message){
		log.info("making script PASS.."+ message);
		TestBase.logExtentReport("making script PASS.."+ message);
		Assert.assertTrue(true, message);
	}
	
	public static void markFail(){
		log.info("making script FAIL..");
		TestBase.logExtentReport("making script FAIL..");
		Assert.fail();
	}
	
	public static void markFailWithMessage(String message){
		log.info("making script FAIL.."+message);
		TestBase.logExtentReport("making script FAIL.."+message);
		Assert.fail(message);
	}
	
	public static void verifyTrue(boolean status){
		log.info("Verifying True... " + status);
		TestBase.logExtentReport("Verifying True... " + status);
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(boolean status){
		log.info("Verifying False... " + status);
		TestBase.logExtentReport("Verifying False... " + status);
		Assert.assertFalse(status);
	}
	
	public static void verifyNull(String s1){
		log.info("verify object '"+s1+"' is null..");
		TestBase.logExtentReport("verify object '"+s1+"' is null..");
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNull(String s1){
		log.info("verify object '"+s1+"' is not null..");
		TestBase.logExtentReport("verify object '"+s1+"' is not null..");
		Assert.assertNotNull(s1);
	}
	
	public static void fail(){
		Assert.fail();
	}
	
	public static void pass(){
		Assert.assertTrue(true);
	}
	
	public static void updateTestStatus(boolean status){
		if(status){
			pass();
		}
		else{
			fail();
		}
	}
}
