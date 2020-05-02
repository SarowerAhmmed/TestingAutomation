package com.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectMap {

	public static String getconfig(String object)  {
		String file = "./Config.properties";// location
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			
		}// serilization

		Properties propeties = new Properties();// to read notepad

		try {
			propeties.load(fis);
		} catch (IOException e) {
		}// properties & serilization connect together
	String value=	propeties.getProperty(object);		
		
		return value;

	}

	
}
