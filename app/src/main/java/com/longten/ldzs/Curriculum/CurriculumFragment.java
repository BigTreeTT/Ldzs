package com.longten.ldzs.Curriculum;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {

    View fragment;
    public TextView currTextView;
    String result;
    Handler handler;


    public CurriculumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment/

        fragment = inflater.inflate(R.layout.fragment_curriculum, container, false);
        currTextView = (TextView) fragment.findViewById(R.id.curr_textView);
        importCurrentTable();
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



    }










}
