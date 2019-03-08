package com.edu.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.edu.mapper.UserloginExMapper;
import com.edu.pojo.UserloginEx;

/**
 * @author xxd 检测用户角色
 */
@Repository
public class CheckRole {

	@Autowired
	private UserloginExMapper userloginExMapper;

	public String check(String userid) {

		UserloginEx user = userloginExMapper.getMessage(userid);

		return user.getRoleName();
	}

}
