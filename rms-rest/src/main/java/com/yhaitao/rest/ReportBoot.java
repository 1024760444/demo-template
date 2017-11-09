package com.yhaitao.rest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 上报请求
 * 
 * @author admin
 *
 */
@Path("/")
public class ReportBoot {
	/**
	 * 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ReportBoot.class);
	
	/**
	 * 数据格式化工具
	 */
	private final static Gson GSON = new Gson();
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@POST
	@Path("/reportBootInitiation")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response initiation(@Context HttpHeaders headers, @QueryParam("token") String token, String json) {
		LOGGER.info("start invoke initiation");
		// 响应结果 : resultMap一级，resultData二级
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultData = new HashMap<String, Object>();
		
		System.err.println("token : " + token);
		
		MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
		Iterator<Entry<String, List<String>>> iterator = requestHeaders.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, List<String>> next = iterator.next();
			System.err.println(next.getKey() + " : " + next.getValue());
		}
		
		// 解析数据
		@SuppressWarnings("unchecked")
		Map<String, Object> inputMap = GSON.fromJson(json, Map.class);
		Object id = inputMap.get("ID");
		String cmdType = inputMap.containsKey("CmdType") ? String.valueOf(inputMap.get("CmdType")) : null;
		String sequenceId = inputMap.containsKey("SequenceId") ? String.valueOf(inputMap.get("SequenceId")) : null;
		String parameter = inputMap.containsKey("Parameter") ? GSON.toJson(inputMap.get("Parameter")) : null;
		
		LOGGER.info("initiation save parameter : {}.", parameter);
		if (id == null || cmdType == null || sequenceId == null || parameter == null) {
			resultMap.put("Result", -200);
			resultMap.put("ID", id);
			resultMap.put("CmdType", cmdType);
			resultMap.put("SequenceId", sequenceId);
			resultData.put("Desc", "参数不全：  ID, CmdType, SequenceId或者Parameter为空");
		} else {
			@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = GSON.fromJson(parameter, Map.class);
			String mac = paramMap.containsKey("MAC") ? String.valueOf(paramMap.get("MAC")) : null;
			String password = paramMap.containsKey("Password") ? String.valueOf(paramMap.get("Password")) : null;
			String province_code = paramMap.containsKey("Province_code") ? String.valueOf(paramMap.get("Province_code"))
					: null;

			// 信息处理
			LOGGER.info("initiation save parameterMap : {}.", paramMap);

			if (mac == null || password == null || province_code == null) {
				resultMap.put("Result", -200);
				resultMap.put("ID", id);
				resultMap.put("CmdType", cmdType);
				resultMap.put("SequenceId", sequenceId);
				resultData.put("Desc", "参数不全：  MAC, Password或者Province_code为空");
			} else {
				if ("-1".equals(mac)) {
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					resultMap.put("Result", -300);
					resultMap.put("ID", id);
					resultMap.put("CmdType", cmdType);
					resultMap.put("SequenceId", sequenceId);
					resultData.put("Desc", "超时请求： 等待30秒");
				} else {
					resultMap.put("Result", 0);
					resultMap.put("ID", id);
					resultMap.put("CmdType", cmdType);
					resultMap.put("SequenceId", sequenceId);
					resultData.put("Desc", "上报信息接收成功");
				}
			}
		}
		
		// 响应设置
		resultMap.put("ResultData", resultData);
		Response response = Response.ok().entity(GSON.toJson(resultMap))
				.cookie(NewCookie.valueOf("id=" + "1234"))
				.cookie(NewCookie.valueOf("domain="+ "www.yhaitao.net"))
				.cookie(NewCookie.valueOf("path=/"))
				.expires(Calendar.getInstance().getTime())
				.build();
		return response;
	}
}
