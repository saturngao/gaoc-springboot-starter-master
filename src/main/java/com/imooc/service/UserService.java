package com.imooc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.imooc.pojo.SysCountRecord;
import com.imooc.pojo.SysUser;

public interface UserService {

	void saveUser(SysUser user) throws Exception;

	void updateUser(Map<String, String> param);

	void deleteUser(String userId);

	SysUser queryUserById(String userId);

	List<SysUser> queryUserList(SysUser user);

	List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

	SysUser queryUserByIdCustom(String userId);
	
	void saveUserTransactional(SysUser user);

	SysUser queryUser(String userId, String password);

	PageInfo<SysUser> queryUserInfo(Map<String, String> param);
}
