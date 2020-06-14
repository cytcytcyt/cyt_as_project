package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class modifyActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private ImageButton back;
    private ImageButton modify;
    private EditText theme;
    private EditText content;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        theme=(EditText)findViewById(R.id.discussTitle);
        content=(EditText)findViewById(R.id.discussContent);
        back=(ImageButton) findViewById(R.id.back) ;
        back.setOnClickListener(this);
        modify=(ImageButton)findViewById(R.id.modify);
        modify.setOnClickListener(this);


        bundle=this.getIntent().getExtras();
        username=bundle.getString("username");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                //跳转到个人中心页面
                System.out.println("点击返回按钮");
                intent=new Intent(modifyActivity.this,personalActivity.class);
                bundle=getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.modify:
                //跳转到修改页面,
                System.out.println("点击修改按钮");
                bundle=getIntent().getExtras();
                String oldTheme=bundle.getString("modifyTheme");
                System.out.println("oldTheme:"+oldTheme);
                updateMyTheme(oldTheme,theme.getText().toString(),content.getText().toString());

                intent=new Intent(modifyActivity.this,personalActivity.class);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void updateMyTheme(String oldTheme,String newTitle,String newContent){
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        String sql="update themes set Content='"+newContent+"',Theme='"+newTitle+"' where Theme='"+oldTheme+"'";
        System.out.println("SQL:"+sql);
        myDatabase.execSQL(sql);
    }
}
