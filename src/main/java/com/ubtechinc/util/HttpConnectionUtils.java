/*
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
package com.ubtechinc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * 功能说明： http Utils   GET  POST   DELETE PUT
 * 
 * HttpUtils.java
 * 
 * Original Author: deane.jia-贾亮亮,2017年9月15日下午4:33:35
 * 
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class HttpConnectionUtils {

	/**
	 * 
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	private static String prepareParam(Map<String, Object> paramsMap) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		if ( paramsMap ==null  || paramsMap.isEmpty()) {

		} else {
			Set<String> keys = paramsMap.keySet();
			for (String key : keys) {
				if (stringBuffer.length() < 1) {
					if (paramsMap.get(key) instanceof String) {
						stringBuffer.append(URLEncoder.encode(key, "UTF-8")).append("=")
								.append(URLEncoder.encode((String) paramsMap.get(key), "UTF-8"));
					}else{
						stringBuffer.append(key).append("=").append(paramsMap.get(key));
					}
				}else{
					if (paramsMap.get(key) instanceof String) {
						stringBuffer.append("&").append(URLEncoder.encode(key, "UTF-8")).append("=")
								.append(URLEncoder.encode((String) paramsMap.get(key), "UTF-8"));
					}else{
						stringBuffer.append("&").append(key).append("=").append(paramsMap.get(key));
					}
				}
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * GET 方法
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String get(String url, Map<String, Object> paramsMap) {
		StringBuffer strBuffer = new StringBuffer();
		try {
			String paramUrl =prepareParam(paramsMap);
			if(paramUrl == null || paramUrl.trim().length() <1){
				
			}else{
				url += ("?" + paramUrl);
			}
			URL restServiceURL = new URL(url );

			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json;charset:utf-8");

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET Request Failed with Error code : "
						+ httpConnection.getResponseCode());
			}
			//读取结果数据
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
			String output;
			while ((output = responseBuffer.readLine()) != null) {
				strBuffer.append(output);
			}
			httpConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}

		return strBuffer.toString();
	}
	
	/**
	 * Http Post
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String json)  {
		StringBuffer strBuffer = new StringBuffer();
		try {
			URL postURL = new URL(url);
			HttpURLConnection httpConnection;
			
			httpConnection = (HttpURLConnection) postURL.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(json.getBytes());
			outputStream.flush();
			
			if(httpConnection.getResponseCode() != 200  && httpConnection.getResponseCode() != 201) {
				throw new RuntimeException("Failed: HTTP error code :" + httpConnection.getResponseCode());
			}
			//读取结果数据
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String output ;
			while((output = responseBuffer.readLine()) != null) {
				strBuffer.append(output);
			}
			httpConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}
	
	/**
	 * Http Post
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 * @throws MalformedURLException 
	 * @throws IOException
	 */
	public static String post(String url,String json,String tokenName,String tokenString){
		StringBuffer stringBuffer = new StringBuffer();
		try {
			URL postURL = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) postURL.openConnection();
			
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConnection.setRequestProperty(tokenName, tokenString);
			
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(json.getBytes());
			outputStream.flush();
			
			if(httpConnection.getResponseCode() != 200  && httpConnection.getResponseCode() != 201) {
				throw new RuntimeException("Failed: HTTP error code" + httpConnection.getResponseCode());
			}
			//读取结果数据
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String output;
			while((output = responseReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			httpConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 * @throws IOException
	 */
	public static String put(String url,String json, String tokenName,String tokenString) throws IOException {
		URL urlPut = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) urlPut.openConnection();
		
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("PUT");
		httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		httpConnection.setRequestProperty(tokenName, tokenString);
		
		OutputStream outputStream = httpConnection.getOutputStream();
		outputStream.write(json.getBytes());
		outputStream.flush();
		
		if(httpConnection.getResponseCode() != 200 && httpConnection.getResponseCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
		}
		//读取结果数据
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		String output ;
		StringBuffer strBuilder = new StringBuffer();
		while((output = bufferReader.readLine())!= null) {
			strBuilder.append(output);
		}
		//断开连接
		httpConnection.disconnect();
		return strBuilder.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @param paramMap
	 * @param tokenName
	 * @param tokenString
	 * @return
	 * @throws IOException
	 */
	public static String delete(String url,Map<String ,Object> paramsMap, String tokenName,String tokenString) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String paramsUrl;
			paramsUrl = prepareParam(paramsMap);
			
			if(paramsUrl == null || paramsUrl.trim().length() <1) {
				
			}else {
				url += ("?" + prepareParam(paramsMap));
			}
			
			URL urlDelete = new URL(url);
			System.out.println(url);
			
			HttpURLConnection httpConnection = (HttpURLConnection) urlDelete.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("DELETE");
			httpConnection.setRequestProperty("Accept", "*/*");
			httpConnection.setRequestProperty(tokenName, tokenString);
			
			if(httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed: HTTP Delete failed with error code :" + httpConnection.getResponseCode());
			}
			//获得结果流
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()) );
			String output;
			
			while( (output = bufferReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			//断开连接
			httpConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
}
