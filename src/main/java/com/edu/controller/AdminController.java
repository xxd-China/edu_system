package com.edu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.edu.common.ExportExcelUtil;
import com.edu.common.MD5Utils;
import com.edu.pojo.College;
import com.edu.pojo.Course;
import com.edu.pojo.CourseEx;
import com.edu.pojo.PageBean;
import com.edu.pojo.StudentEx;
import com.edu.pojo.Teacher;
import com.edu.pojo.TeacherEx;
import com.edu.service.CollegeService;
import com.edu.service.CourseService;
import com.edu.service.StudentService;
import com.edu.service.TeacherService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private StudentService studentService;

	// 显示课程信息
	@RequestMapping(value = "/showCourse")
	public String showCourse(Model model, Integer page) throws Exception {
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

		return "admin/showCourse";
	}

	// 修改课程信息界面
	@RequestMapping(value = "editCourse")
	public String editCourse(Model model, Integer courseid) throws Exception {

		// 查询课程信息（老师名字+所属院系）

		CourseEx courseEx = courseService.findById(courseid);

		model.addAttribute("course", courseEx);

		return "admin/editCourse";
	}

	// 修改课程信息
	@RequestMapping(value = "edit")
	public String edit(CourseEx ex) throws Exception {
		// 保存
		courseService.upadteById(ex.getCourseid(), ex);
		return "redirect:/admin/showCourse";
	}

	// 删除课程 /admin/removeCourse?courseid=${item.courseid}
	@RequestMapping(value = "removeCourse")
	public String removeCourse(Integer courseid) throws Exception {

		// 删除
		courseService.removeById(courseid);

		return "redirect:/admin/showCourse";
	}

	// 搜索课程
	@RequestMapping(value = "selectCourse")
	public String searchCourse(Model model, String findByName) throws Exception {

		// CourseEx为Course的扩展类
		List<Course> list = courseService.findByName(findByName);

		PageBean pageBean = new PageBean();
		pageBean.setList(list);

		model.addAttribute("pagebean", pageBean);

		return "admin/showCourse";
	}

	// 添加课程信息 页面
	@RequestMapping("addCourse")
	public String addCourse() throws Exception {
		return "admin/addCourse";
	}

	// 添加课程信息
	@RequestMapping("add")
	public String add(CourseEx ex) throws Exception {
		courseService.save(ex);
		return "redirect:/admin/showCourse";
	}

	// ajax获取老师信息返回json
	@RequestMapping("getTeacher")
	@ResponseBody
	public List<TeacherEx> getTeacher() throws Exception {

		List<TeacherEx> list = teacherService.findAll();

		return list;
	}

	// ajax获取院系信息返回json
	@RequestMapping("College")
	@ResponseBody
	public List<College> getCollege() throws Exception {

		List<College> all = collegeService.finAll();

		return all;
	}

	// 显示学生信息
	@RequestMapping(value = "/showStudent")
	public String showStudent(Model model, Integer page) throws Exception {
		List<StudentEx> list = null;
		// 页码对象
		PageBean pagebean = new PageBean();
		// 获取总记录数
		int totalCount = studentService.getCountStudent();
		pagebean.setTotalCount(totalCount);
		int totalPage = pagebean.getTotalPage(); // 获取总页数

		if (page == null || page == 0) {
			pagebean.setPagenum(1);
			list = studentService.selectByPagenum(1);

		} else if (page > totalPage) {

			pagebean.setPagenum(totalPage);
			list = studentService.selectByPagenum(totalPage);
		} else {
			pagebean.setPagenum(page);
			list = studentService.selectByPagenum(page);
		}

		pagebean.setList(list);

		model.addAttribute("pagebean", pagebean);

		return "admin/showStudent";
	}

	// 搜索学生
	@RequestMapping(value = "selectStudent")
	public String searchStudent(Model model, String findByName) throws Exception {

		// CourseEx为Course的扩展类
		List<StudentEx> list = studentService.findByName(findByName);

		PageBean pageBean = new PageBean();
		pageBean.setList(list);

		model.addAttribute("pagebean", pageBean);

		return "admin/showStudent";
	}

	// 修改学生信息界面
	@RequestMapping(value = "editStudent")
	public String editStudent(Model model, Integer id) throws Exception {

		StudentEx ex = studentService.findById(id);

		model.addAttribute("student", ex);

		return "admin/editStudent";
	}

	// 修改课程信息
	@RequestMapping(value = "editStu")
	public String editStu(StudentEx ex) throws Exception {
		// 保存
		studentService.update(ex);
		return "redirect:/admin/showStudent";
	}

	// 删除学生
	@RequestMapping(value = "removeStudent")
	public String removeStu(Integer id) throws Exception {

		// 删除
		studentService.removeById(id);

		return "redirect:/admin/showStudent";
	}

	// 添加课程信息页面
	@RequestMapping("addStudent")
	public String addstudent() throws Exception {

		return "admin/addStudent";
	}

	// 保存学生信息
	@RequestMapping("addStu")
	public String addstu(StudentEx ex) throws Exception {
		studentService.save(ex);
		return "redirect:/admin/showStudent";
	}

	// 显示教师页面
	@RequestMapping("showTeacher")
	public String showTeacher(Model model, Integer page) throws Exception {
		List<TeacherEx> list = null;
		// 页码对象
		PageBean pagebean = new PageBean();
		// 获取总记录数
		int totalCount = teacherService.getCountTeacher();
		pagebean.setTotalCount(totalCount);
		int totalPage = pagebean.getTotalPage(); // 获取总页数

		if (page == null || page == 0) {
			pagebean.setPagenum(1);
			list = teacherService.findByPaging(1);

		} else if (page > totalPage) {

			pagebean.setPagenum(totalPage);
			list = teacherService.findByPaging(totalPage);
		} else {
			pagebean.setPagenum(page);
			list = teacherService.findByPaging(page);
		}

		pagebean.setList(list);

		model.addAttribute("pagebean", pagebean);

		return "admin/showTeacher";
	}

	// 搜索老师
	@RequestMapping(value = "selectTeacher")
	public String searchTeacher(Model model, String findByName) throws Exception {

		// 模糊查询
		List<Teacher> list = teacherService.findByName(findByName);

		PageBean pageBean = new PageBean();
		pageBean.setList(list);

		model.addAttribute("pagebean", pageBean);

		return "admin/showTeacher";
	}

	// 修改页面
	@RequestMapping(value = "editTeacher")
	public String editTeacher(Model model, Integer id) throws Exception {

		TeacherEx ex = teacherService.findById(id);

		model.addAttribute("teacher", ex);

		return "admin/editTeacher";
	}

	// 保存页面修改
	@RequestMapping(value = "editTea")
	public String editTea(TeacherEx teacher) throws Exception {
		teacherService.updateById(teacher.getUserid(), teacher);
		return "redirect:/admin/showTeacher";
	}

	// 删除老师
	@RequestMapping(value = "removeTeacher")
	public String removetea(Integer id) throws Exception {

		// 删除
		teacherService.removeById(id);

		return "redirect:/admin/showTeacher";
	}

	// 添加教师页面 addTeacher
	@RequestMapping("addTeacher")
	public String addTeacher() throws Exception {

		return "admin/addTeacher";
	}

	// 保存教师
	@RequestMapping("addTea")
	public String addTea(TeacherEx t) throws Exception {
		teacherService.save(t);
		return "redirect:/admin/showTeacher";
	}

	// 修改密码
	@RequestMapping(value = "passwordRest")
	public String passwordRest() throws Exception {
		return "admin/passwordRest";
	}

	// 账号密码重置页面
	@RequestMapping(value = "userPasswordRest")
	public String reset() {
		return "admin/userPasswordRest";
	}

	// 账号密码重置保存
	@RequestMapping(value = "saveuserPasswordRest")
	public String saveReset(String username, String password) {

		// 使用工具类判断角色
		// String rolename = new CheckRole().check(username);

		// if("student".equals(rolename)){
		// studentService.changePassword(username,password);
		// }else if("teacher".equals(rolename)){
		// teacherService.changePassword(username,password);
		// }
		// 任意一个都可以
		studentService.changePassword(username, MD5Utils.md5(password));
		return "admin/userPasswordRest";
	}

	// 导出学生信息到excel
	@RequestMapping("outGrade")
	public String outgrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 查询
		List<StudentEx> list = studentService.findAll();
		// 使用excel工具对象
		new ExportExcelUtil(list, request, response).exportExcel();
		return "admin/success";
	}

}
