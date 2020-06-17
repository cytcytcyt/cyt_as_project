package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class themeAndAnswersActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back;
    private TextView theme;
    private ImageView photo;
    private TextView nickName;
    private TextView publishTime;
    private TextView thumbUpNum;
    private TextView answerNum;
    private TextView zhuanNum;
    private TextView content;
    private Spinner order;
    private Intent intent;
    private Bundle bundle;
    private List<Answer> answerList=new ArrayList<>();
    private themeAnswersAdapter adapter;
    private ListView listView;
    private AnswerOrderHelper answerOrderHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_and_answers);

        photo=(ImageView)findViewById(R.id.touXiang);
        nickName=(TextView)findViewById(R.id.nickName);
        publishTime=(TextView)findViewById(R.id.publishTime);
        theme=(TextView)findViewById(R.id.theme);
        thumbUpNum=(TextView)findViewById(R.id.thumbUpNum);
        answerNum=(TextView)findViewById(R.id.answerNum);
        zhuanNum=(TextView)findViewById(R.id.zhuanNum);

        back=(ImageButton) findViewById(R.id.back) ;
        back.setOnClickListener(this);

//        order=(Spinner)findViewById(R.id.order);

        bundle=getIntent().getExtras();
        System.out.println("到主题回答界面  theme： "+bundle.getString("theme"));

        theme.setText(bundle.getString("theme"));
        photo.setImageResource(bundle.getInt("photo"));
        nickName.setText(bundle.getString("name"));
        publishTime.setText(bundle.getString("publishTime"));

        thumbUpNum.setText(String.valueOf(bundle.getInt("ThumbUpNum")));
        answerNum.setText(String.valueOf(bundle.getInt("AnswerNum")));
        zhuanNum.setText(String.valueOf(bundle.getInt("ZhuanNum")));

        listView=(ListView)findViewById(R.id.answerlist);

        answerOrderHelper=new AnswerOrderHelper();
        answerOrderHelper.initAnswers(this,answerList,bundle.getString("theme"));
        putAdapterInList();
    }

    public void putAdapterInList(){
        adapter=new themeAnswersAdapter(themeAndAnswersActivity.this,R.layout.answer_item,answerList);
        ListView listview = (ListView) findViewById(R.id.answerlist);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() )
        {
            case R.id.back:
                intent=new Intent(themeAndAnswersActivity.this,chiefActivity.class);
                bundle=getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.thumbUpAnswer:
                int num =0;
                System.out.println("我点啦！！！");
                TextView thumbUpNum=(TextView)findViewById(R.id.thumbUpNumAnswer);
                num=Integer.parseInt(thumbUpNum.getText().toString());
                num++;
                thumbUpNum.setText(String.valueOf(num));
                break;
            default: break;
        }
    }
}
