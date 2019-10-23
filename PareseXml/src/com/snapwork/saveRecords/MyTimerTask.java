package com.snapwork.saveRecords;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class MyTimerTask extends TimerTask {
	public final static Logger logger = Logger.getLogger(MyTimerTask.class.getName());
	private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;

	// private final static int ONE_DAY = 1;
	private final static int TWO_AM = 11;
	private final static int ZERO_MINUTES = 37;

	// Auditloggers auditloggers=new Auditloggers();
	String billerData = "";

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// long currennTime = System.currentTimeMillis();
		int cnt = 0;
		// boolean flag = false;
		String flag = "";
		Date nowDt = new java.util.Date();
		// logger.info("current time" + nowDt);
		String xmlResponse="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
				"   <S:Body>\n" + 
				"      <ns0:doCompanyMasterAuditResponse xmlns:ns0=\"http://inquiry.service.cs.appx.cz.fc.ofss.com/\" xmlns:responseservice=\"http://response.service.fc.ofss.com\" xmlns:exceptioninfra=\"http://exception.infra.fc.ofss.com\" xmlns:datatype=\"http://datatype.fc.ofss.com\" xmlns:errorvalidationinfra=\"http://error.validation.infra.fc.ofss.com\" xmlns:ns1=\"http://dto.common.domain.framework.fc.ofss.com\" xmlns:contextapp=\"http://context.app.fc.ofss.com\" xmlns:domainframework=\"http://domain.framework.fc.ofss.com\">\n" + 
				"         <return>\n" + 
				"            <responseservice:status>\n" + 
				"               <responseservice:errorCode>0</responseservice:errorCode>\n" + 
				"               <responseservice:extendedReply/>\n" + 
				"               <responseservice:externalReferenceNo>1405201902</responseservice:externalReferenceNo>\n" + 
				"               <responseservice:internalReferenceNumber>2019140057909151</responseservice:internalReferenceNumber>\n" + 
				"               <responseservice:isOverriden>false</responseservice:isOverriden>\n" + 
				"               <responseservice:postingDate>\n" + 
				"                  <datatype:dateString>20190520000000</datatype:dateString>\n" + 
				"               </responseservice:postingDate>\n" + 
				"               <responseservice:replyCode>0</responseservice:replyCode>\n" + 
				"               <responseservice:replyText>0</responseservice:replyText>\n" + 
				"            </responseservice:status>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>SDFGFSDGFD</companyCode>\n" + 
				"               <companyName>FASNF</companyName>\n" + 
				"               <flagChangeIndicator>A</flagChangeIndicator>\n" + 
				"               <rowNum>1</rowNum>\n" + 
				"               <uploadType>1</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>7894</companyCode>\n" + 
				"               <companyName>TEST_1491</companyName>\n" + 
				"               <flagChangeIndicator>A</flagChangeIndicator>\n" + 
				"               <rowNum>2</rowNum>\n" + 
				"               <uploadType>1</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>8199</companyCode>\n" + 
				"               <companyName>jain</companyName>\n" + 
				"               <flagChangeIndicator>M</flagChangeIndicator>\n" + 
				"               <rowNum>3</rowNum>\n" + 
				"               <uploadType>2</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>MAYUR13</companyCode>\n" + 
				"               <companyName>jain</companyName>\n" + 
				"               <flagChangeIndicator>M</flagChangeIndicator>\n" + 
				"               <rowNum>4</rowNum>\n" + 
				"               <uploadType>2</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>VIRAT987</companyCode>\n" + 
				"               <companyName>jain</companyName>\n" + 
				"               <flagChangeIndicator>M</flagChangeIndicator>\n" + 
				"               <rowNum>5</rowNum>\n" + 
				"               <uploadType>2</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>18011960</companyCode>\n" + 
				"               <companyName>jain</companyName>\n" + 
				"               <flagChangeIndicator>D</flagChangeIndicator>\n" + 
				"               <rowNum>6</rowNum>\n" + 
				"               <uploadType>2</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <companyMasterAuditDetails>\n" + 
				"               <companyCode>8527</companyCode>\n" + 
				"               <companyName>Test_1491_1</companyName>\n" + 
				"               <flagChangeIndicator>A</flagChangeIndicator>\n" + 
				"               <rowNum>7</rowNum>\n" + 
				"               <uploadType>1</uploadType>\n" + 
				"            </companyMasterAuditDetails>\n" + 
				"            <flagAdditionalData>N</flagAdditionalData>\n" + 
				"            <flagEODRun>Y</flagEODRun>\n" + 
				"         </return>\n" + 
				"      </ns0:doCompanyMasterAuditResponse>\n" + 
				"   </S:Body>\n" + 
				"</S:Envelope>";

		try {
			flag = new SaveUserDetails().saveSelfeeCompDtls(xmlResponse);
			/*
			 * if(flag == true){ logger.info("SUCCESS");
			 * 
			 * }else{ logger.info("Failure"); }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Date getTomorrowMorning2AM() {

		Date date2am = new java.util.Date();
		date2am.setHours(TWO_AM);
		date2am.setMinutes(ZERO_MINUTES);
		// logger.info("tomorow date" + date2am);
		return date2am;
	}

	// call this method from your servlet init method
	public static void startTask() {
		// logger.info("task started ");
		MyTimerTask task = new MyTimerTask();
		Timer timer = new Timer();
		timer.schedule(task, getTomorrowMorning2AM(), ONCE_PER_DAY);// for your case u need to give 1000*60*60*24
	}

	/*
	 * public static void main(String args[]){ startTask();
	 * 
	 * }
	 */
	/*
	 * static{ System.out.println("Static block execution:"); startTask(); }
	 */
	public static void main(String[] args) {
		MyTimerTask myTimerTask=new MyTimerTask();
//		myTimerTask.run();
//		myTimerTask.getTomorrowMorning2AM();
//		myTimerTask.startTask();
		
	}
}
