package com.longten.ldzs.Library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longten.ldzs.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/9.
 */
public class BookDetailInfoAdapter extends BaseAdapter{
    ArrayList<BookDetailInfo> bookDetailInfos;
    LayoutInflater inflater;

    public BookDetailInfoAdapter(Context context, ArrayList<BookDetailInfo> bookDetailInfos){
        this.bookDetailInfos = bookDetailInfos;
        inflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return bookDetailInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return bookDetailInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.bookdetailinfo_item,parent,false);
        BookDetailInfo bookDetailInfo = bookDetailInfos.get(position);
        TextView tvLoginKey = (TextView) convertView.findViewById(R.id.tv_loginkey);
        TextView tvAddr = (TextView) convertView.findViewById(R.id.tv_addr);
        TextView tvType = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvState = (TextView) convertView.findViewById(R.id.tv_state);
        position++;
        tvLoginKey.setText(bookDetailInfo.loginKey);
        tvAddr.setText(bookDetailInfo.Addr);
        tvPrice.setText(bookDetailInfo.price);
        tvState.setText(bookDetailInfo.state);
        tvType.setText(bookDetailInfo.Type);
        return convertView;
    }
}
