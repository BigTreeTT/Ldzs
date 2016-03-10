package com.longten.ldzs.GradeScore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.longten.ldzs.R;

public class QureryScoreActivity extends AppCompatActivity {
    GradePresenter presenter;
    //public TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qurery_score);
        presenter = new GradePresenter(this);
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

                        int position_xq = spinner_xq.getSelectedItemPosition();
                        String xq = (String) spinner_xq.getItemAtPosition(position_xq);

                        Toast.makeText(getApplicationContext(), xn + xq, Toast.LENGTH_SHORT).show();


                    }
                })
                .setNegativeButton("取消", null)
                .show();


    }

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
