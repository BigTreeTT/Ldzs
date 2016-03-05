package com.longten.ldzs.Curriculum;

import android.os.AsyncTask;
import android.util.Log;

import com.longten.ldzs.jwcAPI.JwcAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/3.
 */
public class CurrPresenter {

    private CurriculumFragment fragment;


    public CurrPresenter(CurriculumFragment fragment) {
        super();
        this.fragment = fragment;


    }

    /**
     * 获得当前学期课表信息
     * 并且存入文件中
     *
     * @return
     */

    public boolean getCurrentTable() {

        final JwcAPI jwcAPI = JwcAPI.getJwcAPIInstance();
        final String[] html = {null};
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                return jwcAPI.getCurrentCurriculum();
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);


                html[0] = responseString;
                if (html[0] != null) {
                    Log.d("html[0]",html[0]);
                    analyse(html[0]);

                }

            }
        }.execute();
        return true;



    }

    /**
     * 获取指定学期课表，
     * 并存入相应文件中
     *
     * @param condition
     * @return
     */
    public boolean getTable(final String condition) {
        final JwcAPI jwcAPI = JwcAPI.getJwcAPIInstance();
        final String[] html = {null};
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                return jwcAPI.getCurriculum(params[0]);
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);

                html[0] = responseString;
                if (html[0] != null) {
                    analyse(html[0]);

                }
            }
        }.execute(condition);

        return true;

    }

    /**
     * 根据输入的html解析出数据，
     * 并且存入文件中
     *
     * @param html
     */

    public void analyse(String html) {
        CurriculumInfo curriculumInfo = new CurriculumInfo();
        Document doc = Jsoup.parse(html);
        Element table = doc.getElementById("Table1");
        Elements trs = table.getElementsByTag("tr");
        ArrayList<ArrayList<String>> curriculum = new ArrayList<>();
        for (int i = 2; i < 12; ) {
            Elements tds = trs.get(i).select("td");//
//            for (int j = 0;j<tds.size();j++){
//                Element td = tds.get(j);
//                fragment.currTextView.append(td.text());
//                Log.d("element","---"+String.valueOf(i)+String.valueOf(j)+td.text());
//
//                //
//
//
//            }
            ArrayList<String> course = new ArrayList<>();
            if (i == 2 ||i ==6 || i==10){

                for (int k = 0;k<tds.size();k++){
                    if (k>=2){
                        course.add(tds.get(k).text());
                    }
                }


            }else{
                for (int k = 0;k<tds.size();k++){
                    if (k>=1){
                        course.add(tds.get(k).text());
                    }
                }

            }
            curriculum.add(course);
            i = i+2;
        }
        fragment.currTextView.setText(curriculum.toString());


    }


}
