package com.longten.ldzs.Library;

/**
 * Created by Administrator on 2016/3/8.
 */
public class BookDetailInfo {
    public String loginKey;
    public String Addr;
    public String Type;
    public String price;
    public String state;

    @Override
    public String toString() {
        return loginKey+"\n"+Addr+"\n"+Type+"\n"+price+"\n"+state+"\n";
    }
}
