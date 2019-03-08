package com.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.pojo.PageBean;
import com.edu.pojo.StudentEx;


public interface StudentExMapper {
    

    List<StudentEx> selectByPagenum(PageBean pagebean);

	void updateStudent(StudentEx ex);
    //修改密码
	void change(@Param("userid")String userid, @Param("password")String password);
    //查询所有
	List<StudentEx> findAll();

    
}