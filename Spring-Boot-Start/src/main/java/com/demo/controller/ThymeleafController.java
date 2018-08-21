package com.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/th/")
public class ThymeleafController {

	/**
	 * 存管开户页面
	 * @param map
	 * @param response
	 * @return http://localhost:9518/th/index
	 */
	@RequestMapping("/index")
    public String index(ModelMap map,HttpServletResponse response) {
        map.addAttribute("name", "thymeleaf-imooc");
        return "thymeleaf/index";
    }
	
	@RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }
	
	/**
	 * 存管开户
	 * @param openAccountUrl
	 * @param token
	 * @return
	 */
	@RequestMapping("/openAccount")	
	@ResponseBody
    public JSONObject openAccount(String openAccountUrl,String uid ,String token) {
		JSONObject  accountRequest=new JSONObject();
		accountRequest.put("uid",uid);
		String resp = HttpFormUtil.sendFormData(openAccountUrl,accountRequest.toJSONString(),token);
		System.out.println("存管开户返回数据:"+resp);
		JSONObject  contentResp	=JsonUtil.fromJson(resp, JSONObject.class);
        return  contentResp;
    }
	
	/**
	 * 存管开户回调页面路径
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/openAccountCallbackUrl")
    public String openAccountCallbackUrl(String transId,String resultCode,String resultMsg) throws IOException {
		System.out.println("openAccountCallbackUrl");
//        System.out.println("存管开户通知参数"+jsonObject);
		
        return "thymeleaf/center/openAccountCallbackUrl";
    }
	
	/**
	 * 存管激活页面
	 * @param map
	 * @param response
	 * @return http://localhost:9518/th/activeAccountPage
	 */
	@RequestMapping("/activeAccountPage")
    public String activeAccount(ModelMap map,HttpServletResponse response) {
        map.addAttribute("name", "thymeleaf-imooc");
        return "thymeleaf/activeAccount";
    }
	
	/**
	 * 存管开户激活
	 * @param activeAccountUrl
	 * @param token
	 * @return
	 */
	@RequestMapping("/activeAccount")	
	@ResponseBody
    public JSONObject activeAccount(String activeAccountUrl,String cardNo,String token) {
		JSONObject  accountRequest=new JSONObject();
		accountRequest.put("cardNo",cardNo);
		String resp = HttpFormUtil.sendFormData(activeAccountUrl,accountRequest.toJSONString(),token);
		System.out.println("存管开户激活返回数据:"+resp);
		JSONObject  contentResp	=JsonUtil.fromJson(resp, JSONObject.class);
        return  contentResp;
    }
	
	/**
	 * 存管开户激活回调页面路径
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/activeAccountCallbackUrl")
    public String activeAccountCallbackUrl(String transId,String resultCode,String resultMsg) throws IOException {
		System.out.println("openAccountCallbackUrl");
//	    System.out.println("存管开户激活回调页面路径参数"+jsonObject);

        return "thymeleaf/center/activeAccountCallbackUrl";
    }
	
	/**
	 * 存管提现页面
	 * @param map
	 * @param response
	 * @return http://localhost:9518/th/withdrawPage
	 */
	@RequestMapping("/withdrawPage")
    public String withdraw(ModelMap map,HttpServletResponse response) {
        map.addAttribute("name", "thymeleaf-imooc");
        return "thymeleaf/withdraw";
    }
	
	/**
	 * 存管开户提现
	 * @param activeAccountUrl
	 * @param token
	 * @return
	 */
	@RequestMapping("/withdraw")	
	@ResponseBody
    public JSONObject withdraw(String withdrawUrl,String cardNo,String token) {
		JSONObject  accountRequest=new JSONObject();
		accountRequest.put("certNo",cardNo);
		accountRequest.put("amount","1000");
		accountRequest.put("callbackUrl","http://10.15.107.54:9518/th/withdrawCallbackUrl");
		String resp = HttpFormUtil.sendFormData(withdrawUrl,accountRequest.toJSONString(),token);
		System.out.println("存管开户提现返回数据:"+resp);
		JSONObject  contentResp	=JsonUtil.fromJson(resp, JSONObject.class);
        return  contentResp;
    }
	
	/**
	 * 存管开户提现回调页面路径
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/withdrawCallbackUrl")
    public String withdrawCallbackUrl(String transId,String resultCode,String resultMsg) throws IOException {
		System.out.println("withdrawCallbackUrl");
//	    System.out.println("存管开户激活回调页面路径参数"+jsonObject);

        return "thymeleaf/center/withdrawCallbackUrl";
    }

}