package com.seanfiles.helper;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestConfig {

	protected static Properties props;
	protected static String runID;
	protected static Properties envProps;
	
	private final static String PropFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\"+"ACEAPI20-Config.properties";
	private final static String EnvFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\"+"env.prop";
	private static Logger log = Logger.getLogger(TestConfig.class);
	
	static
	{
	 setEnv();
	}
	
	public static String getRunID() {
		if(runID == null ) {
			runID=(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
		}
		return runID;
	}

	private static void setEnv() {
		File envFile = new File(EnvFilePath);
		if(envFile.exists()) {
			props = new Properties();
			try {
				props.load(new FileInputStream(EnvFilePath));
				setEnv(props.getProperty("env"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			props=null;
		}
	}

	private static void setEnv(String env) {
		if(env != null && env != "") {
			log.info("Env: "+env);
			String envPropFilePath=PropFilePath.replace("Config", "Config-"+env);
			File envPropFile = new File(envPropFilePath);
			if(envPropFile.exists()) {
				log.info("Property file for Env: "+env+" - "+envPropFilePath);
				envProps = new Properties();
				try {
					envProps.load(new FileInputStream(envPropFilePath));
				} catch (Exception e) {
					envProps=null;
				}
			}
			else {
				log.info("FILE NOT FOUND--Property file for Env: "+env+" - "+envPropFilePath);
			}
		}		
	}

	public static String getProperty(String property) {
		String propValue=null;
		try {
			if(props == null) {
				File propFile = new File(PropFilePath);
				props = new Properties();
				if(propFile.exists()) {
					props.load(new FileInputStream(PropFilePath));
					log.info("Property file "+propFile);
				}
				else {
					log.info("FILE NOT FOUND--Property file "+propFile);
				}
			}
			propValue=props.getProperty(property);
			if(envProps != null) {
				propValue=getEnvProperty(property, propValue);
			}
		}
		catch(Exception e) {	
			e.printStackTrace();
		}
		log.info("Propery: "+property+", Value: "+propValue);
		return propValue;
	}

	private static String getEnvProperty(String property, String defaultValue) {
		return envProps.getProperty(property, defaultValue);
	}
}
