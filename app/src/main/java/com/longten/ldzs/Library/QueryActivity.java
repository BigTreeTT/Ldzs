package com.longten.ldzs.Library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.longten.ldzs.R;

import java.util.ArrayList;

public class QueryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LibraryPresenter presenter = new LibraryPresenter(this);
    ArrayList<BookInfo> bookInfos;
    int currentPage =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_query);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        bookInfos = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.books_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setOnTouchListener(
                new RecyclerViewListener(this, new RecyclerViewListener.Listener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(
                                getApplicationContext(),
                                String.valueOf(position),
                                Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).s
                       gotoBookDetailInfoAcyivity(position, currentPage);

                    }
                }));
        presenter.getBooksInfoByTitle(title, currentPage);

        //recyclerView.setScroll

    }

    public void gotoBookDetailInfoAcyivity(int position,int currentPage){
        Intent intent = new Intent(QueryActivity.this,BookDetailInfoActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("currentPage",currentPage);
        startActivity(intent);


    }


}
