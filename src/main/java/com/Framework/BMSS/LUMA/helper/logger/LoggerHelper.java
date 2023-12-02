package com.Framework.BMSS.LUMA.helper.logger;

import com.Framework.BMSS.LUMA.helper.resource.ResourceHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator; // Import this line

/**
 *
 * @author Bhanu Pratap Singh
 *
 */

public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(Class<?> cls) {
		if (root) {
			return LogManager.getLogger(cls.getName()); // Corrected this line
		}

		Configurator.initialize(null, ResourceHelper.getResourcePath("src/main/resources/configfile/log4j2.properties"));
		root = true;
		return LogManager.getLogger(cls.getName()); // Corrected this line
	}

	public static void main(String[] args) {
		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
		log.info("logger is configured");
		log.fatal("logger is configured");
		log.debug("logger is configured");
		log.warn("logger is configured");
	}
}
