package com.lh.crm.common.untils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CntDayTime {
    public static String dayTime(String timeformat){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeformat);
        String crnTime = dateTime.format(formatter);
        return crnTime;
    }
}
