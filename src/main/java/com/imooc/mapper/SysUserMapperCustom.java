package com.imooc.mapper;

import java.util.List;
import java.util.Map;

import com.imooc.pojo.SysUser;

public interface SysUserMapperCustom {
	
	List<SysUser> queryUserSimplyInfoById(String id);

	SysUser queryUserSimplyInfo(String username, String password);

	List<SysUser> queryUserInfo(Map<String, String> param);

	void updateUserInfo(Map<String, String> params);
}