package com.core;

import java.util.Properties;

/**
 * @author Maruthamuthu
 *         This is used to initialize the properties for the whole project
 */

public class CoreProperties
{
	private static Properties coreProperties = new Properties();

	public static String get(String key)
	{
		return coreProperties.getProperty(key);
	}

	public static String get(String key, String defaultValue)
	{
		return coreProperties.getProperty(key, defaultValue);
	}

	public static Object set(String key, String value)
	{
		return coreProperties.setProperty(key, value);
	}
}
