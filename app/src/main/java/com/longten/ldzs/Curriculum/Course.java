package com.longten.ldzs.Curriculum;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/6.
 */
public class Course implements Serializable{
    public String courseName;
    public String startTime;
    public String endTime;
    public String teacher;
    public String addr;

    @Override
    public String toString() {


        return courseName +"+"+startTime+"+"+endTime+"+"+teacher+"+"+addr+"\n";

    }
}
