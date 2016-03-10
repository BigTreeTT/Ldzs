package com.longten.ldzs.GradeScore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ScoreRecyclerViewAdapter extends RecyclerView.Adapter<ScoreRecyclerViewAdapter.ViewHolder>{
    String []data = {"学期成绩","学年成绩","历年成绩","未通过成绩","成绩统计"};
    Context context;
    MainActivity activity;
    public ScoreRecyclerViewAdapter(MainActivity activity){


        this.context = activity.getApplication();
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.scorerecyleview_item,parent,false)
        );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(data[position]);
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "www", Toast.LENGTH_SHORT).show();
//            }
//        });
        View view = (View) holder.textView.getParent();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                activity.gotoQureryScoreActivity(position);



            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView ;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"www",Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    public class ScoreViewHoler {
    }
}
