package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.edu.pojo.CourseEx;
import com.edu.pojo.PageBean;
import com.edu.pojo.SelectedCourseEx;
import com.edu.service.CourseService;
import javax.annotation.Resource;
import java.util.List;



/**
 * @author xxd
 * 教师的控制器
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController {

	@Resource(name = "courseServiceImpl")
	private CourseService courseService;

	@RequestMapping(value = "/showCourse")
	public String CourseShow(Model model) throws Exception {

		// 获取当前用户userid
		Subject subject = SecurityUtils.getSubject();
		String str = (String) subject.getPrincipal();
		Integer id = Integer.valueOf(str);
		List<CourseEx> list = courseService.findByTeacherID(id);

		PageBean pagebean = new PageBean();

		pagebean.setList(list);

		model.addAttribute("pagebean", pagebean);

		return "teacher/showCourse";
	}

	@RequestMapping(value = "/gradeCourse")
	public String studentGrade(Model model, Integer id, Integer page) throws Exception {
		// id为课程id，page为当前页

		List<SelectedCourseEx> list = null;
		// 页码对象
		PageBean pagebean = new PageBean();
		// 获取总记录数
		int totalCount = courseService.studentGradeCount(id);
		pagebean.setTotalCount(totalCount);
		int totalPage = pagebean.getTotalPage(); // 获取总页数

		if (page == null || page == 0) {
			pagebean.setPagenum(1);
			list = courseService.findStudentGrade(id, pagebean);
		} else if (page > totalPage) {
			pagebean.setPagenum(totalPage);
			list = courseService.findStudentGrade(id, pagebean);
		} else {
			pagebean.setPagenum(page);
			list = courseService.findStudentGrade(id, pagebean);
		}

		// 检测是否打分
		for (SelectedCourseEx ex : list) {
			if (ex.getMark() != null) {
				ex.setOver(true);
			} else {
				ex.setOver(false);
			}

		}

		pagebean.setList(list);

		model.addAttribute("pagebean", pagebean);

		return "teacher/showGrade";
	}

	// 打分跳转
	@RequestMapping(value = "/mark")
	public String mark(Model model, Integer studentid, Integer courseid) throws Exception {

		model.addAttribute("studentid", studentid);
		model.addAttribute("courseid", courseid);
		return "teacher/mark";
	}

	// 打分
	@RequestMapping(value = "/markhandle")
	public String markhhandle(Model model, Integer studentid, Integer courseid, Integer mark) throws Exception {

		courseService.mark(studentid, courseid, mark);
		return "redirect:gradeCourse?id=" + courseid;
	}

	// 修改密码
	@RequestMapping(value = "/passwordRest")
	public String passwordRest() throws Exception {
		return "teacher/passwordRest";
	}

	@RequestMapping(value = "/logout")
	public String out() {
		return "redirect:/index";
	}

}
