package com.longten.ldzs.MainView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.longten.ldzs.Appraise.AppraiseFragment;
import com.longten.ldzs.Curriculum.Course;
import com.longten.ldzs.Curriculum.CourseDetail;
import com.longten.ldzs.Curriculum.CurriculumFragment;
import com.longten.ldzs.GradeScore.GradeScoreFragment;
import com.longten.ldzs.GradeScore.QureryScoreActivity;
import com.longten.ldzs.Library.LibraryFragment;
import com.longten.ldzs.Library.QueryActivity;
import com.longten.ldzs.R;
import com.longten.ldzs.jwcAPI.JwcAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LibraryFragment.Listener {
    public DrawerLayout drawerLayout;
    ListView nav_menu;
    ActionBarDrawerToggle toogle;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView headPortrait;
    JwcAPI jwcAPI;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    CurriculumFragment curriculumFragment;
    boolean is_first = true;
    TextView userName;

    String xhxm;
    String studentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //从LoginActivity中获取登录数据
        jwcAPI = JwcAPI.getJwcAPIInstance();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("loginInfo");
        xhxm = bundle.getString("xhxm");


        //设置登录者信息
        studentName = setUserName();
        JwcAPI.studentName = studentName;
        JwcAPI.studentXh = bundle.getString("xh");
        Log.d("test-xh-xm", JwcAPI.studentXh + "-" + JwcAPI.studentName);

    }

    /**
     * 完成初始化工作
     */

    public void init() {
        //nav_header headPortrait 的click事件
        headPortrait = (ImageView) findViewById(R.id.headPortrait);
        headPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("课表");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        //创建把手
        setSupportActionBar(toolbar);
        toogle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close
        );
        toogle.syncState();
        //设置navigation menu的监听器
        navigationView.setNavigationItemSelectedListener(this);

        //获得FragmentManager
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_con, new CurriculumFragment());
        transaction.commit();


    }

    /**
     * 使用正则表达式获取姓名，设置nav Head username
     * 返回姓名
     *
     * @return
     */

    public String setUserName() {
        userName = (TextView) findViewById(R.id.userName);
        //正则表达式
        Pattern pattern = Pattern.compile("\\b[\\u4e00-\\u9fa5].*同学");
        Matcher matcher = pattern.matcher(xhxm);
        String[] name = new String[5];
        if (matcher.find()) {
            xhxm = matcher.group();
            pattern = Pattern.compile("同学");
            name = pattern.split(xhxm);
            xhxm = name[0];
        }
        userName.setText(xhxm + "同学");
        return xhxm;

    }

    /**
     * 导航栏
     *
     * @param menuItem
     * @return
     */


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.nav_xk:


                toolbar.setTitle("选课");
                Toast.makeText(getApplicationContext(),"选课暂未开放",Toast.LENGTH_SHORT).show();


                break;
            case R.id.nav_cj:
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        JwcAPI.getJwcAPIInstance().getScore();
//                    }
//                }.start();
                toolbar.setTitle("成绩");
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_con, new GradeScoreFragment());
                transaction.commit();


                break;
            case R.id.nav_kb:
                toolbar.setTitle("课表");
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_con, new CurriculumFragment());
                transaction.commit();
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//
//                        JwcAPI jwcAPI = JwcAPI.getJwcAPIInstance();
//                        jwcAPI.getCurriculum("11");
//                        Log.d("test123456","1234"+jwcAPI.getCurrentCurriculum());
//                    }
//                }.start();


                break;
            case R.id.nav_pj:
                toolbar.setTitle("评价");
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_con, new AppraiseFragment());
                transaction.commit();
                Toast.makeText(getApplicationContext(),"教务评价暂未开放",Toast.LENGTH_SHORT).show();


                break;
            case R.id.nav_tsg:
                toolbar.setTitle("图书馆");

                /**
                 * 每次commit 后，要重新获取transaction
                 *
                 */
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_con, new LibraryFragment());
                transaction.commit();


                break;
//            case R.id.nav_gg:
//                toolbar.setTitle("公告");
//                transaction = fragmentManager.beginTransaction();
//
//                transaction.replace(R.id.fragment_con, new NewsFragment());
//                transaction.commit();
//                break;


            default:
                break;

        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    @Override
    public void gotoQureryActivity(String title) {

        if (title ==null){
            Toast.makeText(getApplicationContext(),"请输入要查询的内容！",Toast.LENGTH_SHORT).show();
            return;
        }else {
            Intent intent = new Intent(MainActivity.this, QueryActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }

    }

    public void gotoQureryScoreActivity(int position){

        Intent intent = new Intent(MainActivity.this, QureryScoreActivity.class);

        intent.putExtra("position", position);
        startActivity(intent);

    }
    public void gotoCourseDetailActivity(Course course1,Course course2){
        Intent intent = new Intent(MainActivity.this, CourseDetail.class);
        Bundle b = new Bundle();
        b.putSerializable("course1",course1);
        b.putSerializable("course2",course2);
        intent.putExtra("bundle", b);
        startActivity(intent);




    }



}
