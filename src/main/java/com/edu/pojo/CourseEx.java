package com.edu.pojo;

/**
 * Course扩展类    课程
 */
public class CourseEx extends Course {

    //所属院系名
    private String collegeName;
    //老师名字
    private String userName;
    
    private String mark;

    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

	

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

    
}
