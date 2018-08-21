package com.demo.controller;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class OpenAccountResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ret;// 返回结果码

	private String msg;// 返回消息

	private T data; // 返回数据

	public OpenAccountResponse() {
		this.data = (T) new HashMap<>();
	}

	public OpenAccountResponse(T dataSet) {
		this.data = dataSet;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
