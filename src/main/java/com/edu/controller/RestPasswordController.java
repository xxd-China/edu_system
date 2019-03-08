package com.edu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.exception.PasswordError;
import com.edu.pojo.Userlogin;
import com.edu.service.UserloginService;

import javax.annotation.Resource;

/**
 * xxd 重置密码
 */
@Controller
public class RestPasswordController {

	@Resource(name = "userloginServiceImpl")
	private UserloginService userloginService;

	// 本账户密码重置
	@RequestMapping(value = "/passwordRest", method = { RequestMethod.POST })
	public String passwordRest(String oldPassword, String password1) throws Exception {
		// 获取当前用户id
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();

		Userlogin userlogin = userloginService.findByName(username);

		if (!oldPassword.equals(userlogin.getPassword())) {
			throw new PasswordError("旧密码不正确");
		} else {
			userlogin.setPassword(password1);
			userloginService.updateByName(userlogin);
		}

		return "redirect:/logout";
	}

}
