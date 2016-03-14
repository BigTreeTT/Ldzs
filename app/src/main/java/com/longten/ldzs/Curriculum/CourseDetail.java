package com.longten.ldzs.Curriculum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.longten.ldzs.R;

public class CourseDetail extends AppCompatActivity {
    TextView course_name1;
    TextView course_name2;
    TextView course_addr1;
    TextView course_addr2;
    TextView course_teacher1;
    TextView course_teacher2;
    TextView course_time1;
    TextView course_time2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Course course1 = (Course) bundle.getSerializable("course1");
        Course course2 = (Course) bundle.getSerializable("course2");
        course_name1 = (TextView) findViewById(R.id.course_name1);
        course_teacher1 = (TextView) findViewById(R.id.course_teacher1);
        course_addr1 = (TextView) findViewById(R.id.course_addr1);
        course_teacher1 = (TextView) findViewById(R.id.course_teacher1);
        course_time1 = (TextView) findViewById(R.id.course_time1);

        course_name2 = (TextView) findViewById(R.id.course_name2);
        course_teacher2 = (TextView) findViewById(R.id.course_teacher2);
        course_addr2 = (TextView) findViewById(R.id.course_addr2);
        course_teacher2 = (TextView) findViewById(R.id.course_teacher2);
        course_time2 = (TextView) findViewById(R.id.course_time2);
        /**
         *
         * if 表1不为空
         *      if表2不空
         *            先写表二，再写表一
         *      else
         *          写表一，其他null
         *
         *
         *
         */


        if (course1.courseName != null) {

            course_name1.setText(course1.courseName);
            course_teacher1.setText(course1.teacher);
            course_addr1.setText(course1.addr);
            course_time1.setText("第" + course1.startTime + "-" + course1.endTime + "周");
            if (course2.courseName != null) {
                course_name2.setText(course2.courseName);
                course_teacher2.setText(course2.teacher);
                course_addr2.setText(course2.addr);
                course_time2.setText("第" + course2.startTime + "-" + course2.endTime + "周");


            }else {
                course_name2.setText(" ");
                course_teacher2.setText(" ");
                course_addr2.setText(" ");
                course_time2.setText(" ");


            }


        }
        /**
         * if表1该课为空
         *      if表二为空
         *          全填null
         *      else
         *          填表二
         *
         *
         */


        if (course1.courseName == null) {

            course_name1.setText("  ");
            course_teacher1.setText("  ");
            course_addr1.setText("  ");
            course_time1.setText("  ");
            if (course2.courseName != null) {
                course_name1.setText(course2.courseName);
                course_teacher1.setText(course2.teacher);
                course_addr1.setText(course2.addr);
                course_time1.setText("第" + course2.startTime + "-" + course2.endTime + "周");
                course_name2.setText("  ");
                course_teacher2.setText("  ");
                course_addr2.setText("  ");
                course_time2.setText("  ");


            } else {
                course_name1.setText("  ");
                course_teacher1.setText("  ");
                course_addr1.setText("  ");
                course_time1.setText("  ");
                course_name2.setText("  ");
                course_teacher2.setText("  ");
                course_addr2.setText("  ");
                course_time2.setText("  ");


            }
        }

    }
}
