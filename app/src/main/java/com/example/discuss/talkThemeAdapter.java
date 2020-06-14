package com.example.discuss;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class talkThemeAdapter extends ArrayAdapter<Theme>{
    private int resourceId;
    private Theme theme;
    private TextView talkTheme;

    /*参数说明
    Context context：上下文，用getContext（）可获取
    textViewResourceId：自定义的ListView的子项布局 id
    List<Fruit> objects：在ListView上的显示数据
    */
    public talkThemeAdapter(Context context, int textViewResourceId, List<Theme> objects) {
        super(context, textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){//重写getView(),这个方法在每个子项被滚到屏幕内都会被调用
         theme=getItem(position);//获取当前子项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //动态加载自定义的子项布局文件,中参数false代表只是声明Layout属性生效，但不会为这个View添加父布局.因为子项布局有了父布局，就不能添加到ListView中了
        ImageView touxiang=(ImageView)view.findViewById(R.id.touXiang);
        TextView nickName=(TextView) view.findViewById(R.id.nickName);
        TextView publishTime=(TextView) view.findViewById(R.id.publishTime);
        talkTheme=(TextView) view.findViewById(R.id.theme);
        TextView thumbUpNum=(TextView) view.findViewById(R.id.thumbUpNum);
        TextView answerNum=(TextView) view.findViewById(R.id.answerNum);
        TextView zhuanNum=(TextView) view.findViewById(R.id.zhuanNum);
        touxiang.setImageResource(theme.getTouXiang());
        nickName.setText(theme.getNickName());
        publishTime.setText(theme.getPublishTime());
        talkTheme.setText(theme.getTheme());
        thumbUpNum.setText(String.valueOf(theme.getThumbUpNum()));
        answerNum.setText(String.valueOf(theme.getAnswerNum()));
        zhuanNum.setText(String.valueOf(theme.getZhuanNum()));

        ImageButton thumbUp=(ImageButton)view.findViewById(R.id.thumbUp);
        ImageButton answer=(ImageButton)view.findViewById(R.id.answer);
        ImageButton send=(ImageButton)view.findViewById(R.id.send);

//        thumbUp.setOnClickListener(this);
        thumbUp.setTag(theme.getTheme());//theme.getTheme()
//        answer.setOnClickListener(this);
        answer.setTag(theme.getTheme());
//        send.setOnClickListener(this);
        send.setTag(theme.getTheme());
        return view;
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.thumbUp:
//                System.out.println("点赞");
//                System.out.println(v.getTag());
//                break;
//            case R.id.answer:
//
//                System.out.println("回答");
//                System.out.println(v.getTag());
//                break;
//            case R.id.send:
//                System.out.println("转发");
//                System.out.println(talkTheme.getText().toString());
//                break;
//            default:
//                break;
//        }
//    }

//    public void updateTheme(String theme,String col){
//        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
//        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
//    }
}
