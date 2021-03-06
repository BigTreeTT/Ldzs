package com.longten.ldzs.Curriculum;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longten.ldzs.MainView.MainActivity;
import com.longten.ldzs.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {

    View fragment;
    //public TextView currTextView;
    String result;
    Handler handler;
    //ListView listView;
    //GridView gridView;
    RecyclerView curriculum_left;
    RecyclerView curriculum_body;
    public MainActivity activity;
    RecyclerView curriculum_header;


    public CurriculumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment/

        fragment = inflater.inflate(R.layout.fragment_curriculum, container, false);

        curriculum_header = (RecyclerView) fragment.findViewById(R.id.curriculum_header);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(
                        activity.getApplicationContext(),
                        7,
                        GridLayoutManager.VERTICAL,
                        false);


        curriculum_header.setLayoutManager(gridLayoutManager);

        curriculum_header.setAdapter(new CurriculumHeaderAdapter(activity.getApplicationContext()));

        //currTextView = (TextView) fragment.findViewById(R.id.curr_textView);
        File Dir =  activity.getApplicationContext().getFilesDir();
        File curr = new File(Dir,"lastUseCurriculum");
        if (!curr.exists()){

            try {
                curr.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //listView = (ListView) fragment.findViewById(R.id.listView_courseNumber);
        //listView.setAdapter(new LvcnAdapter(activity.getApplicationContext()));
        //gridView = (GridView) fragment.findViewById(R.id.curriculum_gridView);
        curriculum_left = (RecyclerView) fragment.findViewById(R.id.curriculum_left);
        curriculum_left.setLayoutManager(new GridLayoutManager(activity.getApplicationContext(),1));
        curriculum_body = (RecyclerView) fragment.findViewById(R.id.curriculum_body);



        if (curr.length()==0){
            importTable("21");
        }else {
            drawTable();
        }



        return fragment;
    }

    /**
     *
     * 获取指定学期课表
     * 并且存入文件中
     *
     * @param condition
     * @return
     */

    public boolean importTable(String condition){
        CurrPresenter presenter = new CurrPresenter(this);



        return  presenter.getTable(condition);
    }

    /**
     * 获取当前学期课表，并且存入相应文件
     *
     *
     * @return
     */
    public boolean importCurrentTable(){
        CurrPresenter presenter = new CurrPresenter(this);


        return presenter.getTable("21");
    }
    /**
     *
     * 从指定文件中获取课表
     * 并绘至fragment
     *[\u4e00-\u9fa5] 中文
     */

    public void drawTable(){
        CurriculumInfo curriculumInfo = new CurriculumInfo();
        File curr = new File(activity.getFilesDir(),"lastUseCurriculum");
        try {
            if (curr.exists()){
                FileInputStream in = new FileInputStream(curr);
                ObjectInputStream reader = new ObjectInputStream(in);
                curriculumInfo = (CurriculumInfo) reader.readObject();
                //currTextView.setText(curriculumInfo.curriculum.toString() + "hello");
                Log.d("hello",curriculumInfo.curriculum.toString());
                //将数据以图表格式方式绘制


                CurriculumShowInfo curriculumShowInfo = new CurriculumShowInfo();
                CurriculumShowInfo curriculumShowInfo2 = new CurriculumShowInfo();
                for (int i=0;i<curriculumInfo.curriculum.size();i++){
                    ArrayList<Course> courses = new ArrayList<>();
                    ArrayList<Course> courses2 = new ArrayList<>();
                    for (int j=0;j<curriculumInfo.curriculum.get(i).size();j++){
                        Course course = new Course();
                        Course course2 = new Course();
                        String input = curriculumInfo.curriculum.get(i).get(j);
                        //课程名
                        String regex ="\\S+(?=\\s周[\\u4e00-\\u9fa5]第)|\\b\\S+(?=\\s[{]第)";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(input);
                        if (matcher.find()){
                            //currTextView.append("课程:"+matcher.group());
                            course.courseName = matcher.group();
                            if (matcher.find()){
                                //currTextView.append("课程:"+matcher.group());
                                course2.courseName = matcher.group();

                            }else {
                                course2.courseName = null;
                            }
                            //currTextView.append("\n");
                        }else{
                            course.courseName = null;
                        }

                        //startTime
                        String regexStartTime ="(?<=第)\\d+(?=-\\d+周)";
                        Pattern patternStartTime = Pattern.compile(regexStartTime);
                        Matcher matcherStarTime = patternStartTime.matcher(input);
                        if (matcherStarTime.find()){
                            course.startTime = matcherStarTime.group();
                            if (matcherStarTime.find()){
                                course2.startTime = matcherStarTime.group();

                            }else {
                                course2.startTime = null;
                            }
                            //currTextView.append("\n");
                        }else{
                            course.startTime = null;
                        }

                        String regexStartTime2 ="(?<=第)\\d+(?=-\\d+周)";
                        Pattern patternStartTime2 = Pattern.compile(regexStartTime2);
                        Matcher matcherStarTime2 = patternStartTime2.matcher(input);

                        //endTime
                        Matcher matcherEndTime = Pattern
                                .compile("\\d+(?=周)")
                                .matcher(input);
                        if (matcherEndTime.find()){
                            course.endTime = matcherEndTime.group();
                            if (matcherEndTime.find()){
                                course2.endTime = matcherEndTime.group();
                            }


                        }
                        //teacher
                        Matcher matcherTeacher = Pattern
                                .compile("(?<=周[}]\\s)[\\u4e00-\\u9fa5]{2,6}(?=\\s)|(?<=[周][}]\\s\\d{6}[|])[\\u4e00-\\u9fa5]{2,6}(?=\\s)")
                                .matcher(input);
                        if (matcherTeacher.find()){
                            course.teacher = matcherTeacher.group();
                            if (matcherTeacher.find()){
                                course2.teacher = matcherTeacher.group();
                            }



                        }
                        //addr
                        String teacherName =null;
                        if (course.teacher!=null){
                            teacherName = course.teacher;
                        }else if(course2.teacher!=null){
                            teacherName = course2.teacher;
                        }

                        Matcher matcherAddr = Pattern
                                .compile("\\b\\S教[\\u4e00-\\u9fa5]*\\d{3}\\b|\\b[\\u4e00-\\u9fa5]{1,3}\\d{3}|(?<="+teacherName+ "\\s)\\S+")
                                .matcher(input);
                        if (matcherAddr.find()){
                            course.addr = matcherAddr.group();
                            if (matcherAddr.find()){
                                course2.addr = matcherAddr.group();
                            }

                        }





                        courses.add(course);
                        courses2.add(course2);


                    }
                    curriculumShowInfo.curriculum.add(courses);
                    curriculumShowInfo2.curriculum.add(courses2);




                }
                //currTextView.append(curriculumShowInfo.curriculum.toString());
                //currTextView.append(curriculumShowInfo2.curriculum.toString());
                /**
                 * 绘制课表body
                 *
                 */
//                GridViewAdpter gridViewAdpter = new GridViewAdpter(
//                        activity.getApplicationContext(),
//                        curriculumShowInfo,curriculumShowInfo2,1);
                //gridView.setAdapter(gridViewAdpter);
                curriculum_body.setLayoutManager(new GridLayoutManager(
                        activity.getApplicationContext(),
                        7
                ));
                int currentWeek = 2;
                CurriculumBodyAdapter curriculumBodyAdapter = new CurriculumBodyAdapter(
                        activity.getApplicationContext(),
                        curriculumShowInfo,
                        curriculumShowInfo2,
                        currentWeek
                );

                curriculum_body.setAdapter(curriculumBodyAdapter);
                curriculumBodyAdapter.setOnItemClickListener(new CurriculumBodyAdapter.Listener() {
                    @Override
                    public void onClick(Course course1, Course course2) {
                        activity.gotoCourseDetailActivity(course1,course2);

                    }
                });
                curriculum_left.setAdapter(new CurriculumLeftAdapter(activity.getApplicationContext()));
//               curriculum_body.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                   @Override
//                   public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                       listView.smoothScrollBy(oldScrollY-scrollX,10);
//                   }
//               });
                curriculum_body.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        curriculum_left.smoothScrollBy(dy, 10);
                    }
                });









            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }










}
