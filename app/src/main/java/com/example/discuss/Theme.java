package com.example.discuss;

import java.time.LocalDateTime;
import java.util.Date;

public class Theme {//作为listview适配类型
    private int touXiang;
    private String nickName;
    private String publishTime;
    private String theme;
    private String content;
    private String course;
    private int thumbUpNum;
    private int answerNum;
    private int zhuanNum;


    public Theme(String publishTime,String theme,String course){
        this.publishTime=publishTime;
        this.theme=theme;
        this.course=course;
    }

    public Theme(int touXiang,String nickName,String publishTime,String theme,int thumbUpNum,int answerNum,int zhuanNum){
        this.touXiang=touXiang;
        this.nickName=nickName;
        this.publishTime=publishTime;
        this.theme=theme;
        this.thumbUpNum=thumbUpNum;
        this.answerNum=answerNum;
        this.zhuanNum=zhuanNum;
    }

    public int getTouXiang(){return touXiang;}
    public String getNickName(){return nickName;}
    public String getPublishTime(){return publishTime;}
    public String getTheme(){return theme;}
    public String getContent(){return content;}
    public String getCourse(){return course;}
    public int getThumbUpNum(){return thumbUpNum;}
    public int getAnswerNum(){return answerNum;}
    public int getZhuanNum(){return zhuanNum;}
}
