package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpFormUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpFormUtil.class);

	public static String sendFormData(String url, String data,String token) {
		return sendFormData(url, data, 30,token);
	}

	public static String sendFormData(String url, String data, int timeOut,String token) {
		try {
			HttpClient httpclient = HttpClients.createDefault();

			RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(1000 * timeOut)
					.setConnectTimeout(1000 * timeOut).setConnectionRequestTimeout(1000 * timeOut).build();

			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("charset", "UTF-8");
			httpPost.addHeader("Authorization","Bearer "+token);
			httpPost.addHeader("platform", "ios");
			httpPost.addHeader("version", "1");
			httpPost.setConfig(defaultRequestConfig);
			List urlParameters = new ArrayList();
			JSONObject jObject = JSON.parseObject(data);
			for (Map.Entry entity : jObject.entrySet()) {
				urlParameters.add(new BasicNameValuePair((String) entity.getKey(), (String) entity.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

			HttpResponse httpResponse = httpclient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String content = EntityUtils.toString(httpEntity);
			return content;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
