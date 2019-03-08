package com.edu.pojo;

/**
 * teacher扩展类
 */
public class TeacherEx extends Teacher {
    //所属院系名
    private String collegeName;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }
}
