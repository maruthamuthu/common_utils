package com.core.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class Connector
{
	private static Logger logger = Logger.getLogger(Connector.class.getName());


	public static JSONObject invoke(HTTPProperties httpProperties) throws Exception
	{
		URL url = new URL(httpProperties.getURLStringForInvoke());
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod(httpProperties.getMethodName());
		connection.setRequestProperty("User-Agent", "MM");
		connection.setConnectTimeout(HTTPConstants.HTTP_CONNECTION_TIME_OUT);
		connection.setReadTimeout(HTTPConstants.HTTP_READ_TIME_OUT);
		String params = httpProperties.getBodyParametersAsString();

		if(StringUtils.isNotEmpty(params))
		{
			byte[] postData = params.getBytes("UTF-8");
			connection.setRequestProperty("charset", "UTF-8");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			try(DataOutputStream wr = new DataOutputStream(connection.getOutputStream()))
			{
				wr.write(postData);
				wr.flush();
			}
		}

		int returnCode = connection.getResponseCode();
		if(returnCode != HttpsURLConnection.HTTP_OK && returnCode != HttpsURLConnection.HTTP_CREATED)
		{
			String output = convertStreamToString(connection.getErrorStream());
			logger.log(Level.SEVERE, "Error while invoking API - {0}", output);
			throw new Exception(output);
		}

		String output = convertStreamToString(connection.getInputStream());
		return new JSONObject(output);
	}

	private static String convertStreamToString(InputStream in) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		String content = null;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
		{
			while((content = reader.readLine()) != null)
			{
				sb.append(content);
			}
		}
		return sb.toString();
	}
}
