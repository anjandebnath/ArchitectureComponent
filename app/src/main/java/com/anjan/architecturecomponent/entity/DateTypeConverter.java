package com.anjan.architecturecomponent.entity;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anjan Debnath on 11/13/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class DateTypeConverter {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @TypeConverter
    public static Date stringTimeToDate(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String dateToStringTime(Date value) {

        return value == null ? null : df.format(value);
    }
}
