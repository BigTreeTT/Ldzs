package com.longten.ldzs.Curriculum;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/12.
 */
public class CurriculumBodyAdapter extends RecyclerView.Adapter<CurriculumBodyAdapter.MyViewHolder> {

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
    public static final int BG11 = 12;
    public static final int BG12 = 13;
    public static final int BG13 = 14;
    public static final int BG14 = 15;
    public static final int BG15 = 16;


    public interface Listener {
        public void onClick(Course course1, Course course2);
    }

    public void setOnItemClickListener(Listener listener) {
        mListener = listener;

    }


    public CurriculumBodyAdapter(

            Context context,
            CurriculumShowInfo courses1,
            CurriculumShowInfo courses2,
            int currentWeek) {

        this.context = context;
        this.courses1 = courses1;
        this.courses2 = courses2;
        this.currentWeek = currentWeek;

    }

    @Override
    public int getItemViewType(int position) {


        int i = position / 7;//行
        int j = position % 7;//列
        final Course course1 = courses1.curriculum.get(i).get(j);
        final Course course2 = courses2.curriculum.get(i).get(j);

        boolean course1null = course1.courseName == null;
        boolean course2null = course2.courseName == null;
        if (course1null & course2null) {
            //course1 为空,course2 为空
            return NODATA;
        } else if (!course1null & !course2null) {
            //course1 不为空,course2 不为空
            if (Integer.valueOf(course1.startTime) <= currentWeek && Integer.valueOf(course1.endTime) >= currentWeek) {
                //当前课程
                return getBackGroudColor(position);
            } else {
                if (Integer.valueOf(course2.startTime) <= currentWeek && Integer.valueOf(course2.endTime) >= currentWeek) {
                    //当前课程
                    return getBackGroudColor(position);
                } else {
                    return DATAOUT;
                }

            }


        } else if (!course1null) {
            //course1 不为空,course2 为空
            if (Integer.valueOf(course1.startTime) <= currentWeek && Integer.valueOf(course1.endTime) >= currentWeek) {
                //当前课程
                return getBackGroudColor(position);
            } else {
                return DATAOUT;
            }


        } else {
            //course2 不为空,course2 为空

            if (Integer.valueOf(course2.startTime) <= currentWeek && Integer.valueOf(course2.endTime) >= currentWeek) {
                //当前课程
                return BG1;
            } else {
                return DATAOUT;
            }

        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        switch (viewType) {

            case NODATA:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item, parent, false);
                break;
            case DATAOUT:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item1, parent, false);
                break;
            case BG1:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item2, parent, false);
                break;
            case BG2:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item3, parent, false);
                break;
            case BG3:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item4, parent, false);
                break;
            case BG4:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item5, parent, false);
                break;
            case BG5:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item6, parent, false);
                break;
            case BG6:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item7, parent, false);
                break;
            case BG7:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item8, parent, false);
                break;
            case BG8:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item9, parent, false);
                break;
            case BG9:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item10, parent, false);
                break;
            case BG10:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item11, parent, false);
                break;
            case BG11:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item12, parent, false);
                break;
            case BG12:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item13, parent, false);
                break;
            case BG13:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item14, parent, false);
                break;
            case BG14:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item15, parent, false);
                break;
            case BG15:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item16, parent, false);
                break;


            default:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.gridview_item, parent, false);
                break;


        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int i = position / 7;//行
        int j = position % 7;//列
        final Course course1 = courses1.curriculum.get(i).get(j);
        final Course course2 = courses2.curriculum.get(i).get(j);


        if (course1.courseName != null && course2.courseName != null) {
            if (Integer.valueOf(course1.startTime) <= currentWeek && Integer.valueOf(course1.endTime) >= currentWeek) {

                holder.courseName.setText(course1.courseName);
                holder.courseAddr.setText(course1.addr);

            } else if (Integer.valueOf(course2.startTime) <= currentWeek && Integer.valueOf(course2.endTime) >= currentWeek) {

                holder.courseName.setText(course2.courseName);
                holder.courseAddr.setText(course2.addr);

            } else {
                holder.courseName.setText(course2.courseName);
                holder.courseAddr.setText(course2.addr);

            }
        } else if (course2.courseName != null) {
            // if (Integer.valueOf(course2.startTime) <= currentWeek && Integer.valueOf(course2.endTime) >= currentWeek) {

            holder.courseName.setText(course2.courseName);
            holder.courseAddr.setText(course2.addr);

            // }
        } else if (course1.courseName != null) {
            // if (Integer.valueOf(course1.startTime)<=currentWeek&&Integer.valueOf(course1.endTime)>=currentWeek){

            holder.courseName.setText(course1.courseName);
            holder.courseAddr.setText(course1.addr);

            // }

        }
        View view = (View) holder.courseAddr.getParent();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(course1, course2);
            }
        });


    }

    @Override
    public int getItemCount() {

        return (courses1.curriculum.size() - 1) * courses1.curriculum.get(0).size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        TextView courseAddr;


        public MyViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            courseAddr = (TextView) itemView.findViewById(R.id.course_addr);
        }
    }


    public int getBackGroudColor(int position) {
        int[][] colors = new int[5][7];


        int i = position / 7;//行
        int j = position % 7;//列


        for (i = 0; i < 5; i++) {
            for (j = 0; j < 7; j++) {
                colors[i][j] = 0;
            }
        }


        /**
         * 获取当前周课程表
         *
         */
        ArrayList<ArrayList<Course>> curriculum = new ArrayList<>();
        for (i = 0; i < 5; i++) {
            ArrayList<Course> arrayList = new ArrayList<>();
            for (j = 0; j < 7; j++) {
                arrayList.add(new Course());
            }
            curriculum.add(arrayList);
        }


        for (i = 0; i < 5; i++) {

            for (j = 0; j < 7; j++) {
                Course course1 = courses1.curriculum.get(i).get(j);
                Course course2 = courses2.curriculum.get(i).get(j);

                if (course1.courseName!=null){
                    int startTime = Integer.valueOf(course1.startTime);
                    int endTime = Integer.valueOf(course1.endTime);
                    if (startTime<=currentWeek&&endTime>=currentWeek){
                        curriculum.get(i).get(j).courseName = course1.courseName;
                    }

                }
                if (course2.courseName!=null){
                    int startTime = Integer.valueOf(course2.startTime);
                    int endTime = Integer.valueOf(course2.endTime);
                    if (startTime<=currentWeek&&endTime>=currentWeek){
                        curriculum.get(i).get(j).courseName = course2.courseName;
                    }
                }
            }
        }

        int type = 1;
        int beforeType = 1;
        for (i = 0; i < 5; i++) {

            for (j = 0; j < 7; j++) {
                int m0 = 0;
                int n0 = 0;
                if(i>0) n0=7;
                m0 = i;

                if (curriculum.get(i).get(j).courseName != null) {
                    //遍历前面所有课程，寻找是否存在同名
                    String currentName = curriculum.get(i).get(j).courseName;
                    boolean is_have = false;
                    for (int m = 0; m < m0; m++) {
                        for (int n = 0; n < n0; n++) {
                            String beforeName = curriculum.get(m).get(n).courseName;
                            if (beforeName != null) {
                                if (beforeName.equals(currentName)) {
                                    is_have = true;
                                    beforeType = colors[m][n];
                                    break;
                                }

                            }
                        }
                        if (is_have) {
                            break;
                        }
                    }

                    if (!is_have) {
                        colors[i][j] = type;
                        type++;
                    } else {
                        colors[i][j] = beforeType;
                    }


                }

            }

        }


        for (i = 0; i < 5; i++) {
            for (j = 0; j < 7; j++) {
                type = colors[i][j];
                Log.d("type", String.valueOf(type));
            }
            Log.d("type", "------------------");
        }
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 7; j++) {
                Course c = curriculum.get(i).get(j);
                Log.d("courseName", String.valueOf(c.courseName == null));
            }
            Log.d("courseName", "------------------");
        }
        i = position / 7;//行
        j = position % 7;//列
        type = colors[i][j];


        int result = 2;
        switch (type) {
            case 0:
                break;
            case 1:
                result = BG1;
                break;
            case 2:
                result = BG2;
                break;
            case 3:
                result = BG3;
                break;
            case 4:
                result = BG4;
                break;
            case 5:
                result = BG5;
                break;
            case 6:
                result = BG6;
                break;
            case 7:
                result = BG7;
                break;
            case 8:
                result = BG8;
                break;
            case 9:
                result = BG9;
                break;
            case 10:
                result = BG10;
                break;
            case 11:
                result = BG11;
                break;
            case 12:
                result = BG12;
                break;
            case 13:
                result = BG13;
                break;
            case 14:
                result = BG14;
                break;
            case 15:
                result = BG15;
                break;
            default:
                result = 2;


        }
        Log.d("equ", String.valueOf(curriculum.get(0).get(4).courseName.equals(curriculum.get(1).get(2).courseName)));
        Log.d("equ",curriculum.get(1).get(2).courseName);


        return result;
    }
}
