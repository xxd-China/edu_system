package com.edu.service;

import java.util.List;

import com.edu.pojo.College;

public interface CollegeService {

	//获取所有院系信息
    List<College> finAll() throws Exception;

}
