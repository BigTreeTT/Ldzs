package com.longten.ldzs.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;
import com.longten.ldzs.jwcAPI.JwcAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LoginActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.et_username);
        passWord = (EditText) findViewById(R.id.et_password);


        /**
         * 在异步任务中登录
         *
         */

       try {
           findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   final String userNameStr = userName.getText().toString();
                   final String passWordStr = passWord.getText().toString();
                   new AsyncTask<String,String,String>(){


                       @Override
                       protected String doInBackground(String... params) {
                           String xh = params[0];
                           String pw = params[1];
                           JwcAPI jwcAPI = JwcAPI.getJwcAPIInstance();
                           String html = jwcAPI.login(xh,pw,1);
                           Log.d("testhtml",html);
                           if (html ==null){
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(), "请检查密码和网络!", Toast.LENGTH_SHORT).show();
                                   }
                               });
                           }else{

                               String xhxm = analyseHtml(html);
                               Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                               Bundle bundle = new Bundle();
                               bundle.putString("xhxm",xhxm);
                               bundle.putString("xh",userNameStr);
                               intent.putExtra("loginInfo",bundle);

                               startActivity(intent);
                           }


                           return html;
                       }
                   }.execute("20124562","431021199307018314");
               }
           });

       }catch (Exception e){
           Toast.makeText(getApplicationContext(),"请检查网络，重新登录",Toast.LENGTH_SHORT).show();
       }


    }
    public String analyseHtml(String html){
        Document doc = Jsoup.parse(html);
        Element element = doc.getElementById("xhxm");
        String xhxm = element.text();
        Log.d("test-xhxm",xhxm);

        return xhxm;
    }
}
