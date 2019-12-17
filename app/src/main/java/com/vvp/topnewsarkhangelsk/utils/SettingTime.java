package com.vvp.topnewsarkhangelsk.utils;

import java.util.Calendar;
import java.util.Date;

public class SettingTime {

    // начальное время
    public static long getFirstUnixTime(){

        // текущее время
        Date firstDate = new Date();

        //начальный период
        firstDate.setHours(00);
        firstDate.setMinutes(00);
        firstDate.setSeconds(00);


        // задаем время дял перевода в Unix
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);

        // Unix формат
        long firstUnix = (firstCalendar.getTimeInMillis()) / 1000;

        return firstUnix;
    }


    //конечное время
    public static long getSecondUnixTime(){

        // текущее время
        Date secondDate = new Date();

        //конечный период
        secondDate.setHours(23);
        secondDate.setMinutes(59);
        secondDate.setSeconds(59);

        // задаем время дял перевода в Unix
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);

        // Unix формат
        long secondUnix = (secondCalendar.getTimeInMillis()) / 1000;

        return secondUnix;
    }









}
