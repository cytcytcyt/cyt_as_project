package com.example.discuss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class myAnswersAdapter extends ArrayAdapter<Answer> {
    private int resourceId;

    /*参数说明
    Context context：上下文，用getContext（）可获取
    textViewResourceId：自定义的ListView的子项布局 id
    List<Fruit> objects：在ListView上的显示数据
    */
    public myAnswersAdapter(Context context, int textViewResourceId, List<Answer> objects) {
        super(context, textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){//重写getView(),这个方法在每个子项被滚到屏幕内都会被调用
        Answer answer=getItem(position);//获取当前子项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //动态加载自定义的子项布局文件,中参数false代表只是声明Layout属性生效，但不会为这个View添加父布局.因为子项布局有了父布局，就不能添加到ListView中了

        TextView publishTime=(TextView) view.findViewById(R.id.publishTime);
        TextView corseOrTheme=(TextView) view.findViewById(R.id.courseortheme);
        TextView themeOrAnswer=(TextView) view.findViewById(R.id.themeoranswer);

        publishTime.setText(answer.getPublishTime());
        corseOrTheme.setText(answer.getTheme());
        themeOrAnswer.setText(answer.getContent());

        return view;
    }
}
