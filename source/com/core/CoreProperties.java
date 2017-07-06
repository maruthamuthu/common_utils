package com.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/*
    @author Maruthamuthu
	CoreProperties is a singleton class.
	This is used to initialize the properties
 */

public class CoreProperties extends Properties
{
	private static volatile CoreProperties coreProperties;

	/*
    	This constructor validates whether coreProperties is initialized or not. If already initialized then error thrown.
    	This check needed to keep this class is singleton and can't violate singleton using java reflection.
 	*/
	private CoreProperties() throws Exception
	{
		if(coreProperties != null)
		{
			throw new Exception("CoreProperties is a singleton class, so use getInstance() instead of new CoreProperties()");
		}
		checkAndLoadProperties();
	}

	/*
		The getInstance() is used to get the sole instance of CoreProperties class.
	 */
	public static CoreProperties getInstance() throws Exception
	{
		/* Checks coreProperties is already initialized or not*/
		if(coreProperties == null)
		{
			/* Getting lock for CoreProperties class*/
			synchronized(CoreProperties.class)
			{
				/* If one thread initializing the coreProperties and another one waiting for lock (both threads passed the first if check) then we need to
				* check null check again */

				if(coreProperties == null)
				{
					coreProperties = new CoreProperties();
				}
			}
		}
		return coreProperties;
	}

	/*
		The checkAndLoadProperties() is used to load the properties from core.properties file
		The core.properties is not mandatory, so that the file exists check added.
	 */
	private void checkAndLoadProperties() throws Exception
	{
		File propertyFile = new File("core.properties");
		if(propertyFile.exists())
		{
			InputStream propStream = null;
			try
			{
				propStream = new FileInputStream(propertyFile);
				this.load(propStream);
			}
			finally
			{
				if(propStream != null)
				{
					propStream.close();
				}
			}
		}
	}
}
