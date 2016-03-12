package com.longten.ldzs.Library;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longten.ldzs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/9.
 */
public class BooksInfoAdapter extends RecyclerView.Adapter<BooksInfoAdapter.ViewHolder>{
    ArrayList<BookInfo> bookInfos;
    public BooksInfoAdapter(ArrayList<BookInfo> bookInfos){
        this.bookInfos = bookInfos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.bookinfos_item, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.bookName = bookInfos.get(position).;
        holder.bookName.setText("书    名："+ bookInfos.get(position).name);
        holder.bookAuthor.setText("作    者："+ bookInfos.get(position).authors);
        holder.bookPress.setText("出 版 社："+ bookInfos.get(position).press);
        holder.bookTime.setText("出版时间："+ bookInfos.get(position).time);
        if (position == bookInfos.size()){
            Log.d("ooo",String.valueOf(position));
        }

    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName;
        public TextView bookAuthor;
        public TextView bookPress;
        public TextView bookTime;
        public ViewHolder(View root) {
            super(root);
            bookName = (TextView) root.findViewById(R.id.book_name);
            bookAuthor = (TextView) root.findViewById(R.id.book_author);

            bookPress = (TextView) root.findViewById(R.id.book_press);
            bookTime = (TextView) root.findViewById(R.id.book_time);
        }
    }


}
