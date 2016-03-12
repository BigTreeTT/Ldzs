package com.longten.ldzs.GradeScore;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.longten.ldzs.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QureryScoreActivity extends AppCompatActivity {
    GradePresenter presenter;
    //public TextView text;
    RecyclerView recyclerView;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qurery_score);
        presenter = new GradePresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.score_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 1);
        showChoiceDialog(position);
       // text = (TextView) findViewById(R.id.text123);
    }

    public void showChoiceDialog(int position) {
        switch (position) {
            case 0:
                //查询学期成绩
                showXqDialog();

                break;
            case 1:
                //查询学年成绩
                showXnDialog();
                break;
            case 2:
                //查询总成绩
                presenter.queryAllScore();
                break;
            case 3:
                //查询未通过科目
                break;
            case 4:
                //查询成绩统计
                break;
            default:
                break;

        }


    }

    /**
     *
     * 查询指定学期成绩
     */

    private void showXqDialog() {
        View view = getLayoutInflater().inflate(R.layout.scoredialog, null);
        final Spinner spinner_xn = (Spinner) view.findViewById(R.id.spinner_xn);
        final Spinner spinner_xq = (Spinner) view.findViewById(R.id.spinner_xq);


        new AlertDialog.Builder(this)
                .setTitle("选择查询条件")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position_xn = spinner_xn.getSelectedItemPosition();
                        String xn = (String) spinner_xn.getItemAtPosition(position_xn);
                        String regex = ".*(?=[学][年])";

                        Matcher matcher = Pattern.compile(regex).matcher(xn);
                        if (matcher.find()) {
                            xn = matcher.group();
                        }

                        int position_xq = spinner_xq.getSelectedItemPosition();
                        String xq = (String) spinner_xq.getItemAtPosition(position_xq);
                        Toast.makeText(getApplicationContext(), xn + xq, Toast.LENGTH_SHORT).show();

                        switch (xq) {
                            case "第一学期":
                                xq = "1";
                                break;
                            case "第二学期":
                                xq = "2";
                                break;
                        }
                        presenter.queryXqScore(xn, xq);




                    }
                })
                .setNegativeButton("取消", null)
                .show();


    }


    /**
     *
     * 查询指定学年成绩
     */
    private void showXnDialog() {
        View view = getLayoutInflater().inflate(R.layout.scorexndialog, null);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);


        new AlertDialog.Builder(this)
                .setTitle("选择查询条件")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = spinner.getSelectedItemPosition();
                        String xn = (String) spinner.getItemAtPosition(position);
                        String regex = ".*(?=[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5])";
                        Matcher matcher = Pattern.compile(regex).matcher(xn);
                        if (matcher.find()){
                            xn = matcher.group();
                        }


                        Toast.makeText(getApplicationContext(), xn, Toast.LENGTH_SHORT).show();
                        presenter.queryXnScore(xn);


                    }
                })
                .setNegativeButton("取消", null)
                .show();


    }



    /**
     *
     * 查询指定学期成绩，平均绩点，分数，最高，最低，未通过科目并加载
     */
    public void queryYearGradeScore(String xn){


    }

    /**
     * 查询指定学年成绩，平均绩点，分数，最高，最低，未通过科目
     *
     *
     */
    public void queryTermGradeScore(String xn,String xq){




    }



    /**
     *
     * 查询统计信息
     * 所有学期成绩，平均绩点，分数，最高，最低，未通过科目
     *
     */
    public void statisticalGradeScore(){



    }
}
