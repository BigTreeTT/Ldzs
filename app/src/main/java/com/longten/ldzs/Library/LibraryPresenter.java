package com.longten.ldzs.Library;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/8.
 */
public class LibraryPresenter {

    QueryActivity activity;
    BookDetailInfoActivity bookDetailInfoActivity;
    public static ArrayList<BookInfo> bookInfos;
    public static Vector<BookInfo> vectorBookInfo;
    public LibraryPresenter(BookDetailInfoActivity bookDetailInfoActivity){
        super();

        this.bookDetailInfoActivity = bookDetailInfoActivity;

    }


    public LibraryPresenter(QueryActivity activity) {
        super();
        this.activity = activity;
        bookInfos = new ArrayList<>();
        vectorBookInfo = new Vector<>();

    }

    public void getBooksInfoByTitle(String title, final int page) {

        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                String html = null;
                //String html2 = null;
                try {
                    html = LibraryApi.getBooksInfoByTitle(params[0], page);
                    // html2 =LibraryApi.getBookDetailInfo("c++");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return html;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s==null){
                    Toast.makeText(activity.getApplicationContext(),"暂无您要查询的内容！",Toast.LENGTH_SHORT).show();
                }else {
                    //libraryFragment.textView.append(s);
                    bookInfos = analyse(s);
                    Log.d("hello1", String.valueOf(bookInfos.size()));
                    //libraryFragment.textView.append(bookInfos.toString());
                    BookList.bookInfoArrayList = bookInfos;
                    //libraryFragment.recyclerView.setAdapter(new BooksInfoAdapter(bookInfos));
                    //getBookDetailInfo(2, 8);
                    //libraryFragment.setAdapter(bookInfos);
                    BooksInfoAdapter adapter = new BooksInfoAdapter(bookInfos);

                    activity.recyclerView.setAdapter(adapter);


                }




            }
        }.execute(title);
        Log.d("hello0", String.valueOf(bookInfos.size()));

    }

    private ArrayList<BookInfo> analyse(String html) {
        LibraryApi.getInstance();
        Document doc = Jsoup.parse(html);
        ArrayList<BookInfo> books = new ArrayList<>();
        Elements table = doc.getElementsByClass("xxtable");
        //libraryFragment.textView.setText(table.html());
        //每本书
        Elements trs = table.select("tr");
        for (Element tr : trs) {
            BookInfo bookInfo = new BookInfo();
            Elements a = tr.select("a");
            bookInfo.name = a.get(0).text();
            String regex = "(?<=\\s[=]).+";
            Matcher matcher = Pattern
                    .compile(regex)
                    .matcher(bookInfo.name);
            if (matcher.find()) {
                bookInfo.name = matcher.group();
            }

            String str = tr.select("td").get(0).ownText();

            regex = ".*(?=\\s\\S*出版社)";
            matcher = Pattern
                    .compile(regex)
                    .matcher(str);
            if (matcher.find()) {
                bookInfo.authors = matcher.group();
            }
            regex = "\\S+出版社";
            matcher = Pattern.compile(regex)
                    .matcher(str);
            if (matcher.find()) {
                bookInfo.press = matcher.group();
            }

            regex = "(?<=出版社\\s).*";
            matcher = Pattern.compile(regex)
                    .matcher(str);
            if (matcher.find()) {
                bookInfo.time = matcher.group();
            }

            Elements input = tr.select("input");
            String value = input.attr("value");
            Log.d("input", value);
            regex = "(?<=[_]).*";
            matcher = Pattern.compile(regex)
                    .matcher(value);
            if (matcher.find()) {
                bookInfo.key = matcher.group();
            }


            books.add(bookInfo);
            bookInfos.add(bookInfo);
            vectorBookInfo.add(bookInfo);
            BookList.bookInfoArrayList.add(bookInfo);


        }

        bookInfos = books;
        //Log.d("hello", bookInfos.get(2).toString());
        //Log.d("hello", BookList.bookInfoArrayList.toString());
        //libraryFragment.textView.setText(bookInfos.toString());

        return bookInfos;
    }

    public BookDetailInfo getBookDetailInfo(int position, final int pageNumber) {
        final String key = bookInfos.get(position).key;
        final String name = bookInfos.get(position).name;
        Log.d("library-1-2", bookInfos.get(position).toString());
        new AsyncTask<String, String, String>() {


            @Override
            protected String doInBackground(String... params) {
                String html = null;
                try {
                    html = LibraryApi.getBookDetailInfo(name, pageNumber, key);

                    // Log.d("hi",html);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return html;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //libraryFragment.textView.append(s);
                ArrayList<BookDetailInfo> bookDetailInfos = analyseDetail(s);
                //libraryFragment.textView.append(bookDetailInfos.toString());
               // bookDetailInfoActivity.textView.setText(bookDetailInfos.toString());
                bookDetailInfoActivity.listView.setAdapter(
                        new BookDetailInfoAdapter(
                                bookDetailInfoActivity.getApplicationContext(),
                                bookDetailInfos)
                );

            }
        }.execute();

        return null;
    }

    private ArrayList<BookDetailInfo> analyseDetail(String html) {
        Document doc = Jsoup.parse(html);
        ArrayList<BookDetailInfo> books = new ArrayList<>();


        Elements table = doc.getElementsByClass("bordermain");
        Elements trs = table.select("tr");
       // libraryFragment.textView.append(trs.html());
        for (int i = 1; i < trs.size()-1; i++) {
            Element td = trs.get(i);
            BookDetailInfo bookDetailInfo = new BookDetailInfo();

            bookDetailInfo.loginKey = td.getAllElements().get(1).text();
            bookDetailInfo.Addr = td.getAllElements().get(5).text();
            bookDetailInfo.Type = td.getAllElements().get(6).text();
            bookDetailInfo.price = td.getAllElements().get(7).text();
            bookDetailInfo.state = td.getAllElements().get(8).text();
            books.add(bookDetailInfo);


        }


        return books;
    }



    interface Listner{
        public void setAdapter(ArrayList<BookInfo> bookInfos);


    }


}
