package com.core.http;

/**
 * @author Maruthamuthu
 *         This class is used to initialise the HTTP Parameters
 */

public class HTTPParam
{
	private String name;
	private Object value;
	private boolean needAppendInBody;

	public HTTPParam(String name, Object value, boolean needAppendInBody)
	{
		this.name = name;
		this.value = value;
		this.needAppendInBody = needAppendInBody;
	}

	public HTTPParam(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public Object getValue()
	{
		return value;
	}

	public boolean isNeedAppendInBody()
	{
		return needAppendInBody;
	}

}
