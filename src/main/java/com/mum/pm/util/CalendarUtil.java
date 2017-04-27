package com.mum.pm.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chuang on 2017/4/26.
 */
@Component
public class CalendarUtil {

    //If put 0, will return current date
    public Date getDate(int day){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.add(Calendar.DATE, day);
        return today.getTime();
    }
}
