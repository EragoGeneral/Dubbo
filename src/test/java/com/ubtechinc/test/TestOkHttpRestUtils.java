/*
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
package com.ubtechinc.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ubtechinc.util.OkHttpRestUtils;

/**
 * 功能说明：
 * 
 * TestOkHttpRestUtils.java
 * 
 * Original Author: deane.jia-贾亮亮,2017年9月18日下午2:26:21
 * 
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class TestOkHttpRestUtils {
	
	@Test
	public void testGet(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("name", "18820602060");
    	paramMap.put("password", "123456");
		String result = OkHttpRestUtils.get("https://test79.ubtrobot.com/user-service-rest/openapi/token",  paramMap);
		System.out.println(result);
	}
	
	@Test
	public void testPost(){
		//2、Header中验证token的情况
		String url2 = "https://test79.ubtrobot.com/user-service-rest/user/logout";
		String json2 ="";
		String result = OkHttpRestUtils.post(url2, json2,"authorization","d94996f5d9d74477867e95229a4ae7d712m");
		System.out.println(result);
	}
}
