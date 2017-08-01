package com.zhuzi.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.zhuzi.pojo.User;
import com.zhuzi.service.UserService;
import com.zhuzi.util_jxl.ExcelException;
import com.zhuzi.util_poi.ExcelUtil;
/**
 * 
 * @author zhuzi
 * create 2017年7月27日16:01:46
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController{
	
	/**
	 * 登录
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,Model model,HttpSession session) throws Exception{
		logger.debug("===用户名==="+user.getName()+"====密码===="+user.getPwd());
		boolean login = userService.login(user);
		if(login){
			session.setAttribute("name", user.getName());
			return "/index";
		}
		return "/error";
	}
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
		return "/list";
	}
	/**
	 * 导出excel(POI网页版,可直接在浏览器下载)
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("excel_exportHtml")
	public String excel_exportHtml(User user,HttpServletResponse response) throws IOException{
		JSONArray jsonArray = new JSONArray();
		//获取所有用户列表
		List<User> userLists = userService.listUser();
		Map<String,String> headMap = new LinkedHashMap<String,String>();
		headMap.put("name","姓名");
		headMap.put("age","年龄");
		headMap.put("phone","手机");
		String title = "用户列表";
		//将list转为jsonArray
		for (Object object : userLists) {
            jsonArray.add(object);
        }
		ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response);
		return null;
	}
	
	/**
	 * 导出excel(高级版，针对于大量数据，可以分页)
	 * 
	 * 优点:07版以后的扩展名都是.xlsx ，是用新的基于XML的压缩文件格式取代了其目前专有的默认文件格式，
	 * 		在传统的文件名扩展名后面添加了字母x（即.docx取代.doc、.xlsx取代.xls，等等），使其占用空间更小，
	 * 		可以向下兼容xls
	 * 弊端:如果生成一个之后，再次生成的时候会覆盖。
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("excel_exportXlsx")
	public String excel_exportXlsx(User user,Model model) throws IOException{
		JSONArray jsonArray = new JSONArray();
		List<User> userLists = userService.listUser();
		Map<String,String> headMap = new LinkedHashMap<String,String>();
		headMap.put("name","姓名");
		headMap.put("age","年龄");
		headMap.put("phone","手机");
		String title = "用户列表";
		
		for (Object object : userLists) {
            jsonArray.add(object);
        }
		//高级版导出
		OutputStream out = new FileOutputStream("E://用户列表.xlsx");
		ExcelUtil.exportExcelX(title, headMap, jsonArray, null, 0, out);
		out.flush();
		out.close();
		String status = "OK";
		model.addAttribute("status",status);
		model.addAttribute("userLists",userLists);
		return "/list";
	}
	/**
	 * 导出excel(JXL网页版,可直接在浏览器下载)
	 * @param user
	 * @return
	 * @throws IOException 
	 * @throws ExcelException 
	 */
	@RequestMapping("excel_exportHtmlByJxl")
	public String excel_exportHtmlByJxl(User user,HttpServletResponse response) throws IOException, ExcelException{
		//获取所有用户列表
		List<User> userLists = userService.listUser();
		LinkedHashMap<String,String> headMap = new LinkedHashMap<String,String>();
		headMap.put("name","姓名");
		headMap.put("age","年龄");
		headMap.put("phone","手机");
		String sheet = "用户列表";
		com.zhuzi.util_jxl.ExcelUtil.listToExcel(userLists, headMap, sheet, response);
		return null;
	}
	/**
	 * 导出excel(jxl,导出到本地)
	 * @param user
	 * @return
	 * @throws IOException 
	 * @throws ExcelException 
	 */
	@RequestMapping("excel_exportXls")
	public String excel_exportXls(User user,Model model) throws IOException, ExcelException{
		
		List<User> userLists = userService.listUser();
		LinkedHashMap<String,String> headMap = new LinkedHashMap<String,String>();
		headMap.put("name","姓名");
		headMap.put("age","年龄");
		headMap.put("phone","手机");
		String sheetName = "用户列表";
		
		OutputStream out = new FileOutputStream("E://用户列表.xlsx");
		com.zhuzi.util_jxl.ExcelUtil.listToExcel(userLists, headMap, sheetName, out);
		out.flush();
		out.close();
		String status = "OK";
		model.addAttribute("status",status);
		model.addAttribute("userLists",userLists);
		return "/list";
	}
	@Autowired
	UserService userService;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
}
