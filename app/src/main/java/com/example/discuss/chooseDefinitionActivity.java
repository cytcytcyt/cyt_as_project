package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseDefinitionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button amStu;
    private Button amTea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_definition);

        amStu=(Button)findViewById(R.id.amStu);
        amTea=(Button)findViewById(R.id.amTea);

        amStu.setOnClickListener(this);
        amTea.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          if (v.getId()==R.id.amStu){
              Intent intent=new Intent(chooseDefinitionActivity.this,resignActivity.class);
              intent.putExtra("StuOrTea",1);
              startActivity(intent);
          }else if (v.getId()==R.id.amTea){
              Intent intent=new Intent(chooseDefinitionActivity.this,resignActivity.class);
              intent.putExtra("StuOrTea",-1);
              startActivity(intent);
          }
    }
}
