package com.edu.mapper;

import java.util.List;

import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;



/**
 * @author Administrator
 * 学生表和课程表关联查询
 */
public interface StudentAndCourseMapper {
  

    List<Course> selectById(String id);
    
    List<CourseEx> selectAlreadyById(String id);

   
}