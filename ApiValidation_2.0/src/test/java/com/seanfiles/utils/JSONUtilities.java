package com.seanfiles.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.ReadContext;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JSONUtilities {
	
	public static final String NULL_VALUE="__NULL_VALUE__";
	public static final String NO_OBJECT="__NO_OBJECT__";
	public static final String NO_ARRAY="__NO_ARRAY__";
	public static final String NO_ELEMENT="__NO_ELEMENT__";
	public static final String EMPTY_OBJECT="__EMPTY_OBJECT__";
	public static final String EMPTY_ARRAY="__EMPTY_ARRAY__";
	
	public static String prettifyJSON(String jsonStr) {
		String prettyJSON=jsonStr;
		ObjectMapper mapper=new ObjectMapper();
		try {
			Object jsonObject=mapper.readValue(jsonStr, Object.class);
			prettyJSON=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		} catch (Exception e) {
		}
		return prettyJSON;
	}
	
	public static String getJsonElementValue(ReadContext jsonData, String jsonPath) {
		String stringValue=null;
		Object elementValue=null;
		try {
			elementValue=jsonData.read(jsonPath);
		}
		catch(Exception e) {
			//System.out.println("***Exception in getting value for jsonPath "+jsonPath);
			return null;
		}
		if(elementValue== null) {
			return null;
		}
		if(elementValue.getClass() == String.class) {
			stringValue=(String) elementValue;
		}
		else if(elementValue.getClass() == Integer.class) {
			stringValue=Integer.toString((Integer) elementValue);
		}
		else if(elementValue.getClass() == Double.class) {
			stringValue=Double.toString((Double) elementValue);
		}
		else if(elementValue.getClass() == Boolean.class) {
			stringValue=Boolean.toString((Boolean) elementValue);
		}
		else if(elementValue.getClass() == Long.class) {
			stringValue=Long.toString((Long) elementValue);
		}
		else if(elementValue.getClass() == net.minidev.json.JSONArray.class) {
			stringValue=getJSONArrayElement((net.minidev.json.JSONArray) elementValue, 0);
		}
		else if(elementValue.getClass() == LinkedHashMap.class) {
			LinkedHashMap map=(LinkedHashMap) elementValue;
			Object inElm = map.values().toArray()[0];
			if(inElm.getClass() == String.class) {
				stringValue=(String) inElm;
			}
			else if(inElm.getClass() == Integer.class) {
				stringValue=Integer.toString((Integer) inElm);
			}
			else if(inElm.getClass() == Double.class) {
				stringValue=Double.toString((Double) inElm);
			}
			else if(inElm.getClass() == Boolean.class) {
				stringValue=Boolean.toString((Boolean) inElm);
			}
			else if(inElm.getClass() == Long.class) {
				stringValue=Long.toString((Long) inElm);
			}
		}
		else {
			System.out.println("###*****"+elementValue.getClass().getName()+" jsonPath: "+jsonPath);
		}
		//System.out.println("***Value at jsonPath "+jsonPath+ " is "+stringValue);
		return stringValue;
	}
	

	private static String getJSONArrayElement(JSONArray arr, int index) {
		String stringValue=null;
		Object elementValue=null;
		try {
			elementValue=arr.get(index);
		}
		catch(Exception e) {
			return null;
		}
		if(elementValue.getClass() == String.class) {
			stringValue=(String) elementValue;
		}
		else if(elementValue.getClass() == Integer.class) {
			stringValue=Integer.toString((Integer) elementValue);
		}
		else if(elementValue.getClass() == Double.class) {
			stringValue=Double.toString((Double) elementValue);
		}
		else if(elementValue.getClass() == Boolean.class) {
			stringValue=Boolean.toString((Boolean) elementValue);
		}
		else if(elementValue.getClass() == JSONArray.class) {
			stringValue=getJSONArrayElement((JSONArray) elementValue, 0);
		}
		else {
			System.out.println(elementValue.getClass().getName());
		}
		return stringValue;
	}

	public static boolean modifyValueAtJsonPath(DocumentContext jsonData, String jsonPath, String newValue) {
		//System.out.println("*jsonPath "+jsonPath);
		if(jsonData == null || jsonPath == null) {
			return false;
		}
		Object elementValue=null;
		try {
			elementValue=jsonData.read(jsonPath);
		}
		catch(Exception e) {
			return false;
		}
		if(newValue != null) {
			if(newValue.equalsIgnoreCase(NULL_VALUE)) {
				jsonData.set(jsonPath, null);
				return true;
			}
			else if(newValue.equalsIgnoreCase(NO_ELEMENT) ||
					newValue.equalsIgnoreCase(NO_OBJECT) ||
					newValue.equalsIgnoreCase(NO_ARRAY)) {
				jsonData.delete(jsonPath);				
				return true;
			}
			else if(newValue.equalsIgnoreCase(EMPTY_OBJECT)) {
				JSONObject newEmptyObject=new JSONObject();
				jsonData.set(jsonPath, newEmptyObject);				
				return true;
			}
			else if(newValue.equalsIgnoreCase(EMPTY_ARRAY)) {
				JSONArray newEmptyArray=new JSONArray();
				jsonData.set(jsonPath, newEmptyArray);				
				return true;
			}
		}
		if(elementValue == null) {
			//System.out.println("*Value at jsonPath "+jsonPath+" is null");
			jsonData.set(jsonPath, newValue);
			return true;
		}
		if(newValue == null) {
			//System.out.println("*Value at jsonPath "+jsonPath+" is set to null");
			jsonData.set(jsonPath, newValue);
			return true;
		}
		if(elementValue.getClass() == String.class) {
			jsonData.set(jsonPath, newValue);
		}
		else if(elementValue.getClass() == Integer.class) {
			jsonData.set(jsonPath, Integer.valueOf(newValue));
		}
		else if(elementValue.getClass() == Double.class) {
			jsonData.set(jsonPath, Double.valueOf(newValue));
		}
		else if(elementValue.getClass() == Boolean.class) {
			jsonData.set(jsonPath, Boolean.valueOf(newValue));
		}
		else if(elementValue.getClass() == net.minidev.json.JSONArray.class) {
			return modifySONArrayElement((net.minidev.json.JSONArray) elementValue, 0, jsonData, jsonPath, newValue);
		}
		else {
			try {
				jsonData.set(jsonPath, newValue);
			}
			catch(Exception e) {
				System.out.println("###failed to modify "+jsonPath);
				return false;
			}
		}
		return true;
	}


	private static boolean modifySONArrayElement(JSONArray arr, int index, DocumentContext jsonData, String jsonPath, String newValue) {
			Object elementValue=null;
			try {
				elementValue=arr.get(index);
			}
			catch(Exception e) {
				return false;
			}
			if(elementValue.getClass() == String.class) {
				jsonData.set(jsonPath, newValue);
			}
			else if(elementValue.getClass() == Integer.class) {
				jsonData.set(jsonPath, Integer.valueOf(newValue));
			}
			else if(elementValue.getClass() == Double.class) {
				jsonData.set(jsonPath, Double.valueOf(newValue));
			}
			else if(elementValue.getClass() == Boolean.class) {
				jsonData.set(jsonPath, Boolean.valueOf(newValue));
			}
			else if(elementValue.getClass() == JSONArray.class) {
				return modifySONArrayElement((net.minidev.json.JSONArray) elementValue, 0, jsonData, jsonPath, newValue);
			}
			else {
				try {
					jsonData.set(jsonPath, newValue);
				}
				catch(Exception e) {
					System.out.println("###failed to modify "+jsonPath);
					return false;
				}
			}
			return true;
	}

	public static String mergeJSONStr(String JSONStr1, String JSONStr2) {
		ObjectMapper mapper=new ObjectMapper();
		try {
			JSONObject jsonObject1=mapper.readValue(JSONStr1, JSONObject.class);
			JSONObject jsonObject2=mapper.readValue(JSONStr2, JSONObject.class);
			
			jsonObject1.putAll(jsonObject2);
			return jsonObject1.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
