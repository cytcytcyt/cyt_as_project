package com.example.discuss;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class chiefActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener,AdapterView.OnItemSelectedListener {
    private ImageButton grzx;
    private ImageButton fb;
    private String theme;
    private Spinner order;
    private Intent intent;
    private Bundle bundle;
    private List<Theme> themeList=new ArrayList<Theme>();
    private talkThemeAdapter adapter;
    private ListView listView;
    private ThemeOrderHelper themeOrderHelper;
    private AnswerDialog answerDialog;
    private EditText search;
//    private List<Theme> resultList=new ArrayList<>();

//    private TextView thumbUpNum;
private int num;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chief);

        grzx=(ImageButton)findViewById(R.id.grzx);
        fb=(ImageButton)findViewById(R.id.fb);
        order=(Spinner)findViewById(R.id.order);
        search=(EditText)findViewById(R.id.search) ;
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("S:"+s.toString());
                themeList.clear();
                MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(chiefActivity.this);
                SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
                Cursor cursor=myDatabase.query("themes",null,"Theme like ?", new String[]{"%"+s.toString()+"%"}, null, null, null, null);
                if(cursor.moveToFirst()) {

                    do {
                        String theme = cursor.getString(1);
                        String content=cursor.getString(2);
                        String publisher = cursor.getString(3);
                        String publishTime = cursor.getString(4);
                        int answerNum = cursor.getInt(5);
                        int thumbUpNum = cursor.getInt(6);
                        int zhuanNum = cursor.getInt(7);
                        //根据themes中publisher（学号）查学生姓名
                        Cursor cursor1 = myDatabase.query("stuInformation", new String[]{"Name"}, "Number=?", new String[]{publisher}, null, null, null, null);
                        if (cursor1.moveToFirst()) {
                            publisher = cursor1.getString(0);
                        }

                        Theme one = new Theme(R.drawable.photo, publisher, publishTime, theme, thumbUpNum, answerNum, zhuanNum);
                        themeList.add(one);
                    }while(cursor.moveToNext());
                }else{
                    Toast.makeText(chiefActivity.this,"无相关搜索",Toast.LENGTH_LONG).show();
                }
                putAdapterInList();
            }
        });
//        thumbUpNum=(TextView)findViewById(R.id.thumbUpNum);


//        View view = View.inflate( this, R.layout.talktheme_item, null);
//        theme=(TextView)view.findViewById(R.id.theme);
//        ImageButton thumbUp=(ImageButton)findViewById(R.id.thumbUp);
//        ImageButton answer=(ImageButton)findViewById(R.id.answer);
//        ImageButton send=(ImageButton)findViewById(R.id.send);

        listView=(ListView)findViewById(R.id.talklist);

        themeOrderHelper=new ThemeOrderHelper();
        themeOrderHelper.initThemes(this,themeList);
        putAdapterInList();

        grzx.setOnClickListener(this);
        fb.setOnClickListener(this);

        listView.setOnItemClickListener(this);
        order.setOnItemSelectedListener(this);
    }

    //切换主题排列顺序
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] orderRules=order.getResources().getStringArray(R.array.orderrules);
        String orderRule=orderRules[position];
        System.out.println("orderRule:"+orderRule);
        switch (orderRule){
            case "按时间顺序":
                themeList.clear();
                themeOrderHelper.initThemes(this,themeList);
                putAdapterInList();
                System.out.println("按时间顺序");
                break;
            case "按时间倒序":
                themeList.clear();
                themeOrderHelper.initThemesByTimeDcs(this,themeList);
                putAdapterInList();
                System.out.println("按时间倒序");
                break;
            case "按回复数降序":
                themeList.clear();
                themeOrderHelper.initThemesByAnswerDcs(this,themeList);
                putAdapterInList();
                System.out.println("按回复数降序");
                break;
            case "按点赞数降序":
                themeList.clear();
                themeOrderHelper.initThemesByThumbDcs(this,themeList);
                putAdapterInList();
                System.out.println("按点赞数降序");
                break;
            default:
                System.out.println("default +++++");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void putAdapterInList(){
        adapter=new talkThemeAdapter(chiefActivity.this,R.layout.talktheme_item,themeList);
        ListView listview = (ListView) findViewById(R.id.talklist);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grzx:
                intent=new Intent(chiefActivity.this, personalActivity.class);
                bundle=getIntent().getExtras();
                System.out.println("到个人中心界面  username： "+bundle.getString("username"));
                intent.putExtras(bundle);
                System.out.println("放好bundle ");
                startActivity(intent);
                break;
            case R.id.fb:
                intent=new Intent(chiefActivity.this, publishActivity.class);
                bundle=getIntent().getExtras();
                System.out.println("到发布界面  username： "+bundle.getString("username"));
                intent.putExtras(bundle);
                System.out.println("放好bundle ");
                startActivity(intent);
                break;
            case R.id.thumbUp:
                TextView thumbUpNum=(TextView)findViewById(R.id.thumbUpNum);
                num=Integer.parseInt(thumbUpNum.getText().toString());
                num++;
                thumbUpNum.setText(String.valueOf(num));
                updateTheme(v.getTag().toString(),"ThumbUpNum");
                System.out.println("点赞 theme:"+v.getTag());
                break;
            case R.id.answer:
//                System.out.println("回答");
                TextView answerNum=(TextView)findViewById(R.id.answerNum);
                num=Integer.parseInt(answerNum.getText().toString());
                num++;
                answerNum.setText(String.valueOf(num));
                System.out.println("answerNum"+answerNum.getText());
                updateTheme(v.getTag().toString(),"answerNum");
                final String theme=v.getTag().toString();
                answerDialog=new AnswerDialog(this,new AnswerDialog.LeaveMyDialogListener() {
                    @Override
                    public void onClick(View view) {
                        switch(view.getId()){
                            case R.id.publish_answer:
                                answerTheme(theme);
                                System.out.println("回答 theme:"+theme);
                                break;
                            case R.id.publish_cancel:
                                break;
                            default:
                                break;
                        }
                    }
                });
                answerDialog.setCancelable(true);
                answerDialog.show();


//                System.out.println("回答 theme:"+theme);
                break;
            case R.id.send:
                TextView zhuanNum=(TextView)findViewById(R.id.zhuanNum);
                num=Integer.parseInt(zhuanNum.getText().toString());
                num++;
                zhuanNum.setText(String.valueOf(num));
                updateTheme(v.getTag().toString(),"ZhuanNum");
                System.out.println("转发 theme:"+v.getTag());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Theme chooseTheme=themeList.get(position);
        theme=chooseTheme.getTheme();


        System.out.println("chooseTheme.getTheme()"+chooseTheme.getTheme());
//        Toast.makeText(chiefActivity.this,chooseTheme.toString(),Toast.LENGTH_LONG).show();

        intent=new Intent(chiefActivity.this, themeAndAnswersActivity.class);
        bundle=getIntent().getExtras();
        System.out.println("到theme answer界面  username： "+bundle.getString("username"));
        bundle.putString("theme",chooseTheme.getTheme());
        bundle.putInt("photo",chooseTheme.getTouXiang());
        bundle.putString("name",chooseTheme.getNickName());
        bundle.putString("publishTime",chooseTheme.getPublishTime());
        bundle.putString("content",chooseTheme.getContent());
        System.out.println("chooseTheme.getContent():"+chooseTheme.getContent());
        bundle.putInt("ThumbUpNum",chooseTheme.getThumbUpNum());
        bundle.putInt("AnswerNum",chooseTheme.getAnswerNum());
        bundle.putInt("ZhuanNum",chooseTheme.getZhuanNum());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void updateTheme(String theme,String col){
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        String sql="update themes set "+col+"="+col+"+1 where Theme=\""+theme+"\"";
        System.out.println("sql="+sql);
        myDatabase.execSQL(sql);
    }

    public void answerTheme(String theme) {
        EditText answer = (EditText) answerDialog.findViewById(R.id.et_answer);
        int themeId = -1;
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(chiefActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("themes", new String[]{"Id"}, "Theme=?", new String[]{theme}, null, null, null, null);
        if (cursor.moveToFirst())
            themeId = cursor.getInt(0);
        ContentValues values = new ContentValues();
        values.put("ThemeId", themeId);
        values.put("Content", answer.getText().toString());
        bundle=getIntent().getExtras();
        values.put("Publisher", bundle.getString("username"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        values.put("PublishTime", simpleDateFormat.format(date));
        values.put("ThumbUpNum", 0);
        values.put("IsTeacher", 0);
        myDatabase.insert("answers", null, values);
        answerDialog.dismiss();
    }
}
