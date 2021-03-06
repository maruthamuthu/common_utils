package com.core.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.core.CoreException;
import com.core.ErrorConstants;
import com.sun.istack.internal.NotNull;

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
	private boolean canThrowExceptionForErrorResponse = true;
	private String userAgent;
	private String contentType;

	private int responseType = -1;

	public HTTPProperties()
	{
		params = new ArrayList<>();
		userAgent = HTTPConstants.DEFAULT_USER_AGENT;
		contentType = HTTPConstants.DEFAULT_CONTENT_TYPE;
	}

	public void addParameter(String key, Object value, boolean needAppendInBody) throws CoreException
	{
		addParameter(new HTTPParam(key, value, needAppendInBody));
	}

	public void addParameter(String key, Object value) throws CoreException
	{
		addParameter(new HTTPParam(key, value));
	}

	public void addParameter(HTTPParam param) throws CoreException
	{
		HTTPParam paramNeedToRemove = null;
		for(HTTPParam httpParam : params)
		{
			if(param.getName().equals(httpParam.getName()))
			{
				if(canThrowExceptionForDuplicateParams)
				{
					throw new CoreException(ErrorConstants.PARAMETER_CAN_NOT_BE_DUPLICATE, "Param name can't be duplicate");
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

	boolean canThrowExceptionForErrorResponse()
	{
		return canThrowExceptionForErrorResponse;
	}

	public void setThrowExceptionForErrorResponse(boolean canThrowExceptionForErrorResponse)
	{
		this.canThrowExceptionForErrorResponse = canThrowExceptionForErrorResponse;
	}

	private int getResponseType()
	{
		return responseType;
	}

	public void setResponseType(int responseType)
	{
		this.responseType = responseType;
	}

	private int getMethodType()
	{
		return method;
	}

	private List<HTTPParam> getParamList()
	{
		return params;
	}

	String getUrl()
	{
		return url;
	}

	public String getUserAgent()
	{
		return userAgent;
	}

	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public void setUrl(@NotNull String url) throws CoreException, MalformedURLException
	{
		if(url.indexOf('?') != -1)
		{
			parseParametersFromUrlString(url);
		}
		else
		{
			this.url = url;
		}
	}

	private void parseParametersFromUrlString(String url) throws CoreException, MalformedURLException
	{
		URL url1 = new URL(url);
		this.url = (new StringBuilder().append(url1.getProtocol()).append("://").append(url1.getHost()).append(url1.getPath())).toString();
		String[] parameters = url1.getQuery().split("&");
		for(String param : parameters)
		{
			if(param.contains("="))
			{
				checkAndAddToParameterList(param);
			}
		}
	}

	private void checkAndAddToParameterList(String parameter) throws CoreException
	{
		String[] keyVsValue = parameter.split("=");
		if(keyVsValue.length == 2)
		{
			addParameter(keyVsValue[0], keyVsValue[1]);
		}
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
		for(HTTPParam param : getParamList())
		{
			if((!needOnlyBodyParams && !param.isNeedAppendInBody()) || (needOnlyBodyParams && param.isNeedAppendInBody()))
			{
				sb.append(param.getName());
				sb.append(equal);
				sb.append(param.getValue());
				sb.append(separator);
			}
		}
		return sb.substring(0, (sb.length() > 0 ? sb.length() - 1 : 0));
	}

	String getMethodName()
	{
		switch(getMethodType())
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

	public Object handleResponseType(String response) throws CoreException
	{
		try
		{
			switch(getResponseType())
			{
				case HTTPConstants.RESPONSE_TYPE_STRING:
					return response;
				case HTTPConstants.RESPONSE_TYPE_JSON_OBJECT:
					return new JSONObject(response);
				default:
					return new JSONObject(response);
			}
		}
		catch(JSONException e)
		{
			throw new CoreException(e.getMessage());
		}
	}
}
