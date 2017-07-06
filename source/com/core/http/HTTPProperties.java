package com.core.http;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

/**
 * @author Maruthamuthu
 *         This class is used to handle the list the HTTP Parameters
 */

public class HTTPProperties
{
	private List<HTTPParam> params;
	private String url;
	private int method;
	private boolean canThrowExceptionForDuplicateParams = true;

	public HTTPProperties()
	{
		params = new ArrayList<>();
	}

	public void setParameter(String key, Object value, boolean needAppendInBody) throws Exception
	{
		addParameter(new HTTPParam(key, value, needAppendInBody));
	}

	public void setParameter(String key, Object value) throws Exception
	{
		addParameter(new HTTPParam(key, value));
	}

	public void addParameter(HTTPParam param) throws Exception
	{
		HTTPParam paramNeedToRemove = null;
		for(HTTPParam httpParam : params)
		{
			if(param.getName().equals(httpParam.getName()))
			{
				if(canThrowExceptionForDuplicateParams)
				{
					throw new InvalidValue("Param name can't be duplicate");
				}
				paramNeedToRemove = httpParam;
				break;
			}
		}

		if(paramNeedToRemove == null)
		{
			params.add(param);
		}
		else
		{
			params.remove(paramNeedToRemove);
			params.add(param);
		}
	}

	String getBodyParametersAsString()
	{
		return getParametersAsString(true);
	}

	String getURLParametersAsString()
	{
		return getParametersAsString(false);
	}

	public void setMethodAsHTTPGet()
	{
		this.method = HTTPConstants.HTTP_READ;
	}

	public void setMethodAsHTTPPost()
	{
		this.method = HTTPConstants.HTTP_CREATE;
	}

	public void setMethodAsHTTPPut()
	{
		this.method = HTTPConstants.HTTP_UPDATE;
	}

	public void setMethodAsHTTPDelete()
	{
		this.method = HTTPConstants.HTTP_DELETE;
	}

	public void setThrowExceptionForDuplicateParams(boolean canThrowExceptionForDuplicateParams)
	{
		this.canThrowExceptionForDuplicateParams = canThrowExceptionForDuplicateParams;
	}

	String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	String getURLStringForInvoke()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getUrl());
		sb.append('?');
		sb.append(getParametersAsString(false));
		return sb.toString();
	}

	private String getParametersAsString(boolean needOnlyBodyParams)
	{
		StringBuilder sb = new StringBuilder();
		char separator = '&';
		char equal = '=';
		for(HTTPParam param : params)
		{
			if((!needOnlyBodyParams && !param.isNeedAppendInBody()) || (needOnlyBodyParams && param.isNeedAppendInBody()))
			{
				sb.append(param.getName());
				sb.append(equal);
				sb.append(param.getValue());
				sb.append(separator);
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

	String getMethodName()
	{
		switch(this.method)
		{
			case HTTPConstants.HTTP_READ:
				return HTTPConstants.HTTP_METHOD_GET;
			case HTTPConstants.HTTP_DELETE:
				return HTTPConstants.HTTP_METHOD_DELETE;
			case HTTPConstants.HTTP_CREATE:
				return HTTPConstants.HTTP_METHOD_POST;
			case HTTPConstants.HTTP_UPDATE:
				return HTTPConstants.HTTP_METHOD_PUT;
			default:
				return HTTPConstants.HTTP_METHOD_GET;
		}
	}
}
