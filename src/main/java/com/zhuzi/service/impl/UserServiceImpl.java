package com.zhuzi.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuzi.mapper.UserMapper;
import com.zhuzi.pojo.User;
import com.zhuzi.service.UserService;
import com.zhuzi.util_word.DocumentHandler;

@Service
public class UserServiceImpl implements UserService {

	public boolean login(User user) {
		boolean flag = false;
		User login = userMapper.login(user);
		if (login != null) {
			logger.debug("用户名密码正确");
			flag = true;
			return flag;
		}
		logger.debug("用户名密码错误");
		return flag;
	}

	public List<User> listUser() {
		List<User> userLists = userMapper.listUser();
		logger.debug("所有用户:" + userLists);
		return userLists;
	}

	public List<User> listByExcel() {
		List<User> users = userMapper.listByExcel();
		logger.debug("所有excel信息:" + users);
		return users;
	}

	/**
	 * 处理word相关参数
	 */
	@Override
	public String exportWord(List<User> userLists) {
		// word的报告名称采取UUID，随机生成
		String fileName = UUID.randomUUID().toString();
		StringBuilder realPath = new StringBuilder();
		realPath = realPath.append("E://").append(fileName).append(".doc");

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userLists", userLists);
		try {
			DocumentHandler.createDoc(dataMap, realPath.toString());
		} catch (UnsupportedEncodingException e) {
			logger.debug("调用word生成模版工具出错：" + e);
		}
		return null;
	}

	/**
	 * word(网页版)
	 */
	@Override
	public String exportWordByHtml(List<User> userLists,
			HttpServletResponse resp) {
		// word的报告名称采取UUID，随机生成
		String fileName = UUID.randomUUID().toString();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userLists", userLists);
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			DocumentHandler.createDocByHtml(dataMap, fileName,resp);
		} catch (UnsupportedEncodingException e) {
			logger.debug("调用word(网页版)生成模版工具出错："+e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Autowired
	private UserMapper userMapper;
	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
}
