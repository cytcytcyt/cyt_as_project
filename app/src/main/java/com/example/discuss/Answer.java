package com.example.discuss;

public class Answer {//作为listview适配类型
    private int photo;
    private String nickName;
    private String publishTime;
    private String theme;
    private String content;
    private int thumbUpNum;
    private int isTeacher;


    public Answer(String publishTime, String theme, String content) {
        this.publishTime = publishTime;
        this.theme = theme;
        this.content = content;
    }

    public Answer(int photo,String nickName,String publishTime,String theme,String content){
        this.photo = photo;
        this.nickName = nickName;
        this.publishTime = publishTime;
        this.theme = theme;
        this.content = content;
    }

    public Answer(int photo,String nickName,String publishTime,String content,int thumbUpNum){
        this.photo = photo;
        this.nickName = nickName;
        this.publishTime = publishTime;
        this.thumbUpNum = thumbUpNum;
        this.content = content;
    }

    public int getPhoto(){return photo;}
    public String getNickName() {
        return nickName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }

    public int getThumbUpNum() {
        return thumbUpNum;
    }

    public int getIsTeacher() {
        return isTeacher;
    }
}
