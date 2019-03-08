package com.edu.pojo;


public class SelectedCourseEx extends Selectedcourse {
    //新增Student 对象字段
    private StudentEx studentEx;

    //扩展课程信息对象
    private CourseEx courseEx;
    
    private String userName;

    //判断该学生是否已经完成该课程
    private Boolean over = false;


    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public StudentEx getStudentEx() {
        return studentEx;
    }

    public void setStudentEx(StudentEx studentEx) {
        this.studentEx = studentEx;
    }

    public CourseEx getCouseEx() {
        return courseEx;
    }

    public void setCouseEx(CourseEx couseEx) {
        this.courseEx = couseEx;
    }
}
