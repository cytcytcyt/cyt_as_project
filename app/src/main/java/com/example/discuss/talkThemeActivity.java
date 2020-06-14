package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class talkThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talktheme_item);

        TextView theme=(TextView)findViewById(R.id.theme);
        System.out.println("theme:"+theme.getText());
        Bundle bundle=getIntent().getExtras();
        switch (bundle.getInt("choice")){
            case 1:
                System.out.println("t、点赞");
                break;
            case 2:
                System.out.println("t回复");
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
