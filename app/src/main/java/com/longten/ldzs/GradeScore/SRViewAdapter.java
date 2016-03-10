package com.longten.ldzs.GradeScore;

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
 * Created by Administrator on 2016/3/10.
 */
public class SRViewAdapter extends RecyclerView.Adapter<SRViewAdapter.ScoreViewHoler>{
    Context context;
    ArrayList<GradeInfo> gradeInfos;
    public SRViewAdapter(Context context,ArrayList<GradeInfo> gradeInfos){
        this.context = context;
        this.gradeInfos = gradeInfos;
    }

    @Override
    public SRViewAdapter.ScoreViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        ScoreViewHoler viewHoler = new ScoreViewHoler(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.score_recyclerveiw_item,parent,false)
        );
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(SRViewAdapter.ScoreViewHoler holder, int position) {
        GradeInfo gradeInfo = gradeInfos.get(position);
        holder.name.setText(gradeInfo.name);
        holder.credit.setText(gradeInfo.credit);
        holder.GPA.setText(gradeInfo.GPA);
        holder.score.setText(gradeInfo.score);
        holder.makeUpEx.setText(gradeInfo.makeUpEx);
        holder.reStudy.setText(gradeInfo.reStudy);
        Log.d("aaaa",gradeInfo.toString());

    }

    @Override
    public int getItemCount() {
        return gradeInfos.size();
    }

    public class ScoreViewHoler extends RecyclerView.ViewHolder {
        TextView name;
        TextView credit;
        TextView GPA;
        TextView score;
        TextView makeUpEx;
        TextView reStudy;
        public ScoreViewHoler(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            credit = (TextView) itemView.findViewById(R.id.credit);
            GPA = (TextView) itemView.findViewById(R.id.GPA);
            score = (TextView) itemView.findViewById(R.id.score);
            makeUpEx = (TextView) itemView.findViewById(R.id.makeUpEx);
            reStudy = (TextView) itemView.findViewById(R.id.reStudy);
        }
    }
}
