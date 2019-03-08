package com.edu.service;

import java.util.List;

import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;
import com.edu.pojo.StudentEx;

/**
 * Student学生Service层
 */
public interface StudentService {

    //根据id个更新学生信息
    void updataById(Integer id, StudentEx studentEx) throws Exception;

    //根据id删除学生信息
    void removeById(Integer id) throws Exception;

    //获取分页查询学生信息
    List<StudentEx> findByPaging(Integer pagenum) throws Exception;

    //保存学生信息
    Boolean save(StudentEx studentEx) throws Exception;

    //获取学生总数
    int getCountStudent() throws Exception;

    //根据id获取学生信息
    StudentEx findById(Integer id) throws Exception;

    //根据名字模糊查询
    List<StudentEx> findByName(String name) throws Exception;

    //查询该学生的选课信息
    List<Course> findStudentAndSelectCourseListByName(String name) throws Exception;
    
    //查询该学生的已修选课信息
    List<CourseEx> findCourseAlreadyByName(String name) throws Exception;
    //根据页码分页查询学生信息
	List<StudentEx> selectByPagenum(int i);
    //修改学生信息
	void update(StudentEx ex);
    //修改密码
	void changePassword(String username, String password);
    //查询所有学生信息
	List<StudentEx> findAll();

}
