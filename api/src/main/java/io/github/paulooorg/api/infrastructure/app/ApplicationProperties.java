package io.github.paulooorg.api.infrastructure.app;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationProperties {
	private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);
	
	private static final Properties properties = new Properties();
	
	static {
		//TODO: Read active profile application-prod, -hml, -dsv, -local
		String propertiesFilePath = "META-INF/conf/application.properties";
		try (InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
			properties.load(input);
		} catch (Exception e) {
			logger.error("Failed to read file on path " + propertiesFilePath);
		}
	}
	
	public static String get(String key) {
		try {
			return properties.getProperty(key);
		} catch (Exception e) {
            logger.error("Value not found for key {}", key);
            return key;
		}
	}
}
