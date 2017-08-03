package com.zhuzi.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	/**
	 * 导出word
	 * @param userLists
	 * @return
	 */
	String exportWord(List<User> userLists);
	/**
	 * 导出word(网页版)
	 * @param userLists
	 * @return
	 */
	String exportWordByHtml(List<User> userLists,HttpServletResponse resp);
	
}
