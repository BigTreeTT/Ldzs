package com.longten.ldzs.Library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.longten.ldzs.R;

/**
 * Created by Administrator on 2016/3/9.
 */
public class RecyclerViewListener implements View.OnTouchListener {
    GestureDetector mGestureDetector;
    int position;
    interface Listener{
        public void onClick(int position);
    }

    public RecyclerViewListener(Context context, final Listener mListener){
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                mListener.onClick(position);
                return super.onSingleTapUp(e);
            }
        });


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.books_recyclerView);
        position = recyclerView.getChildAdapterPosition(
                recyclerView.findChildViewUnder(event.getX(),event.getY()));
        mGestureDetector.onTouchEvent(event);

        return false;
    }
}
