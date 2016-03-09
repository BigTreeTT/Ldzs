package com.longten.ldzs.GradeScore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ScoreRecyclerViewAdapter extends RecyclerView.Adapter<ScoreRecyclerViewAdapter.ViewHolder>{
    String []data = {"学期成绩","学年成绩","历年成绩","未通过成绩"};
    @Override
    public ScoreRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.scorerecyleview_item,null)
        );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView ;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
