package com.core.http;

/**
 * @author Maruthamuthu
 * This class is used to initialise the HTTP Parameters
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

	public HTTPParam()
	{

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public boolean isNeedAppendInBody()
	{
		return needAppendInBody;
	}

	public void setNeedAppendInBody(boolean needAppendInBody)
	{
		this.needAppendInBody = needAppendInBody;
	}
}
