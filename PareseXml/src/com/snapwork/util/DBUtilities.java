package com.snapwork.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DBUtilities
{
  public static volatile DBUtilities dbConnectionObj;
  public static final Logger logger = Logger.getLogger(DBUtilities.class
    .getName());

  public static synchronized DBUtilities dbUtilitilyObj()
  {
    if (dbConnectionObj == null) {
      dbConnectionObj = new DBUtilities();
    }
    return dbConnectionObj;
  }

  public static void closeConnecObj(Connection connection) {
    if (connection != null)
      try {
        connection.close();
      }
      catch (SQLException localSQLException)
      {
      }
      catch (Throwable localThrowable)
      {
      }
  }

  public static void closePreparedStatementObj(PreparedStatement preparedStatement)
  {
    if (preparedStatement != null)
      try {
        preparedStatement.close();
      }
      catch (SQLException localSQLException)
      {
      }
      catch (Throwable localThrowable)
      {
      }
  }

  public static void closeResultsetObj(ResultSet resultSet) {
    if (resultSet != null)
      try {
        resultSet.close();
      }
      catch (SQLException localSQLException)
      {
      }
      catch (Throwable localThrowable)
      {
      }
  }

  public static void closeStatement(Statement statement) {
    if (statement != null)
      try {
        statement.close();
      }
      catch (SQLException localSQLException)
      {
      }
      catch (Throwable localThrowable)
      {
      }
  }

  /*public Connection getConnectionFromIP()
  {
    Connection connection = null;
    try
    {
//    	System.out.println("connnection started ");
      Context context = new InitialContext();
      DataSource source = (DataSource)context.lookup("jdbc/WLR71"); //jdbc/WLSRPT61UAT // worklight 7 :jdbc/WLR71
      connection = source.getConnection();
//      System.out.println("connnection estabilished :"+connection);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return connection;
  }*/
  
  public Connection getConnectionFromIP(){
		 Connection connection=null;
		 try {
			    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//				logger.info("Connecting to the database...");
				
//				 String dbIpAddress="192.168.0.38";  
//				    String dbPort="1521";
//				    String dbUserName="system";
//				    String dbUserPassword="admin";
//				    String dbDataBaseName="XE";
			    
			    String dbIpAddress="192.168.0.11";  
			    String dbPort="1521";
			    String dbUserName="IDBI_BANK";
			    String dbUserPassword="IDBI_BANK_LOCAL";
			    String dbDataBaseName="XE";
			     //   connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dbIpAddress+":"+dbPort+":"+dbDataBaseName, dbUserName, dbUserPassword); 
				
	           // connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.17.93:1728:SGMORADB", "sgmusr", "sgmusr_123");  
				    connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dbIpAddress+":"+dbPort+":"+dbDataBaseName+"", dbUserName, dbUserPassword);
//	            logger.info("Connected to the database...Shiro");  
		
		} catch (Exception e) {
			 logger.info("exception :::>"+e);
		}
		 return connection;
	 }
}