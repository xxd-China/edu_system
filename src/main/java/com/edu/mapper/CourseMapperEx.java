package com.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.pojo.CourseEx;
import com.edu.pojo.PageBean;
import com.edu.pojo.SelectedCourseEx;



/**
 * @author xxd
 * 此功能是手写，非逆向工程生成(所有EX结尾的mapper，都为逆向工程生成)
 */
public interface CourseMapperEx {

    //分页查询课程信息
    List<CourseEx> findByPaging(PageBean peanbean) throws Exception;
    
    //根据老师ID查询课程信息
    List<CourseEx> findCourseByID(int id) throws Exception;
    
    //查询学生成绩并分页
    List<SelectedCourseEx> findStudentGrade(@Param("id")int id,@Param("pagebean")PageBean pagebean);

    //成绩条数
	int findCourseCount(int id);

	//学生成绩打分
	void mark(@Param("studentid")Integer studentid, @Param("courseid")Integer courseid, @Param("mark")Integer mark);
	
	//根据id查课程信息
	CourseEx findById(Integer id);

}
