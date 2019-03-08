package com.edu.service;

import com.edu.pojo.Teacher;
import com.edu.pojo.TeacherEx;

import java.util.List;

/**
 * Teacher老师Service层
 */
public interface TeacherService {

    //根据id更新老师信息
    void updateById(Integer id, TeacherEx teacherEx) throws Exception;

    //根据id删除老师信息
    void removeById(Integer id) throws Exception;

    //获取分页查询老师信息
    List<TeacherEx> findByPaging(Integer pagenum) throws Exception;

    //保存老师信息
    Boolean save(TeacherEx teacherEx) throws Exception;

    //获取老师总数
    int getCountTeacher() throws Exception;

    //根据id查询
    TeacherEx findById(Integer id) throws Exception;

    //根据名字查询
    List<Teacher> findByName(String name) throws Exception;

    //获取全部教师
    List<TeacherEx> findAll() throws Exception;
    //修改密码
	void changePassword(String username, String password);
}
