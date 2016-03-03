package com.longten.ldzs.Curriculum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longten.ldzs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {

    View fragment;


    public CurriculumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment/

        fragment = inflater.inflate(R.layout.fragment_curriculum, container, false);
        return fragment;
    }

    /**
     *
     * 打开app ，加载课程
     *
     *
     */
    public void drawTable(){
        CurrPresenter presenter = new CurrPresenter();


    }
    /**
     *
     * 手动更新课程表
     *
     */
    public void updateTable(){
        CurrPresenter presenter = new CurrPresenter();


    }



}
