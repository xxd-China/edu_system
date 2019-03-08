package com.edu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.mapper.SelectedcourseExMapper;
import com.edu.mapper.SelectedcourseMapper;
import com.edu.pojo.SelectedCourseEx;
import com.edu.pojo.Selectedcourse;
import com.edu.pojo.SelectedcourseExample;
import com.edu.service.SelectedCourseService;
import java.util.List;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

	@Autowired
	private SelectedcourseMapper selectedcourseMapper;

	@Autowired
	private SelectedcourseExMapper selectedcourseExMapper;

	public List<SelectedCourseEx> findByCourseIDPaging(Integer page, Integer id) throws Exception {
		return null;
	}

	// 获取该课程学生数
	public Integer countByCourseID(Integer id) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = example.createCriteria();
		criteria.andCourseidEqualTo(id);

		return selectedcourseMapper.countByExample(example);
	}

	public void updataOne(SelectedCourseEx selectedCourseEx) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = example.createCriteria();

		criteria.andCourseidEqualTo(selectedCourseEx.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseEx.getStudentid());

		selectedcourseMapper.updateByExample(selectedCourseEx, example);

	}

	public void save(SelectedCourseEx selectedCourseEx) throws Exception {
		selectedcourseMapper.insert(selectedCourseEx);
	}

	public List<SelectedCourseEx> findByStudentID(Integer id) throws Exception {
		return null;
	}

	// 删除课程
	public void remove(SelectedCourseEx selectedCourseEx) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = example.createCriteria();

		criteria.andCourseidEqualTo(selectedCourseEx.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseEx.getStudentid());

		selectedcourseMapper.deleteByExample(example);
	}

	@Override
	public List<SelectedCourseEx> findByCourseID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectedCourseEx findOne(SelectedCourseEx selectedCourseEx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 是否选过课程
	@Override
	public Selectedcourse findAndCheckCourse(int id, String userid) {
		Selectedcourse fc = selectedcourseExMapper.findAndCheckCourse(id, userid);

		return fc;
	}

	// 保存课程
	@Override
	public void saveCourse(int id, String userId) {

		selectedcourseExMapper.saveCourse(id, userId);
	}

	@Override
	public void removeCourse(int id, String userId) {

		selectedcourseExMapper.removeCourse(id, userId);

	}

}
