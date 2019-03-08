package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.edu.exception.AlreadyExistException;
import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;
import com.edu.pojo.PageBean;
import com.edu.pojo.Selectedcourse;
import com.edu.service.CourseService;
import com.edu.service.SelectedCourseService;
import com.edu.service.StudentService;
import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

	@Resource(name = "courseServiceImpl")
	private CourseService courseService;

	@Resource(name = "studentServiceImpl")
	private StudentService studentService;

	@Resource(name = "selectedCourseServiceImpl")
	private SelectedCourseService selectedCourseService;

	// 搜索功能
	@RequestMapping(value = "/selectCourse", method = { RequestMethod.POST })
	public String selectCourse(String findByName, Model model) throws Exception {

		// CourseEx为Course的扩展类
		List<Course> list = courseService.findByName(findByName);

		PageBean pageBean = new PageBean();
		pageBean.setList(list);

		model.addAttribute("pagebean", pageBean);
		return "student/showCourse";
	}

	// 课程信息
	@RequestMapping(value = "/showCourse")
	public String stuCourseShow(Model model, Integer page) throws Exception {

		List<CourseEx> list = null;
		// 页码对象
		PageBean pagebean = new PageBean();
		// 获取总记录数
		int totalCount = courseService.getCountCouse();
		pagebean.setTotalCount(totalCount);
		int totalPage = pagebean.getTotalPage(); // 获取总页数

		if (page == null || page == 0) {
			pagebean.setPagenum(1);
			list = courseService.findByPaging(1);

		} else if (page > totalPage) {

			pagebean.setPagenum(totalPage);
			list = courseService.findByPaging(totalPage);
		} else {
			pagebean.setPagenum(page);
			list = courseService.findByPaging(page);
		}

		pagebean.setList(list);

		model.addAttribute("pagebean", pagebean);

		return "student/showCourse";
	}

	// 已选课程
	@RequestMapping(value = "/selectedCourse")
	public String selectedCourse(Model model) throws Exception {
		// 获取当前用户id
		Subject subject = SecurityUtils.getSubject();

		// System.out.println(subject.getPrincipal());
		// System.out.println((String)subject.getPrincipal());

		List<Course> list = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

		// List<SelectedCourseEx> list = studentEx.getSelectedCourseList();

		model.addAttribute("selectedCourseList", list);

		return "student/selectCourse";
	}

	// 选课操作
	@RequestMapping(value = "/stuselectedCourse")
	public String stuSelectedCourse(int id) throws Exception {
		// id为选课id
		// 获取当前用户userid
		Subject subject = SecurityUtils.getSubject();
		String userid = (String) subject.getPrincipal();

		// 在数据库中查询是否已经选了这门课
		Selectedcourse sc = selectedCourseService.findAndCheckCourse(id, userid);

		if (sc == null) {
			selectedCourseService.saveCourse(id, userid);
		} else {
			throw new AlreadyExistException("该门课程你已经选了，不能再选");
			// System.out.println("非空");
		}
		// 返回已选课程
		return "redirect:/student/selectedCourse";
	}

	// 退课操作
	@RequestMapping(value = "/outCourse")
	public String outCourse(int id) throws Exception {
		// 获取当前用户id
		Subject subject = SecurityUtils.getSubject();
		String userid = (String) subject.getPrincipal();

		selectedCourseService.removeCourse(id, userid);

		return "redirect:/student/selectedCourse";
	}

	// 已修课程
	@RequestMapping(value = "/overCourse")
	public String overCourse(Model model) throws Exception {

		// 获取当前用户id
		Subject subject = SecurityUtils.getSubject();
		// 查询已选修课信息

		List<CourseEx> list = studentService.findCourseAlreadyByName((String) subject.getPrincipal());

		model.addAttribute("list", list);

		return "student/overCourse";
	}

	// 修改密码
	@RequestMapping(value = "/passwordRest")
	public String passwordRest() throws Exception {
		return "student/passwordRest";
	}

	@RequestMapping(value = "/logout")
	public String out() {
		return "redirect:/index";
	}

}
