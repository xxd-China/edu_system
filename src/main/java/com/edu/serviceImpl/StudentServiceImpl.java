package com.edu.serviceImpl;

import com.edu.common.MD5Utils;
import com.edu.mapper.CollegeMapper;
import com.edu.mapper.StudentAndCourseMapper;
import com.edu.mapper.StudentExMapper;
import com.edu.mapper.StudentMapper;
import com.edu.mapper.UserloginExMapper;
import com.edu.pojo.*;
import com.edu.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Student
 */
@Service
public class StudentServiceImpl implements StudentService {

    //使用spring 自动注入
    @Autowired
    private StudentAndCourseMapper sc;

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private StudentExMapper studentExMapper;

    @Autowired
    private CollegeMapper collegeMapper;
    
    @Autowired
    private UserloginExMapper userloginExMapper;

    public void updataById(Integer id, StudentEx studentEx) throws Exception {
        studentMapper.updateByPrimaryKey(studentEx);
    }

    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }


    public Boolean save(StudentEx studentEx) throws Exception {
        Student stu = studentMapper.selectByPrimaryKey(studentEx.getUserid());
        if (stu == null) {
            studentMapper.insert(studentEx);
            //保存账号密码，角色为学生
            userloginExMapper.saveStudent(studentEx.getUserid(),studentEx.getUsername(),MD5Utils.md5(String.valueOf(studentEx.getUsername())));
            return true;
        }

        return false;
    }

    //返回学生总数
    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        //通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();

        return studentMapper.countByExample(studentExample);
    }

    public StudentEx findById(Integer id) throws Exception {

        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentEx studentEx = null;
        if (student != null) {
            studentEx = new StudentEx();
            //类拷贝
            BeanUtils.copyProperties(student, studentEx);
        }

        return studentEx;
    }

    //模糊查询
    public List<StudentEx> findByName(String name) throws Exception {

        StudentExample studentExample = new StudentExample();
        //自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Student> list = studentMapper.selectByExample(studentExample);

        List<StudentEx> studentExList = null;

        if (list != null) {
            studentExList = new ArrayList<StudentEx>();
            for (Student s : list) {
                StudentEx studentEx = new StudentEx();
                //类拷贝
                BeanUtils.copyProperties(s, studentEx);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                studentEx.setcollegeName(college.getCollegename());

                studentExList.add(studentEx);
            }
        }

        return studentExList;
    }



	@Override
	public List<StudentEx> findByPaging(Integer pagenum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    //查询已选课程
	@Override
	public List<Course> findStudentAndSelectCourseListByName(String id) throws Exception {
		// TODO Auto-generated method stub
		List<Course> selectById = sc.selectById(id);
		return selectById;
	}
	//查询已选修课程
	@Override
	public List<CourseEx> findCourseAlreadyByName(String name) throws Exception {
		// TODO Auto-generated method stub
		List<CourseEx> list = sc.selectAlreadyById(name);
		return list;
	}

	@Override
	public List<StudentEx> selectByPagenum(int i) {
		PageBean pagebean = new PageBean();
        pagebean.setPagenum(i);

        List<StudentEx> list = studentExMapper.selectByPagenum(pagebean);
		return list;
	}

	@Override
	public void update(StudentEx ex) {
		studentExMapper.updateStudent(ex);
	}

	@Override
	public void changePassword(String username, String password) {
		studentExMapper.change(username,password);
		
	}
    //所有学生信息
	@Override
	public List<StudentEx> findAll() {
		return studentExMapper.findAll();
	}
}
