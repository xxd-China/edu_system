package com.edu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.common.MD5Utils;
import com.edu.pojo.Userlogin;

/**
 * xxd
 * 登录
 */
@Controller
public class LoginController {

	// 跳回登录页
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String toIndex() {

		return "/../../login";
	}

	// 登录表单处理
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(Userlogin userlogin) throws Exception {

		// Shiro实现登录
		Subject subject = SecurityUtils.getSubject();// 获得当前用户对象，状态为"未认证"
		// 创建用户名密码令牌对象
		UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(), MD5Utils.md5(userlogin.getPassword()));
		// 如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
		try {
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			return "redirect:/index";
		}

		if (subject.hasRole("admin")) {
			return "redirect:/admin/showCourse";
		} else if (subject.hasRole("teacher")) {
			return "redirect:/teacher/showCourse";
		} else if (subject.hasRole("student")) {
			return "redirect:/student/showCourse";
		}

		return "/index";

	}

	// @RequestMapping("/login")
	// public String Test() {
	// System.out.println("1111111111");
	// return "success";
	// }

}
