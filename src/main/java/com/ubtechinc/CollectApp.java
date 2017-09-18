package com.ubtechinc;

//import com.ubtechinc.util.HttpConnectionUtils;
import com.ubtechinc.util.OkHttpRestUtils;

/**
 * 
 * 功能说明：
 * 
 * App.java
 * 
 * Original Author: deane.jia-贾亮亮,2017年9月15日下午6:02:12
 * 
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class CollectApp {

	public static final String RESTAPI_PLATFORMREQUEST = "http://10.10.23.79:8085/uPlatformRequest/addOrUpdate";

	public static void main(String[] args) {

//		String strJson = "[{\"createTime\": \"2017-09-18T06:16:44.876Z\", \"id\": 0,\"platformName\": \"talkshow\",\"remark\": \"talkShow\", \"requestDate\": \"2017-09-18T06:16:44.876Z\",\"requestTimes\": 0, \"robotId\": \"333111\",\"robotTypeId\": 0,\"updateTime\": \"2017-09-18T06:16:44.876Z\"}]";
//		String result = HttpConnectionUtils.post(RESTAPI_PLATFORMREQUEST, strJson);
//		System.out.println(result);
		
		String strJson = "[{\"createTime\": \"2017-09-18T06:16:44.876Z\", \"id\": 0,\"platformName\": \"talkshow\",\"remark\": \"talkShow\", \"requestDate\": \"2017-09-18T06:16:44.876Z\",\"requestTimes\": 0, \"robotId\": \"111333\",\"robotTypeId\": 0,\"updateTime\": \"2017-09-18T06:16:44.876Z\"}]";
		String result = OkHttpRestUtils.post(RESTAPI_PLATFORMREQUEST, strJson);
		System.out.println(result);
	}
}
