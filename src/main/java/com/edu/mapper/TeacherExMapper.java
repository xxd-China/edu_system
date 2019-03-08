package com.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.edu.pojo.PageBean;
import com.edu.pojo.TeacherEx;

public interface TeacherExMapper {
 
    //查询所有信息
    List<TeacherEx> selectAll();
    //老师的位数
	int teacherCounnt();
	//分页查询老师信息
	List<TeacherEx> findByPagenum(PageBean pagebean);
	//根据id查询教师
	TeacherEx findbyid(Integer id);
	//更新操作
	void update(TeacherEx teacherEx);
	//保存老师信息
	void save(TeacherEx teacherEx);
	//修改密码
	void change(@Param("userid")String username, @Param("passowrd")String password);

  
}