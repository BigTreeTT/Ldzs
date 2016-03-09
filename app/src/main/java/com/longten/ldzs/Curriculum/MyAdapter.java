package com.longten.ldzs.Curriculum;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/3/7.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    CurriculumShowInfo curricuShowInfo;
    CurriculumShowInfo curricuShowInfo2;
    public MyAdapter(CurriculumShowInfo curricuShowInfo,CurriculumShowInfo curricuShowInfo2){
        this.curricuShowInfo = curricuShowInfo;
        this.curricuShowInfo2 = curricuShowInfo2;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        View root;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
        }
    }
}
