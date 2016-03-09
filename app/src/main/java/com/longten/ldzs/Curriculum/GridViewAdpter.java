package com.longten.ldzs.Curriculum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/7.
 */
public class GridViewAdpter extends BaseAdapter {

    CurriculumShowInfo curriculumShowInfo;
    CurriculumShowInfo curriculumShowInfo2;

    int currentWeek;
    LayoutInflater inflater ;
    public GridViewAdpter(
            Context context,
            CurriculumShowInfo curriculumShowInfo,
            CurriculumShowInfo curriculumShowInfo2,
            int currentWeek
            ){
            inflater = LayoutInflater.from(context);
        this.curriculumShowInfo = curriculumShowInfo;
        this.curriculumShowInfo2 = curriculumShowInfo2;
        this.currentWeek = currentWeek;

    }


    @Override
    public int getCount() {

        return curriculumShowInfo.curriculum.size()*curriculumShowInfo.curriculum.get(0).size();
    }

    @Override
    public Object getItem(int position) {
        int i=0;
        int j=0;
        i =position/7;
        j =position%7;

        return curriculumShowInfo.curriculum.get(i).get(j);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.gridview_item,parent,false);

        TextView course_name = (TextView) convertView.findViewById(R.id.course_name);
        TextView course_addr = (TextView) convertView.findViewById(R.id.course_addr);
        int i=0;
        int j=0;
        i =position/7;
        j =position%7;
        Course course1 = curriculumShowInfo.curriculum.get(i).get(j);
        Course course2 = curriculumShowInfo2.curriculum.get(i).get(j);
        if (course1.courseName!=null){
            if (Integer.valueOf(course1.startTime)<=currentWeek&&
                    Integer.valueOf(course1.endTime)>=currentWeek){
                course_name.setText(course1.courseName);
                course_addr.setText("@"+course1.addr);

            }
        }
        if (course2.courseName!=null){
            if (Integer.valueOf(course2.startTime)<=currentWeek&&
                    Integer.valueOf(course2.endTime)>=currentWeek){
                course_name.setText(course2.courseName);
                course_addr.setText(course2.addr);

            }
        }




        return convertView;
    }
}
