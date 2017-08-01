package com.zhuzi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuzi.mapper.UserMapper;
import com.zhuzi.pojo.User;
import com.zhuzi.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	public boolean login(User user) {
		boolean flag = false;
		User login = userMapper.login(user);
		if(login!=null){
			logger.debug("用户名密码正确");
			flag = true;
			return flag;
		}
		logger.debug("用户名密码错误");
		return flag;
	}
	public List<User> listUser() {
		List<User> userLists = userMapper.listUser();
		logger.debug("所有用户:"+userLists);
		return userLists;
	}
	public List<User> listByExcel() {
		List<User> users = userMapper.listByExcel();
		logger.debug("所有excel信息:"+users);
		return users;
	}
	@Autowired
	private UserMapper userMapper;
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
}
