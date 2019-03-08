package com.edu.service;

import java.util.List;

import com.edu.pojo.SelectedCourseEx;
import com.edu.pojo.Selectedcourse;

/**
 * 选课表servic层
 */
public interface SelectedCourseService {

    //根据课程ID查询课程
    List<SelectedCourseEx> findByCourseID(Integer id) throws Exception;

    //根据课程id分页查询课程
    List<SelectedCourseEx> findByCourseIDPaging(Integer page, Integer id) throws Exception;

    //获取该课程学生数
    Integer countByCourseID(Integer id) throws Exception;

    //查询指定学生成绩
    SelectedCourseEx findOne(SelectedCourseEx selectedCourseEx) throws Exception;

    //打分
    void updataOne(SelectedCourseEx selectedCourseEx) throws Exception;

    //选课
    void save(SelectedCourseEx selectedCourseEx) throws Exception;

    //根据学生id查找课程
    List<SelectedCourseEx> findByStudentID(Integer id) throws Exception;

    //退课
    void remove(SelectedCourseEx selectedCourseEx) throws Exception;

    //查询当前课程是否被选过 （非自动生成）
    Selectedcourse findAndCheckCourse(int id,String userid);
    
    //保存课程（非自动生成）
    void saveCourse(int id,String userId);
    
    //退课 （非自动生成）
    void removeCourse(int id,String userId);
}
