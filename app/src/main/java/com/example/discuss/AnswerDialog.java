package com.example.discuss;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class AnswerDialog extends android.app.Dialog implements android.view.View.OnClickListener{
    private EditText et_answer;
    private Button publish_answer;
    private LeaveMyDialogListener listener;




    public interface LeaveMyDialogListener{
        public void onClick(View view);
    }

    public AnswerDialog(@NonNull Context context,LeaveMyDialogListener listener) {
        super(context);
        setContentView(R.layout.activity_answer_chief);
        Window window = this.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //window.getDecorView().setBackgroundResource(android.R.color.transparent);
        this.listener=listener;
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        publish_answer=findViewById(R.id.publish_answer);
        et_answer=findViewById(R.id.et_answer);

        publish_answer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
