package com.longten.ldzs.Curriculum;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/12.
 */
public class CurriculumBodyAdapter extends RecyclerView.Adapter<CurriculumBodyAdapter.MyViewHolder>{

    CurriculumShowInfo courses1;
    CurriculumShowInfo courses2;
    Context context;
    int currentWeek;
    Activity activity;
    Listener mListener;


    /**
     *
     *
     */
    public static final int NODATA = 0;
    public static final int DATAOUT = 1;
    public static final int BG1 = 2;
    public static final int BG2 = 3;
    public static final int BG3 = 4;
    public static final int BG4 = 5;
    public static final int BG5 = 6;
    public static final int BG6 = 7;
    public static final int BG7 = 8;
    public static final int BG8 = 9;
    public static final int BG9 = 10;
    public static final int BG10 = 11;
    public static final int BG11= 12;





    public interface Listener{
        public void onClick(Course course1,Course course2);
    }

    public void setOnItemClickListener(Listener listener){
        mListener = listener;

    }




    public CurriculumBodyAdapter(

            Context context,
            CurriculumShowInfo courses1,
            CurriculumShowInfo courses2,
            int currentWeek){

        this.context = context;
        this.courses1 = courses1;
        this.courses2 = courses2;
        this.currentWeek = currentWeek;

    }

    @Override
    public int getItemViewType(int position) {



        int i = position/7;//行
        int j = position%7;//列
        final Course course1 = courses1.curriculum.get(i).get(j);
        final Course course2= courses1.curriculum.get(i).get(j);

        boolean course1null = course1.courseName==null;
        boolean course2null = course2.courseName==null;
        if (course1null&course2null){
            //course1 为空,course2 为空
            return NODATA ;
        }else if (!course1null&!course2null){
            //course1 不为空,course2 不为空
            if (Integer.valueOf(course1.startTime)<=currentWeek&&Integer.valueOf(course1.endTime)>=currentWeek){
                //当前课程
                return BG1;
            }else {
                if (Integer.valueOf(course2.startTime)<=currentWeek&&Integer.valueOf(course2.endTime)>=currentWeek){
                    //当前课程
                    return BG1;
                }else {
                    return DATAOUT;
                }

            }



        }else if (!course1null){
            //course1 不为空,course2 为空
            if (Integer.valueOf(course1.startTime)<=currentWeek&&Integer.valueOf(course1.endTime)>=currentWeek){
                //当前课程
                return BG1;
            }else {
                return DATAOUT;
            }


        }else {
            //course2 不为空,course2 为空

            if (Integer.valueOf(course2.startTime)<=currentWeek&&Integer.valueOf(course2.endTime)>=currentWeek){
                //当前课程
                return BG1;
            }else {
                return DATAOUT;
            }

        }



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        switch (viewType){

            case NODATA:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item,parent,false);
                break;
            case DATAOUT:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item1,parent,false);
                break;
            case BG1:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item2,parent,false);
                break;
            default:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item,parent,false);
                break;


        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int i = position/7;//行
        int j = position%7;//列
        final Course course1 = courses1.curriculum.get(i).get(j);
        final Course course2= courses1.curriculum.get(i).get(j);





        if (course1.courseName!=null&&course2.courseName!=null){
            if (Integer.valueOf(course1.startTime)<=currentWeek&&Integer.valueOf(course1.endTime)>=currentWeek){

            holder.courseName.setText(course1.courseName);
            holder.courseAddr.setText(course1.addr);

             }else if (Integer.valueOf(course2.startTime)<=currentWeek&&Integer.valueOf(course2.endTime)>=currentWeek){

                holder.courseName.setText(course2.courseName);
                holder.courseAddr.setText(course2.addr);

            }else {
                holder.courseName.setText(course2.courseName);
                holder.courseAddr.setText(course2.addr);

            }
        }else if(course2.courseName!=null) {
           // if (Integer.valueOf(course2.startTime) <= currentWeek && Integer.valueOf(course2.endTime) >= currentWeek) {

                holder.courseName.setText(course2.courseName);
                holder.courseAddr.setText(course2.addr);

           // }
        } else if (course1.courseName!=null){
           // if (Integer.valueOf(course1.startTime)<=currentWeek&&Integer.valueOf(course1.endTime)>=currentWeek){

                holder.courseName.setText(course1.courseName);
                holder.courseAddr.setText(course1.addr);

          // }

        }
        View view = (View) holder.courseAddr.getParent();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(course1,course2);
            }
        });


    }

    @Override
    public int getItemCount() {

        return (courses1.curriculum.size()-1) * courses1.curriculum.get(0).size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName ;
        TextView courseAddr ;



        public MyViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            courseAddr = (TextView) itemView.findViewById(R.id.course_addr);
        }
    }
}
