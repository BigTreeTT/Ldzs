package com.longten.ldzs.Curriculum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/11.
 */
public class CurriculumHeaderAdapter extends RecyclerView.Adapter<CurriculumHeaderAdapter.MyViewHolder>{
    Context context;


    String []data = {"周一","周二","周三","周四","周五","周六","周日"};
    public CurriculumHeaderAdapter(Context context){
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater
                .from(context).inflate(R.layout.curriculum_header_body,parent,false)
        );
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.curriculum_date);
        }
    }
}
