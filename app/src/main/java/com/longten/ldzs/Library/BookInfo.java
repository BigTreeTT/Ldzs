package com.longten.ldzs.Library;

/**
 * Created by Administrator on 2016/3/8.
 */
public class BookInfo {
    public String name;
    public String authors;
    public String press;
    public String time;
    public String key;

    @Override
    public String toString() {
        return name +" \n"+authors +" \n"+ press + " \n"+time+"\n" +key+"\n\n";

    }
}
