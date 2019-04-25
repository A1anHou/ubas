package com.alan.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getNeedTime(Date date,int hour, int minute, int second, int addDay, int ...args){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(addDay != 0){
            calendar.add(Calendar.DATE,addDay);
        }
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        if(args.length==1){
            calendar.set(Calendar.MILLISECOND,args[0]);
        }
        return calendar.getTime();
    }

    public static int getDuration(Date startTime,Date endTime,Date startLimit,Date endLimit){
        long st = startTime.getTime();
        long et = endTime.getTime();
        long sl = startLimit.getTime();
        long el = endLimit.getTime();
        if(st>el||et<sl){
            return 0;
        }
        if(st<sl){
            st=sl;
        }
        if(et>el){
            et=el;
        }
        return (int)(et-st)/(1000*60);
    }


}
