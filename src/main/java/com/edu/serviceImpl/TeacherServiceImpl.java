package com.edu.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.common.MD5Utils;
import com.edu.mapper.TeacherExMapper;
import com.edu.mapper.TeacherMapper;
import com.edu.mapper.UserloginExMapper;
import com.edu.pojo.PageBean;
import com.edu.pojo.Teacher;
import com.edu.pojo.TeacherEx;
import com.edu.pojo.TeacherExample;
import com.edu.pojo.TeacherExample.Criteria;
import com.edu.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherExMapper tex;

	@Autowired
	private TeacherMapper t;
	
	@Autowired
	private UserloginExMapper userloginExMapper;
	
	@Override
	public void updateById(Integer id, TeacherEx teacherEx) throws Exception {
		tex.update(teacherEx);
	}

	@Override
	public void removeById(Integer id) throws Exception {
		    t.deleteByPrimaryKey(id);
	}

	@Override
	public List<TeacherEx> findByPaging(Integer pagenum) throws Exception {
		PageBean pagebean = new PageBean();
        pagebean.setPagenum(pagenum);

        List<TeacherEx> list = tex.findByPagenum(pagebean);
		return list;
	}

	@Override
	public Boolean save(TeacherEx teacherEx) throws Exception {
		
		tex.save(teacherEx);
		userloginExMapper.saveTeacher(teacherEx.getUserid(),teacherEx.getUsername(),MD5Utils.md5(String.valueOf(teacherEx.getUsername())));
		return true;
	}

	@Override
	public int getCountTeacher() throws Exception {
		int count = tex.teacherCounnt();
		return count;
	}

	@Override
	public TeacherEx findById(Integer id) throws Exception {
		TeacherEx t = tex.findbyid(id);
		return t;
	}

	@Override
	public List<Teacher> findByName(String name) throws Exception {
		
        
        TeacherExample teacherExample = new TeacherExample();
        
        Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Teacher> list = t.selectByExample(teacherExample);

        return list;
	}

	//获取所有教师信息
	@Override
	public List<TeacherEx> findAll() throws Exception {
		List<TeacherEx> list = tex.selectAll();
		return list;
	}

	@Override
	public void changePassword(String username, String password) {
		tex.change(username,password);
		
	}

}
