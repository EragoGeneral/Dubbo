/*
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
package com.ubtechinc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 功能说明：OKHttp 的GET、POST、PUT、DELETE方法
 * 
 * OkHttpRestUtils.java
 * 
 * Original Author: deane.jia-贾亮亮,2017年9月18日上午10:57:58
 * 
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class OkHttpRestUtils {

	private static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public static OkHttpClient getClientInstance(){
		if(null == client ){
			synchronized (client) {
				if(null == client){
					 client = new OkHttpClient();
				}
			}
		}
		return client;
	}

	private static String prepareParams(Map<String,Object> params) throws UnsupportedEncodingException {
		StringBuilder strBuilder = new StringBuilder();
		if(params.isEmpty()) {
			return strBuilder.append("").toString();
		}else {
			Set<String> keys = params.keySet();
			for(String key :keys) {
				if(strBuilder.length() < 1) {
					if(params.get(key) instanceof String) {
						strBuilder.append(key).append("=").append(URLEncoder.encode((String)params.get(key),"UTF-8"));
					}else {
						strBuilder.append(key).append("=").append(params.get(key));
					}
					
				}else {
					if(params.get(key) instanceof String) {
						strBuilder.append("&").append(key).append("=").append(URLEncoder.encode((String)params.get(key),"UTF-8"));
					}else {
						strBuilder.append("&").append(key).append("=").append(params.get(key));
					}
					
				}
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 * Http GET 方法
	 * @param url
	 * @param paramsMap
	 * @return
	 * @throws IOException
	 */
	public static String get(String url,Map<String,Object> paramsMap)  {
		try {
			String urlParams = prepareParams(paramsMap);
			if(urlParams == null || urlParams.trim().length()<1) {
				
			}else {
				url += ("?" + urlParams);
			}
			
			Request request = new Request.Builder().url(url).get().build();
			Response response = getClientInstance().newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * HTTP POST 添加数据操作，不用验证
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String json) {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request = new Request.Builder().url(url).post(body).build();

			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * HTTP POST 添加数据操作
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String json, String tokenName, String tokenString)  {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request;
			if(null == tokenName || tokenName.trim().length() <1){
				 request = new Request.Builder().url(url).post(body).build();
			}else{
				 request = new Request.Builder().url(url).post(body).addHeader(tokenName, tokenString).build();
			}
			
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * HTTP PUT 更新数据操作
	 * @param url
	 * @param json
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String put(String url,String json, String tokenName,String tokenString) {
		try {
			RequestBody body = RequestBody.create(JSON, json);
			Request request;
			if(null == tokenName || tokenName.trim().length() <1){
				 request = new Request.Builder().url(url).put(body).build();
			}else{
				 request = new Request.Builder().url(url).put(body).addHeader(tokenName, tokenString).build();
			}
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * HTTP DELETE 删除数据操作
	 * @param url
	 * @param paramsMap
	 * @param tokenName
	 * @param tokenString
	 * @return
	 */
	public static String delete(String url,Map<String ,Object> paramsMap, String tokenName,String tokenString){
		try {
			String paramsUrl;
			paramsUrl = prepareParams(paramsMap);
			if(paramsUrl == null || paramsUrl.trim().length() <1) {
				
			}else {
				url += ("?" + prepareParams(paramsMap));
			}
			
			Request request;
			if(null == tokenName || tokenName.trim().length() <1){
				 request = new Request.Builder().url(url).delete().build();
			}else{
				 request = new Request.Builder().url(url).delete().addHeader(tokenName, tokenString).build();
			}
			//OKHTTP 访问，返回结果信息
			Response response = getClientInstance().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			
		}
		return null;
	}
}
