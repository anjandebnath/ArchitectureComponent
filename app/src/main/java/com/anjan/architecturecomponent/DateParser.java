package com.anjan.architecturecomponent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anjan Debnath on 1/3/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class DateParser {

    private static DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

    public static String convertDateToString(Date date) {
        String strDate = "";
        strDate = dateFormat1.format(date);
        return strDate;
    }
}
