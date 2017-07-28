package com.core.http;

/**
 * @author Maruthamuthu
 *         This class is used to define a HTTP Constants
 */

public class HTTPConstants
{
	public static final int HTTP_READ = 0;
	public static final int HTTP_DELETE = 1;
	public static final int HTTP_CREATE = 2;
	public static final int HTTP_UPDATE = 3;

	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_DELETE = "DELETE";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_PUT = "PUT";

	public static final int HTTP_CONNECTION_TIME_OUT = 300000;
	public static final int HTTP_READ_TIME_OUT = 1000000;

	public static final int RESPONSE_TYPE_STRING = 0;
	public static final int RESPONSE_TYPE_JSON_OBJECT = 1;

	public static final String DEFAULT_USER_AGENT = "MM";
	public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";
}
