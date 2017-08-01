package com.zhuzi.service;

import java.util.List;

import com.zhuzi.pojo.User;

public interface UserService {
	/**
	 * 登录判断
	 * @param user
	 * @return
	 */
	boolean login(User user);
	/**
	 * 查询所有user
	 * @return
	 */
	List<User> listUser();
	/**
	 * 查询信息
	 * @return
	 */
	List<User> listByExcel();
}
