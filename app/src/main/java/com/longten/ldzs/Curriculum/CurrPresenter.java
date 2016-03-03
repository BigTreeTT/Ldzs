package com.longten.ldzs.Curriculum;

/**
 * Created by Administrator on 2016/3/3.
 */
public class CurrPresenter {
    /**
     *
     *解析以字符串返回的课表数据
     *
     */

    public void analyseResponse(){

    }

    public void drawTable(){
        /**
         * if 本地数据为空，则从服务器加载数据
         * else 从本地获取数据，然后加载
         */
        if (true){
            drawTableFromJwc();

        }else{
            drawTableFromLocal();

        }
    }

    /**
     * 从服务器获取数据，绘制课程表
     * 并且更新本地数据
     *
     */
    public void drawTableFromJwc(){



    }

    /**
     * 从本地获取数据，绘制课程表
     *
     */
    public void drawTableFromLocal(){


    }




}
