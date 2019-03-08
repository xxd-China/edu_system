package com.edu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.mapper.UserloginExMapper;
import com.edu.mapper.UserloginMapper;
import com.edu.pojo.Userlogin;
import com.edu.pojo.UserloginExample;
import com.edu.service.UserloginService;
import java.util.List;

@Service
public class UserloginServiceImpl implements UserloginService {

	@Autowired
	private UserloginMapper userloginMapper;
	@Autowired
	private UserloginExMapper userloginExMapper;

	// 根据用户名查找用户
	public Userlogin findByName(String name) throws Exception {
		UserloginExample userloginExample = new UserloginExample();

		UserloginExample.Criteria criteria = userloginExample.createCriteria();
		criteria.andUsernameEqualTo(name);

		List<Userlogin> list = userloginMapper.selectByExample(userloginExample);

		return list.get(0);
	}

	public void save(Userlogin userlogin) throws Exception {
		userloginMapper.insert(userlogin);
	}

	public void removeByName(String name) throws Exception {
		UserloginExample userloginExample = new UserloginExample();

		UserloginExample.Criteria criteria = userloginExample.createCriteria();
		criteria.andUsernameEqualTo(name);

		userloginMapper.deleteByExample(userloginExample);
	}

	// 更新用户（更改密码）
	public void updateByName(Userlogin userlogin) {

		userloginExMapper.updateById(userlogin);
	}

}
