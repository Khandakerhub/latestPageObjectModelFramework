package com.Framework.BMSS.LUMA.helper.resource;

/**
 * 
 * @author Bhanu Pratap Singh
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath +"/"+ path);
		return basePath +"/"+ path;
	}
}
