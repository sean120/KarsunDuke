package com.seanfiles.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.log4j.Logger;

import com.seanfiles.utils.JSONUtilities;
import com.seanfiles.utils.XMLParserUtil;

import cucumber.api.Scenario;

public class TestScenario {
	
	private static Logger log = Logger.getLogger(TestScenario.class);
	public static Scenario scenario=null;
	
	public static void setScenario(Scenario s) {
		scenario=s;
	}
	
	public static void embedToScenario(String fileName, String fileType) {
		if(scenario == null) {
			return;
		}
		
		File attachFile = new File(fileName);
		try {
			scenario.embed(Files.readAllBytes(attachFile.toPath()), fileType);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static void embedToScenario(String str) {
		embedTextToScenario(str,"text/plain");
	}

	public static void embedTextToScenario(String str, String mimeType) {
		if(scenario == null) {
			return;
		}
		
		try {
			byte[] byteStr=str.getBytes();
			scenario.embed(byteStr, mimeType);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

	

	public static void writeJSONToScenario(String jsonStr, String desc) {
		if(scenario == null) {
			log.info(desc+" : "+jsonStr.replace("\n", " ").replace("\r", " "));
			return;
		}
		writeToScenario(desc+" : "+jsonStr.replace("\n", " ").replace("\r", " "));
		embedTextToScenario(JSONUtilities.prettifyJSON(jsonStr), "text/json");
	}
	
	public static void writeXMLToScenario(String xmlStr, String desc) {
		if(scenario == null) {
			log.info(desc+" : "+xmlStr.replace("\n", " ").replace("\r", " "));
		}
		writeToScenario(desc+" : "+xmlStr.replace("\n", " ").replace("\r", " "));
		embedTextToScenario(XMLParserUtil.prettifyXML(xmlStr), "text/xml");
	}
	
	public static void writeToScenario(String text) {
		if(scenario == null) {
			log.info(text);
			return;
		}
		scenario.write(text);
	}

}
