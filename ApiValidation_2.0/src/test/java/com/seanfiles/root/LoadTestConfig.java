package com.seanfiles.root;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public class LoadTestConfig {

	protected static Properties props;
	protected static Properties propswrite;

	public Properties loadProperties() {
		try {
			props = new Properties();
			props.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\ACEAPIConfig.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return props;
	}
	public static String getProperty(String property) {
		try {
			if(props == null) {
				props = new Properties();
				props.load(new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\Properties\\ACEAPIConfig.properties"));
			}
			return props.getProperty(property);
		}
		catch(Exception e) {
			
		}
		return null;
	}
	public void storeProperties(String name, String value) {
		try {
			if (name.equals("apigeeaccesstoken")) {
				propswrite = new Properties();
				Parameters params = new Parameters();
				FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
						PropertiesConfiguration.class)
								.configure(params.properties().setFileName(System.getProperty("user.dir")
										.concat("\\src\\test\\resources\\Properties\\ACEAPIConfig.properties")));
				Configuration config = builder.getConfiguration();
				config.setProperty(name, value);
				builder.save();
			}

		} catch (Exception ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}
