package com.longten.ldzs.Library;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class LibraryApi {
    public static String charset = "GBK";
    public static HttpClient httpClient;

    private static LibraryApi instance;

    private LibraryApi() {
    }

    public static LibraryApi getInstance() {
        if (instance == null) {
            instance = new LibraryApi();
            httpClient = new DefaultHttpClient();
        }
        return instance;

    }

    /***
     * http://222.22.255.106:8089/opac/jdjsjg.jsp
     */
//    page:1
//    oper:and
//    addquery:false
//    changpage:false
//    geshi:bgfm
//    ifface:true
//    filterfl:
//    filterdcd:
//    filtersub:
//    filterkdm:
//    viewallsub:false
//    jstj:title
//    jsc:(unable to decode value)
//    sort:datestr
//    orderby:desc


//    Host:222.22.255.106:8089
//    Origin:http://222.22.255.106:8089
//    Referer:http://222.22.255.106:8089/opac/jdjsjg.jsp
    public static String getBooksInfoByTitle(String title, int pageNumber) throws IOException {
        URL url = new URL("http://222.22.255.106:8089/opac/jdjsjg.jsp");

        HttpPost post = new HttpPost("http://222.22.255.106:8089/opac/jdjsjg.jsp");
        post.setHeader("Host", "222.22.255.106:8089");
        post.setHeader("Origin", "http://222.22.255.106:8089");
        post.setHeader("Referer", "http://222.22.255.106:8089/opac/jdjsjg.jsp");
        /**
         *
         */
        String page = String.valueOf(pageNumber);
        String operoper = "and";
        String addquery = "false";
        String changpage = "false";
        String geshi = "bgfm";
        String ifface = "true";
        String filterfl = "";
        String filterdcd = "";
        String filtersub = "";
        String filterkdm = "";
        String viewallsub = " false";
        String jstj = "title";
        String jsc = title;
        String sort = "datestr";
        String orderby = "desc";

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("page", page));
        pairs.add(new BasicNameValuePair("addquery", addquery));
        pairs.add(new BasicNameValuePair("geshi", changpage));
        pairs.add(new BasicNameValuePair("geshi", geshi));
        pairs.add(new BasicNameValuePair("ifface", ifface));
        pairs.add(new BasicNameValuePair("filterfl", filterfl));
        pairs.add(new BasicNameValuePair("filterdcd", filterdcd));
        pairs.add(new BasicNameValuePair("filtersub", filtersub));
        pairs.add(new BasicNameValuePair("filterkdm", filterkdm));
        pairs.add(new BasicNameValuePair("viewallsub", viewallsub));
        pairs.add(new BasicNameValuePair("jsc", jsc));
        pairs.add(new BasicNameValuePair("sort", sort));
        pairs.add(new BasicNameValuePair("orderby", orderby));
        pairs.add(new BasicNameValuePair("jstj", jstj));
        pairs.add(new BasicNameValuePair("operoper", operoper));

        post.setEntity(new UrlEncodedFormEntity(pairs, LibraryApi.charset));
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        String html = null;
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            html = EntityUtils.toString(httpResponse.getEntity());
            Log.d("library", html);
        }
        Log.d("library", String.valueOf(httpResponse.getStatusLine().getStatusCode()));


        return html;
    }


 /*  page:1
    oper:and
    addquery:false
    changpage:false
    geshi:bgfm
    ifface:true
    filterfl:
    filterdcd:
    filtersub:
    filterkdm:
    viewallsub:false
    jstj:title
    jsc:c++
    mypagecount1:1
    sort:datestr
    orderby:desc
    mypagecount1:1*/

    /**
     * Host:222.22.255.106:8089
     * Origin:http://222.22.255.106:8089
     * Referer:http://222.22.255.106:8089/opac/jdjsjg.jsp
     * url :http://222.22.255.106:8089/opac/ckgc.jsp?kzh=zyl0009976
     * http://222.22.255.106:8089/opac/ckgc.jsp?kzh=zyl0010116
     */
    public static String getBookDetailInfo(String name, int pageNumber, String key) throws IOException {
        String url = "http://222.22.255.106:8089/opac/ckgc.jsp?kzh=" + key;
        Log.d("library-1-2-3", url);

        String jsc = name;
        String kzh = key;
        String page = String.valueOf(pageNumber);

        HttpPost post = new HttpPost(url);

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("page",page));
        pairs.add(new BasicNameValuePair("oper","and"));
        pairs.add(new BasicNameValuePair("addquery","false"));
        pairs.add(new BasicNameValuePair("changpage","false"));
        pairs.add(new BasicNameValuePair("geshi","bgfm"));
        pairs.add(new BasicNameValuePair("ifface","true"));
        pairs.add(new BasicNameValuePair("filterfl",""));
        pairs.add(new BasicNameValuePair("filterdcd",""));
        pairs.add(new BasicNameValuePair("filtersub",""));
        pairs.add(new BasicNameValuePair("filterkdm",""));
        pairs.add(new BasicNameValuePair("viewallsub","false"));
        pairs.add(new BasicNameValuePair("jstj","title"));
        pairs.add(new BasicNameValuePair("jsc",jsc));
        pairs.add(new BasicNameValuePair("mypagecount1","1"));
        pairs.add(new BasicNameValuePair("sort","datestr"));
        pairs.add(new BasicNameValuePair("orderby","desc"));
        pairs.add(new BasicNameValuePair("mypagecount1", page));

        post.setEntity(new UrlEncodedFormEntity(pairs, LibraryApi.charset));


        post.setHeader("Host", "222.22.255.106:8089");
        post.setHeader("Origin", "http://222.22.255.106:8089");
        post.setHeader("Referer", "http://222.22.255.106:8089/opac/jdjsjg.jsp");
        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(post);
        String html =null;
        if (response.getStatusLine().getStatusCode()==200){
            html = EntityUtils.toString(response.getEntity());
            Log.d("library-123",html);
            Log.d("library","ok detail!");
        }

        return html;





    }
}
