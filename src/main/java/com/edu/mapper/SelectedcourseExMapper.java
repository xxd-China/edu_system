package com.edu.mapper;

import org.apache.ibatis.annotations.Param;

import com.edu.pojo.Selectedcourse;

public interface SelectedcourseExMapper {
	//检查课程是否被选过
	Selectedcourse findAndCheckCourse(@Param("id")int id ,@Param("userId")String userId);
	
	//保存所选课程
	void saveCourse(@Param("id")int id ,@Param("userId")String userId);
	
	//t退课
	void removeCourse(@Param("id")int id ,@Param("userId")String userId);
	
	
}