package com.longten.ldzs.Curriculum;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {

    View fragment;
    public TextView currTextView;
    String result;
    Handler handler;
    public MainActivity activity;


    public CurriculumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment/

        fragment = inflater.inflate(R.layout.fragment_curriculum, container, false);
        currTextView = (TextView) fragment.findViewById(R.id.curr_textView);
        File Dir =  activity.getApplicationContext().getFilesDir();
        File curr = new File(Dir,"lastUseCurriculum");
        if (!curr.exists()){

            try {
                curr.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        drawTable();
        return fragment;
    }

    /**
     *
     * 获取指定学期课表
     * 并且存入文件中
     *
     * @param condition
     * @return
     */

    public boolean importTable(String condition){


        return false;
    }

    /**
     * 获取当前学期课表，并且存入相应文件
     *
     *
     * @return
     */
    public boolean importCurrentTable(){
        CurrPresenter presenter = new CurrPresenter(this);


        return presenter.getTable("11");
    }
    /**
     *
     * 从指定文件中获取课表
     * 并绘至fragment
     *
     */
    public void drawTable(){
        CurriculumInfo curriculumInfo = new CurriculumInfo();
        File curr = new File(activity.getFilesDir(),"lastUseCurriculum");
        try {
            if (curr.exists()){
                FileInputStream in = new FileInputStream(curr);
                ObjectInputStream reader = new ObjectInputStream(in);
                curriculumInfo = (CurriculumInfo) reader.readObject();
                currTextView.setText(curriculumInfo.curriculum.toString()+"hello");
                //将数据以图表格式方式绘制
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }










}
