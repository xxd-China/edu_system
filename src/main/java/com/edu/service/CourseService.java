package com.edu.service;

import java.util.List;
import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;
import com.edu.pojo.PageBean;
import com.edu.pojo.SelectedCourseEx;

public interface CourseService {
    //根据id更新课程信息
    void upadteById(Integer id, CourseEx courseEx) throws Exception;

    //根据id删除课程信息
    Boolean removeById(Integer id) throws Exception;

    //获取分页查询课程信息
    List<CourseEx> findByPaging(Integer pagenum) throws Exception;

    //插入课程信息
    Boolean save(CourseEx couseEx) throws Exception;

    //获取课程总数
    int getCountCouse() throws Exception;

    //根据id查询
    CourseEx findById(Integer id) throws Exception;

    //根据名字查询
    List<Course> findByName(String name) throws Exception;

    //根据教师id查找课程
    List<CourseEx> findByTeacherID(Integer id) throws Exception;
    
    //查询学生成绩
    List<SelectedCourseEx> findStudentGrade(Integer id,PageBean pagebean) throws Exception;
    
    //根据课程id查询学生成绩的总条数
    int studentGradeCount(int id);

	void mark(Integer studentid, Integer courseid, Integer mark);
}
