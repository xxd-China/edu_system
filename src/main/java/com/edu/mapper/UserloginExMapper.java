package com.edu.mapper;
import org.apache.ibatis.annotations.Param;

import com.edu.pojo.Userlogin;
import com.edu.pojo.UserloginEx;

public interface UserloginExMapper {
   
	//修改密码
    int updateById(Userlogin userlogin);
    //检测角色
	UserloginEx getMessage(String userid);
	//保存学生密码
	void saveStudent(@Param("userid")Integer userid, @Param("username")String username, @Param("md5")String md5);
	//保存教师密码
	void saveTeacher(@Param("userid")Integer userid, @Param("username")String username, @Param("md5")String md5);
	//保存admin密码
	void saveAdmin(@Param("userid")Integer userid, @Param("username")String username, @Param("md5")String md5);
}