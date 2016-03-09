package com.longten.ldzs.GradeScore;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradeScoreFragment extends Fragment {
    View fragment;
    RecyclerView recyclerView;
    MainActivity activity;




    public GradeScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragment = inflater.inflate(R.layout.fragment_grade_score, container, false);
        recyclerView = (RecyclerView) fragment.findViewById(R.id.score_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(activity.getApplication(),2));
        recyclerView.setAdapter(new ScoreRecyclerViewAdapter());

        return fragment;
    }



    /**
     *
     * 查询指定学期成绩，平均绩点，分数，最高，最低，未通过科目并加载
     */
    public void queryYearGradeScore(String condition){


    }

    /**
     * 查询指定学年成绩，平均绩点，分数，最高，最低，未通过科目
     *
     * @param condition
     */
    public void queryTermGradeScore(String condition){



    }



    /**
     *
     * 查询统计信息
     * 所有学期成绩，平均绩点，分数，最高，最低，未通过科目
     *
     */
    public void statisticalGradeScore(){



    }





}
