package com.longten.ldzs.GradeScore;

import android.os.AsyncTask;
import android.widget.Toast;

import com.longten.ldzs.jwcAPI.JwcAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/3.
 */
public class GradePresenter {

    public QureryScoreActivity activity;
    public ArrayList<GradeInfo> gradeInfos;

    public GradePresenter(QureryScoreActivity activity) {
        this.activity = activity;
        gradeInfos = new ArrayList<>();
    }

    /**
     * 获取学年成绩
     *
     * @param xn
     */
    public void queryXnScore(String xn) {

        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                String html = JwcAPI.getJwcAPIInstance().getScore();
                return html;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null){
                    analyse(s);
                }else{
                    Toast.makeText(activity.getApplicationContext(),"请求失败，请重试！",Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(xn);

    }

    /**
     * 获取指定学年学期成绩
     *
     * @param xn
     * @param xq
     */
    public void queryXqScore(String xn, String xq) {


    }

    /**
     * 获取历年成绩
     */
    public void queryAllScore() {


    }
    private void analyse(String html){
        Document doc = Jsoup.parse(html);
        Elements table = doc.getElementsByClass("datelist");
        Elements trs = table.select("tr");
//        GradeInfo gradeInfo = new GradeInfo();
//        gradeInfo.name = trs.get(3).getAllElements().get(4).text();
//        gradeInfos.add(gradeInfo);

        for (int i =1;i<trs.size();i++){
            Elements tds = trs.get(i).getAllElements();
            GradeInfo gradeInfo = new GradeInfo();
            gradeInfo.name = tds.get(4).text();
            gradeInfo.type = tds.get(5).text();
            gradeInfo.credit = tds.get(7).text();
            gradeInfo.GPA= tds.get(8).text();
            gradeInfo.score = tds.get(9).text();
            gradeInfo.makeUpEx= tds.get(11).text();
            gradeInfo.reStudy= tds.get(12).text();

            gradeInfos.add(gradeInfo);

        }

//        for (int i=1;i<trs.size();i++){
//            Element tr = trs.get(i);
//            //一科成绩
//            Elements tds = tr.getElementsByTag("td");
//            GradeInfo gradeInfo = new GradeInfo();
//
//            gradeInfo.name = trs.get(3).text();
//            gradeInfo.type = trs.get(4).ownText();
//            gradeInfo.credit = trs.get(5).ownText();
//            gradeInfo.GPA= trs.get(6).ownText();
//            gradeInfo.score = trs.get(7).ownText();
//            gradeInfo.makeUpEx= trs.get(9).ownText();
//            gradeInfo.reStudy= trs.get(10).getAllElements().get(3).text();
//
//
//            gradeInfos.add(gradeInfo);
//        }
       // Toast.makeText(activity.getApplicationContext(),gradeInfos.toString(),Toast.LENGTH_SHORT).show();;
        activity.text.setText(gradeInfos.toString());


    }


}
