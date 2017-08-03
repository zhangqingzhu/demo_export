package com.zhuzi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhuzi.pojo.User;
import com.zhuzi.service.UserService;
/**
 * 
 * @author zhuzi
 * create 2017年8月1日19:00:32
 *
 */
@Controller
@RequestMapping(value="/userByWord")
public class UserByWordController{
	
	/**
	 * 获取所有用户list
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		List<User> userLists = userService.listUser();
		model.addAttribute("userLists",userLists);
		logger.debug("查询所有用户:"+userLists);
		return "/list2";
	}
	/**
	 * 导出word
	 * @param user
	 * @return
	 */
	@RequestMapping(value="word_export",method=RequestMethod.GET)
	public String word_export(Model model){
		//获取所有用户列表
		List<User> userLists = userService.listUser();
	    userService.exportWord(userLists);
	    String status = "OK";
		model.addAttribute("status",status);
		model.addAttribute("userLists",userLists);
		return "/list2";
	}
	/**
	 * 导出word(网页版)
	 * @param user
	 * @return
	 */
	@RequestMapping(value="word_exportByHtml",method=RequestMethod.GET)
	public String word_exportByHtml(Model model,HttpServletResponse resp){
		//获取所有用户列表
		List<User> userLists = userService.listUser();
	    userService.exportWordByHtml(userLists, resp);
		return null;
	}
	@Autowired
	UserService userService;
	private static Logger logger = LoggerFactory.getLogger(UserByWordController.class);
}
