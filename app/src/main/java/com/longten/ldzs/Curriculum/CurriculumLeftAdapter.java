package com.longten.ldzs.Curriculum;

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
public class CurriculumLeftAdapter extends RecyclerView.Adapter<CurriculumLeftAdapter.MyViewHolder>{
    String []data = {"1","2","3","4","5","6","7","8","9","10"};
    Context context;
    public CurriculumLeftAdapter(Context context){

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.listview_item,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length-2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);

        }
    }

}
