package com.snapwork.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.snapwork.util.DBUtilities;
import com.snapwork.util.JSonParser;

public class LoginController {
	public static void main(String[] args) {
		String xmlResponse = "<return>\n" + 
				"            <responseservice:status>\n" + 
				"               <responseservice:errorCode>0</responseservice:errorCode>\n" + 
				"               <responseservice:extendedReply/>\n" + 
				"               <responseservice:externalReferenceNo>2606201806</responseservice:externalReferenceNo>\n" + 
				"               <responseservice:internalReferenceNumber>2019011033000247</responseservice:internalReferenceNumber>\n" + 
				"               <responseservice:isOverriden>false</responseservice:isOverriden>\n" + 
				"               <responseservice:postingDate>\n" + 
				"                  <datatype:dateString>20190111000000</datatype:dateString>\n" + 
				"               </responseservice:postingDate>\n" + 
				"               <responseservice:replyCode>0</responseservice:replyCode>\n" + 
				"               <responseservice:replyText>0</responseservice:replyText>\n" + 
				"            </responseservice:status>\n" + 
				"            <branchdetails>\n" + 
				"               <branchName>ADDANKI</branchName>\n" + 
				"               <cityName>ADDANKI</cityName>\n" + 
				"               <codBrn>3297</codBrn>\n" + 
				"               <state>Andhra Pradesh</state>\n" + 
				"            </branchdetails>\n" + 
				"            <branchdetails>\n" + 
				"               <branchName>AKIVIDU</branchName>\n" + 
				"               <cityName>AKIVIDU</cityName>\n" + 
				"               <codBrn>4333</codBrn>\n" + 
				"               <state>Andhra Pradesh</state>\n" + 
				"            </branchdetails>\n" + 
				"            <branchdetails>\n" + 
				"               <branchName>RAM NAGAR ANANTHAPUR</branchName>\n" + 
				"               <cityName>ANANTHAPUR</cityName>\n" + 
				"               <codBrn>4246</codBrn>\n" + 
				"               <state>Andhra Pradesh</state>\n" + 
				"            </branchdetails>\n" + 
				" <branchdetails>\n" + 
				"               <branchName>RAMBILASHPUR</branchName>\n" + 
				"               <cityName>fdfd</cityName>\n" + 
				"               <codBrn>3001</codBrn>\n" + 
				"               <state>West Bengal</state>\n" + 
				"            </branchdetails>\n" + 
				"         </return>";

		try {
			
			JSonParser jSonParser =  new JSonParser();
			org.json.simple.JSONObject jsonObject = JSonParser.XmltoJson(xmlResponse);
			jsonObject = jSonParser.printJsonObject(jsonObject);
			System.out.println("branchdetails ::"+jsonObject.get("branchdetails"));
			dbConnection();
			dbConnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dbConnection() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@192.168.0.100:1521:XE";
		String username = "MAHINDRAFINANCE";
		String password = "MAHINDRAFINANCE";
		try {
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			if (conn != null) {
		        System.out.println("Connected");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	public static void dbConnect() {
		
		PreparedStatement stmt = null;
		Connection connection=null;
		DBUtilities dbConObj=new DBUtilities(); 
		try {
			connection = dbConObj.getConnectionFromIP();
			if(connection!=null) {
				System.out.println("Connected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
