package com.constant;

public class ConnectionConstants {
   
	public static enum EnumConstans  {
		Hikari
	};
        
        
       
	public static final EnumConstans connectionPool = EnumConstans.Hikari;
	public static final String databaseName = "conexion";
	public static final String databaseLogin = "conexion";
	public static final String databasePassword = "control";
	public static final String databasePort = "3306";
	public static final String databaseHost = "desarrollo.gadesl.com";
	public static final int getDatabaseMaxPoolSize = 10;
	public static final int getDatabaseMinPoolSize = 5;

	public static String getConnectionChain() {
		return "jdbc:mysql://" + ConnectionConstants.databaseHost + ":" + ConnectionConstants.databasePort + "/"
				+ ConnectionConstants.databaseName;
	}

}
