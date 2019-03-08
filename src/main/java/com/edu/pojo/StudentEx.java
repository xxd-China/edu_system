package com.edu.pojo;

import java.util.List;

/**
 * Student的扩展类
 */
public class StudentEx extends Student {
    //所属院系名
    private String collegeName;
    
    private int mark;

    //选课列表
    private List<SelectedCourseEx> selectedCourseList;

    
    public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

    public void setSelectedCourseList(List<SelectedCourseEx> selectedCourseList) {
        this.selectedCourseList = selectedCourseList;
    }

    public List<SelectedCourseEx> getSelectedCourseList() {
        return selectedCourseList;
    }

}
