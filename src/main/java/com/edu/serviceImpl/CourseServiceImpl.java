package com.edu.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.exception.AddCourseException;
import com.edu.mapper.CourseMapper;
import com.edu.mapper.CourseMapperEx;
import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;
import com.edu.pojo.CourseExample;
import com.edu.pojo.PageBean;
import com.edu.pojo.SelectedCourseEx;
import com.edu.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private CourseMapperEx courseMapperEx;

	public void upadteById(Integer id, CourseEx courseEx) throws Exception {
		courseMapper.updateByPrimaryKey(courseEx);
	}

	// 删除课程
	public Boolean removeById(Integer id) throws Exception {
		try {
			courseMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			// throw new DeleteException("删除失败，因为已有学生选了这门课");
			e.printStackTrace();
		}

		return true;
	}

	// 获取分页查询课程信息
	public List<CourseEx> findByPaging(Integer pagenum) throws Exception {
		PageBean pagebean = new PageBean();
		pagebean.setPagenum(pagenum);

		List<CourseEx> list = courseMapperEx.findByPaging(pagebean);
		return list;
	}

	// 保存课程
	public Boolean save(CourseEx couseEx) throws Exception {

		try {
			courseMapper.insert(couseEx);
		} catch (Exception e) {
			throw new AddCourseException("保存失败！请重新保存");
		}

		return true;
	}

	// 返回总条数
	public int getCountCouse() throws Exception {
		// 自定义查询对象
		CourseExample courseExample = new CourseExample();
		// 通过criteria构造查询条件
		CourseExample.Criteria criteria = courseExample.createCriteria();
		criteria.andCoursenameIsNotNull();

		return courseMapper.countByExample(courseExample);
	}

	public List<Course> findByName(String name) throws Exception {
		CourseExample courseExample = new CourseExample();
		// 自定义查询条件
		CourseExample.Criteria criteria = courseExample.createCriteria();

		criteria.andCoursenameLike("%" + name + "%");

		List<Course> list = courseMapper.selectByExample(courseExample);

		return list;
	}

	public CourseEx findById(Integer id) throws Exception {
		CourseEx ex = courseMapperEx.findById(id);
		return ex;
	}

	// 根据老师ID查询课程信息
	public List<CourseEx> findByTeacherID(Integer id) throws Exception {
		List<CourseEx> list = courseMapperEx.findCourseByID(id);
		return list;
	}

	// 学生成绩查询（非自动生成）
	public List<SelectedCourseEx> findStudentGrade(Integer id, PageBean pagebean) throws Exception {
		List<SelectedCourseEx> list = courseMapperEx.findStudentGrade(id, pagebean);
		return list;
	}

	@Override
	public int studentGradeCount(int id) {

		return courseMapperEx.findCourseCount(id);
	}

	@Override
	public void mark(Integer studentid, Integer courseid, Integer mark) {
		courseMapperEx.mark(studentid, courseid, mark);
	}

}
