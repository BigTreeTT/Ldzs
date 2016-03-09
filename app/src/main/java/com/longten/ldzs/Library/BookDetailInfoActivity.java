package com.longten.ldzs.Library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.longten.ldzs.R;

public class BookDetailInfoActivity extends AppCompatActivity {
    LibraryPresenter presenter;
    //TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_info);
        presenter = new LibraryPresenter(this);
        //textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",1);
        int pageNumber = intent.getIntExtra("currentPage",1);
        presenter.getBookDetailInfo(position,pageNumber);








    }


}
