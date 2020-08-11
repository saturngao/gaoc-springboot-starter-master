package com.imooc.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.pojo.*;
import com.imooc.service.SysDoorCountService;
import com.imooc.service.UserService;
import com.imooc.utils.JsonUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@Controller		// @RestController = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserContoller {
	@Autowired
	private UserService userService;
	@Autowired
	private SysDoorCountService sysDoorCountService;
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public IMoocJSONResult getUserJson(String password, String id) {
		val stringStringHashMap = new HashMap<String, String>();
		stringStringHashMap.put("id", id);
		stringStringHashMap.put("password", password);
		userService.updateUser(stringStringHashMap);
		return IMoocJSONResult.ok();
	}

	@RequestMapping("jump/update/{userId}")
	public String jumpUser(ModelMap map, @PathVariable("userId") String userId) {
		map.addAttribute("userId", userId);
		return "thymeleaf/change_pwd";
	}

	@RequestMapping("jump/log/{userId}")
	public String jumpUserLog(ModelMap map, @PathVariable("userId") String userId) {

		val param = new HashMap<String, String>();
		param.put("userId", userId);
		PageInfo<SysCountRecord> list = sysDoorCountService.getAllRecordByCon(param);
		map.addAttribute("record", list);
		return "thymeleaf/see_log";
	}

	@RequestMapping("jump/info")
	@ResponseBody
	public IMoocJSONResult jumpInfo(ModelMap map) {
		PageInfo<SysCountRecord> list = sysDoorCountService.getAllRecordByCon(new HashMap<String, String>());
		return IMoocJSONResult.ok(list);
	}

	@RequestMapping("login")
	public String loginAction(ModelMap map,String username, String password) {
//		map.addAttribute("resource", resource);
		SysUser u = userService.queryUser(username, password);
		if (null != u){
			if (u.getUsername().equals("admin")){
				List<SysUser> sysUsers = userService.queryUserInfo(new HashMap<String, String>());
				map.addAttribute("sysUsers", sysUsers);
				return "thymeleaf/admin_index";
			} else {
				List<Map<String, String>> list = sysDoorCountService.getCodeList();
				map.addAttribute("codeList", list);
				map.addAttribute("userId", u.getId());
				return "thymeleaf/index";
			}
		}
		return "thymeleaf/login";
	}

	@RequestMapping("count")
	@ResponseBody
	public IMoocJSONResult count(ModelMap map,SysDoorCount doorCount) {
		SysDoorCount sysDoorCount = sysDoorCountService.getSysDoorCount(doorCount.getId());
		sysDoorCount.setUserId(doorCount.getUserId());
		SysCountRecord sysCountRecord = new SysCountRecord();
		sysCountRecord.setUserId(doorCount.getUserId());

		BigDecimal qH = new BigDecimal(doorCount.getCH()).multiply(new BigDecimal(sysDoorCount.getCH()));
		BigDecimal qC = new BigDecimal(doorCount.getCC()).multiply(new BigDecimal(sysDoorCount.getCC()));
		BigDecimal qLH = new BigDecimal(doorCount.getCLH()).multiply(new BigDecimal(sysDoorCount.getCLH()));
		BigDecimal qLC = new BigDecimal(doorCount.getCLC()).multiply(new BigDecimal(sysDoorCount.getCLC()));
		BigDecimal qYH = new BigDecimal(doorCount.getCYH()).multiply(new BigDecimal(sysDoorCount.getCYH()));
		BigDecimal qYC = new BigDecimal(doorCount.getCYC()).multiply(new BigDecimal(sysDoorCount.getCYC()));
		BigDecimal result = new BigDecimal(sysDoorCount.getB()).add(qH).add(qC).add(qLH).add(qLC).add(qYH).add(qYC);
		sysCountRecord.setTotalFee(result.toString());
		sysCountRecord.setTotalCount(JsonUtils.objectToJson(sysDoorCount));
		sysDoorCountService.saveSysCount(sysCountRecord);
		return IMoocJSONResult.ok(result.toString());
	}

	@RequestMapping("query")
	@ResponseBody
	public IMoocJSONResult query(ModelMap map,String doorId) {
		SysDoorCount sysDoorCount = sysDoorCountService.getSysDoorCount(doorId);
		return IMoocJSONResult.ok(JsonUtils.objectToJson(sysDoorCount));
	}

	@RequestMapping("del/{userId}")
	@ResponseBody
	public IMoocJSONResult delUser(ModelMap map, @PathVariable("userId") String userId) {
		userService.deleteUser(userId);
		return IMoocJSONResult.ok();
	}

	@RequestMapping("record/{userId}")
	@ResponseBody
	public IMoocJSONResult record(ModelMap map, @PathVariable("userId") String userId) {
		val param = new HashMap<String, String>();
		param.put("userId", userId);
		PageInfo<SysCountRecord> list = sysDoorCountService.getAllRecordByCon(param);
		return IMoocJSONResult.ok(list);
	}
}
