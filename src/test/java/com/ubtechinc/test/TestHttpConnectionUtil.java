/*
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
package com.ubtechinc.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ubtechinc.util.HttpConnectionUtils;

/**
 * 功能说明：
 * 
 * TestHttpUtil.java
 * 
 * Original Author: deane.jia-贾亮亮,2017年9月15日下午4:47:43
 * 
 * Copyright (C)2012-2017 深圳优必选科技 All rights reserved.
 */
public class TestHttpConnectionUtil {

	@Test
    public  void testGet() {
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("name", "18820602060");
    	paramMap.put("password", "123456");
    	String result = HttpConnectionUtils.get("https://test79.ubtrobot.com/user-service-rest/openapi/token", paramMap);
    	System.out.println(result);

	}
	
	@Test
	public void testPost(){
    	//2、Header中验证token的情况
		String url2 = "https://test79.ubtrobot.com/user-service-rest/user/logout";
		String json2 ="";
		System.out.println(HttpConnectionUtils.post(url2,json2,"authorization","729b20ccc71a4af29ceab162e9d1569d12m"));
	}
}
