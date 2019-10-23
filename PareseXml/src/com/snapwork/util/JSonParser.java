package com.snapwork.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSonParser {

	static JSONObject jsonObject = null;
	public JSonParser(){
			 jsonObject =  new JSONObject();
	}
	
	public static JSONObject parseString(String param) 
	{
		JSONObject jsonObject = null;
		JSONParser jsonParser = null;
		try {
			jsonParser = new JSONParser();
			if (param != null) {
				jsonObject = (JSONObject) jsonParser.parse(param);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject = null;
		} finally {
			jsonParser = null;
		}
		return jsonObject;
	}

	@SuppressWarnings("unused")
	public static JSONObject XmltoJson(String response) throws Exception 
	{
		org.json.JSONObject xmlJSONObj = null;
		JSONObject jsonObject = null;
		try {
			xmlJSONObj = XML.toJSONObject(response);
			String reb = xmlJSONObj.toString(4);
			//System.out.println(reb);
			jsonObject = JSonParser.parseString(xmlJSONObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return jsonObject;
	}

	public String jsonToxml(String params) 
	{
		org.json.JSONObject json = null;
		String xml = "";
		try {
			json = new org.json.JSONObject(params);
			xml = XML.toString(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject getProperJson(JSONObject jsonObject) throws Exception 
	{
		JSONObject newjsonObject = new JSONObject();
		try {
			Set<String> set = jsonObject.entrySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Entry<String, String>) it.next();
				String paramName = entry.getKey();
				String paramValue = entry.getValue();
				newjsonObject.put(paramName, "<![CDATA["+paramValue+"]]>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonObject;
		}
		return newjsonObject; 
	}

	public   JSONObject printJsonObject(JSONObject jsonObj) {
		 
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        Object keyvalue = jsonObj.get(keyStr);
	        //Print key and value
//	          System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        jsonObject.put(keyStr, keyvalue);
	        //for nested objects iteration if required
	        if (keyvalue instanceof JSONObject)
	            printJsonObject((JSONObject)keyvalue);
	    }
	    return jsonObject;
	}

	public static  org.json.JSONObject printJsonObject(org.json.JSONObject jsonObj) {
		 Iterator<org.json.JSONObject> key =  jsonObj.keys();
	   while(key.hasNext()) {
	        //based on you key types
		   Object keyvalue = key.next();
		   System.out.println(keyvalue);
//	        Object keyvalue = jsonObj.get(keyStr);
	        //Print key and value
//	          System.out.println("key: "+ keyStr + " value: " + keyvalue);
//	        jsonObject.put(keyStr, keyvalue);
	        //for nested objects iteration if required
//	        if (keyvalue instanceof JSONObject)
//	            printJsonObject((JSONObject)keyvalue);
	    }
	    return jsonObj;
	}
	
	public static void main(String[] args) throws Exception {
		String startSessionResponse = "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\"><soapenv:Body><ns2:startSessionResponse xmlns:ns2=\"http://soap.api.interact.unicacorp.com\"><ns2:return><ns1:apiVersion xmlns:ns1=\"http://api.interact.unicacorp.com/xsd\">1.0.0</ns1:apiVersion><ns1:sessionID xmlns:ns1=\"http://api.interact.unicacorp.com/xsd\">123</ns1:sessionID><ns1:statusCode xmlns:ns1=\"http://api.interact.unicacorp.com/xsd\">0</ns1:statusCode></ns2:return></ns2:startSessionResponse></soapenv:Body></soapenv:Envelope>";
		startSessionResponse=  startSessionResponse.replace("xmlns:ns1=\"http://api.interact.unicacorp.com/xsd\"", "");
		startSessionResponse=  startSessionResponse.replace("xmlns:ns2=\"http://soap.api.interact.unicacorp.com\"", "");
		startSessionResponse=  startSessionResponse.replace("xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\"", "");
		
		org.json.JSONObject jsonObject1 = XML.toJSONObject(startSessionResponse);

		System.out.println(jsonObject1);
		
		JSonParser.printJsonObject(jsonObject1); 
		
		WebServiceResponseParser parser =  new WebServiceResponseParser(startSessionResponse);
		
		System.out.println(parser.getNodeValue("soapenv:Envelope", "soapenv:Body"));
		
		System.out.println(parser.getNodeValue("soapenv:Envelope", "soapenv:Body"));
		
		startSessionResponse=  startSessionResponse.replace("xmlns:ns1=\"http://api.interact.unicacorp.com/xsd\"", "");
		startSessionResponse=  startSessionResponse.replace("xmlns:ns2=\"http://soap.api.interact.unicacorp.com\"", "");
		startSessionResponse=  startSessionResponse.replace("xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\"", "");
		
		
		JSONObject jsonObject = JSonParser.parseString(startSessionResponse);
		if(jsonObject != null){
			
			JSONObject resObject  = JSonParser.getProperJson(jsonObject);
			if(resObject != null){
			  String statusCode = resObject.get("ns1:statusCode") ==  null?"": resObject.get("ns1:statusCode").toString();
			  String sessionId =  resObject.get("ns1:sessionID") ==  null?"":resObject.get("ns1:sessionID").toString();
			 System.out.println(statusCode);
			 System.out.println(sessionId);
			}
		}
	}
}
