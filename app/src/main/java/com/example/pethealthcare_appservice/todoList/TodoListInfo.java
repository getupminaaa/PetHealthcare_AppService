package com.example.pethealthcare_appservice.todoList;

public class TodoListInfo {
    private String str_todoName;
    private String str_todoDetail;
    private String str_check_mon;
    private String str_check_tue;
    private String str_check_wed;
    private String str_check_thr;
    private String str_check_fri;
    private String str_check_sat;
    private String str_check_sun;
    private String str_repeatOnOff;
    private String str_reNoti;
    private String hour;
    private String minute;

    public TodoListInfo(String str_todoName, String str_todoDetail, String str_check_mon
            , String str_check_tue, String str_check_wed, String str_check_thr
            , String str_check_fri, String str_check_sat, String str_check_sun
            , String str_repeatOnOff, String str_reNoti, String hour, String minute) {
        this.str_todoName = str_todoName;
        this.str_todoDetail = str_todoDetail;
        this.str_check_mon = str_check_mon;
        this.str_check_tue = str_check_tue;
        this.str_check_wed = str_check_wed;
        this.str_check_thr = str_check_thr;
        this.str_check_fri = str_check_fri;
        this.str_check_sat = str_check_sat;
        this.str_check_sun = str_check_sun;
        this.str_repeatOnOff = str_repeatOnOff;
        this.str_reNoti = str_reNoti;
        this.hour = hour;
        this.minute = minute;
    }

    public String getStr_todoName() {
        return str_todoName;
    }

    public void setStr_todoName(String str_todoName) {
        this.str_todoName = str_todoName;
    }

    public String getStr_todoDetail() {
        return str_todoDetail;
    }

    public void setStr_todoDetail(String str_todoDetail) {
        this.str_todoDetail = str_todoDetail;
    }

    public String getStr_check_mon() {
        return str_check_mon;
    }

    public void setStr_check_mon(String str_check_mon) {
        this.str_check_mon = str_check_mon;
    }

    public String getStr_check_tue() {
        return str_check_tue;
    }

    public void setStr_check_tue(String str_check_tue) {
        this.str_check_tue = str_check_tue;
    }

    public String getStr_check_wed() {
        return str_check_wed;
    }

    public void setStr_check_wed(String str_check_wed) {
        this.str_check_wed = str_check_wed;
    }

    public String getStr_check_thr() {
        return str_check_thr;
    }

    public void setStr_check_thr(String str_check_thr) {
        this.str_check_thr = str_check_thr;
    }

    public String getStr_check_fri() {
        return str_check_fri;
    }

    public void setStr_check_fri(String str_check_fri) {
        this.str_check_fri = str_check_fri;
    }

    public String getStr_check_sat() {
        return str_check_sat;
    }

    public void setStr_check_sat(String str_check_sat) {
        this.str_check_sat = str_check_sat;
    }

    public String getStr_check_sun() {
        return str_check_sun;
    }

    public void setStr_check_sun(String str_check_sun) {
        this.str_check_sun = str_check_sun;
    }

    public String getStr_repeatOnOff() {
        return str_repeatOnOff;
    }

    public void setStr_repeatOnOff(String str_repeatOnOff) {
        this.str_repeatOnOff = str_repeatOnOff;
    }

    public String getStr_reNoti() {
        return str_reNoti;
    }

    public void setStr_reNoti(String str_reNoti) {
        this.str_reNoti = str_reNoti;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
