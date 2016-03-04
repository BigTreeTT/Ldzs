package com.longten.ldzs.jwcAPI;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/29.
 */
public class JwcAPI {
    public static final String indexAdd = "http://220.168.44.238/";
    public static final String loginAdd = "default2.aspx";
    //public static final String mainPageAdd = "default2.aspx";
    public static String cookie = null;
    HttpClient httpClient;
    public static String studentName;
    public static String studentXh;

    /**
     * 单例模式，线程安全
     *
     */
    private static JwcAPI instance;
    private JwcAPI(){}
    public static synchronized JwcAPI getJwcAPIInstance(){
        if (instance==null){
            return new JwcAPI();
        }
        return instance;


    }
    public void printXHXM(){
        Log.d("test-xh-xm",studentName + studentXh);

    }







    /**
     * 登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return HttpClient的实例 如果登录失败返回null
     */
    public String login(String userName, String passWord, int jkl) {
        //获取cookie

        try {
            HttpGet indexGet = new HttpGet(indexAdd);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(indexGet);
            String cookie = response.getFirstHeader("Set-Cookie").getValue();
            Log.d("Cookie", cookie);


            /**
             *  模拟登录
             */

            HttpPost loginPost = new HttpPost(indexAdd + loginAdd);
            //禁止自动重定向
            loginPost.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
            //设置参数，编码为gb2312
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUKMTMzMzIxNTg3OWRkCI6FFDmmlh9WgeV6FG/RB8ibLSw="));
            pairs.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", "09394A33"));
            pairs.add(new BasicNameValuePair("__EVENTVALIDATION", "/wEWDAKl8emDCQKl1bKzCQLs0fbZDAKEs66uBwK/wuqQDgKAqenNDQLN7c0VAuaMg+INAveMotMNAoznisYGArursYYIAt+RzN8I+WBXmgDMJ6svAWqiRdPPuxYMdCc="));
            pairs.add(new BasicNameValuePair("txtUserName", userName));
            pairs.add(new BasicNameValuePair("TextBox2", passWord));
            pairs.add(new BasicNameValuePair("txtSecretCode", ""));
            pairs.add(new BasicNameValuePair("RadioButtonList1", "学生"));
            pairs.add(new BasicNameValuePair("Button1", ""));
            pairs.add(new BasicNameValuePair("lbLanguage", ""));
            loginPost.setEntity(new UrlEncodedFormEntity(pairs, "gb2312"));


            //发送post请求 ，手动重定向
            response = client.execute(loginPost);
            Log.d("statusCode", response.getStatusLine().toString());
            if (response.getStatusLine().getStatusCode() == 302) {
                String location = response.getFirstHeader("Location").getValue();
                Log.d("Location", location);
                HttpGet mainGet = new HttpGet(indexAdd + location);
                response = client.execute(mainGet);
                String mainHtml = EntityUtils.toString(response.getEntity());
                Log.d("mainHtml", mainHtml);
                httpClient = client;
                return mainHtml;

            }else{
                return  null;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
    /**
     *坑啊 ，要带上请求头
     *
     *
     *  获取个人信息
     *  get.setHeader("Referer", "http://220.168.44.238/xs_main.aspx?xh=20124562");
     *  get.setHeader("Host","220.168.44.238");
     *  http://220.168.44.238/xsgrxx.aspx?xh=20124562&xm=%C1%FA%CC%DA&gnmkdm=N121501
     *
     */

    public String getPersonInformation(){
        HttpGet get = new HttpGet(
                "http://220.168.44.238/xsgrxx.aspx?xh=20124562&xm=%C1%FA%CC%DA&gnmkdm=N121501");
        try {
            get.setHeader("Referer", "http://220.168.44.238/xs_main.aspx?xh=20124562");
            get.setHeader("Host","220.168.44.238");


            HttpResponse response = httpClient.execute(get);
            String re = EntityUtils.toString(response.getEntity());
            Log.d("test-re",re);
            Log.d("test-response-code", String.valueOf(response.getStatusLine()));
            if (response.getStatusLine().getStatusCode()==200){
                return re;
            }else{
                return null;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     *
     * 获取课表
     * http://220.168.44.238/xskbcx.aspx?xh=20124562&xm=%C1%FA%CC%DA&gnmkdm=N121603
     *
     *
     */
    public String getCurriculum(){

        String url ="http://220.168.44.238/xskbcx.aspx?xh="
                +JwcAPI.studentXh+"&xm="
                +URLEncoder.encode(JwcAPI.studentName+"gb2312")+"&gnmkdm=N121603";
        HttpGet get = new HttpGet(url);
        get.setHeader("Referer", "http://220.168.44.238/xs_main.aspx?xh=20124562");
        get.setHeader("Host","220.168.44.238");
        try {
            HttpResponse response = httpClient.execute(get);
            //发送get请求，并且把返回内容放入字符串result
            String result = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode()==200){
                return result;
            }else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


























    /**
     * 封装请求
     *
     * @return 09394A33
     */

    public StringBuilder getRequestData(String userName, String passWord) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("__VIEWSTATE", URLEncoder.encode("/wEPDwUKMTMzMzIxNTg3OWRkCI6FFDmmlh9WgeV6FG/RB8ibLSw=", "gb2312"));
            params.put("__VIEWSTATEGENERATOR", URLEncoder.encode("", "gb2312"));
            params.put("__EVENTVALIDATION", URLEncoder.encode
                    ("/wEWDAKl8emDCQKl1bKzCQLs0fbZDAKEs66uBwK/wuqQDgKAqenNDQLN7c0" +
                            "VAuaMg+INAveMotMNAoznisYGArursYYIAt+RzN8I+WBXmgDMJ6svAWqiRdPPuxYMdCc=", "gb2312"));
            params.put("txtUserName", URLEncoder.encode(userName, "gb2312"));
            params.put("TextBox2", URLEncoder.encode(passWord, "gb2312"));
            params.put("txtSecretCode", URLEncoder.encode("", "gb2312"));
            params.put("RadioButtonList1", URLEncoder.encode("学生", "gb2312"));
            params.put("Button1", URLEncoder.encode("", "gb2312"));
            params.put("lbLanguage", URLEncoder.encode("", "gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");


        }
        //删除最后一个"&"
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);


        return stringBuilder;
    }

    /**
     * 登录，并且设置cookie
     *
     * @param userName
     * @param passWord
     * @return 如果错误失败则返回false 成功则返回true
     */

    public boolean login(String userName, String passWord) {
        try {
            URL url = new URL(indexAdd);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            InputStream inputStream = conn.getInputStream();

            //获取cookie
            Map<String, List<String>> headers = conn.getHeaderFields();
            List<String> cookies = headers.get("Set-Cookie");
            StringBuilder cookie = new StringBuilder();
            for (int i = 0; i < cookies.size(); i++) {

                cookie = cookie.append(cookies.get(i));
            }
            Log.d("cookie", String.valueOf(cookie));

            //模拟登录，发送post请求
            url = new URL(indexAdd + loginAdd);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);//打开输出流，向服务器写入数据
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);//post请求不能被缓存
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Cookie", String.valueOf(cookie));
            StringBuilder request = getRequestData(userName, passWord);
            //获得输出流
//            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//            writer.write(String.valueOf(request));

            OutputStream out = conn.getOutputStream();
            out.write(new String(request).getBytes("gb2312"));

            if (conn.getResponseCode() == 302) {
                String loaction = conn.getHeaderField("Location");
                Log.d("location", loaction);
                URL urllocation = new URL(indexAdd + loaction);
                conn = (HttpURLConnection) urllocation.openConnection();
                conn.setRequestProperty("Cookie", String.valueOf(cookie));
                inputStream = conn.getInputStream();

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
                char buff[] = new char[1024];
                StringBuffer stringBuffer = new StringBuffer();
                int lenth = 0;
                while ((lenth = reader.read(buff)) != -1) {
                    stringBuffer.append(buff, 0, lenth);


                }
                Log.d("test-res1", String.valueOf(stringBuffer));

            }
            Log.d("test-responseCode", String.valueOf(conn.getResponseCode()));
            Log.d("test-url", url.toString());
            Log.d("test-cookie", String.valueOf(cookie));
            this.cookie = String.valueOf(cookie);
            return true;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }




}
