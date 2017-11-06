package com.yhaitao.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
	@Path("/reportBootInitiation")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public String initiation(String json) {
		LOGGER.info("start invoke initiation");
		// 响应结果 : resultMap一级，resultData二级
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultData = new HashMap<String, Object>();
		
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
		return GSON.toJson(resultMap);
	}
}
