package com.longten.ldzs.Library;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment{
    View fragment;
    //RecyclerView recyclerView;
    MainActivity activity;
    EditText search_et;
    ImageView btn_seach;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    public LibraryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment = inflater.inflate(R.layout.fragment_library, container, false);


        //textView = (TextView) fragment.findViewById(R.id.library_tv);
        //LibraryPresenter presenter = new LibraryPresenter(this);
        //presenter.getBooksInfoByTitle("c", 8);
        //presenter.getBookDetailInfo(bookInfos.get(2),8);
        //Log.d("hello2",BookList.bookInfoArrayList.toString());
        //presenter.getBookDetailInfo(2,8);
       // recyclerView = (RecyclerView) fragment.findViewById(R.id.books_recyclerView);
        //recyclerView.setLayoutManager(new GridLayoutManager(activity.getApplication(),1));
        search_et = (EditText) fragment.findViewById(R.id.search_library);
        btn_seach = (ImageView) fragment.findViewById(R.id.btn_search);
        btn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = search_et.getText().toString();
                activity.gotoQureryActivity(title);


            }
        });



        return fragment;
    }
    public interface Listener{
        public void gotoQureryActivity(String title);

    }





}
