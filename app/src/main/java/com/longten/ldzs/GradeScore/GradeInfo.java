package com.longten.ldzs.GradeScore;

/**
 * Created by Administrator on 2016/3/3.
 */
public class GradeInfo {
    public String name;
    public String type;
    public String credit;
    public String GPA;
    public String score;
    public String makeUpEx;
    public String reStudy;

    @Override
    public String toString() {
        return name+ "---" +type + "---"+credit+ "---"+GPA+ "---"+score+ "---"+makeUpEx+ "---"+reStudy+"\n";
    }
}
