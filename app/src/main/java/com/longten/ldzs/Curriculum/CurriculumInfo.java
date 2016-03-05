package com.longten.ldzs.Curriculum;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/3.
 */
public class CurriculumInfo {
    public ArrayList<Courses> curriculum;

    public CurriculumInfo(){
        curriculum = new ArrayList<>();
    }

    public class Courses{
        public ArrayList<String> courses;
        public Courses(){
            courses = new ArrayList<>();
        }

    }



}
