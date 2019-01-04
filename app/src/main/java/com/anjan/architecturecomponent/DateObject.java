package com.anjan.architecturecomponent;

/**
 * Created by Anjan Debnath on 1/3/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class DateObject extends ListObject {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType(int userId) {
        return TYPE_DATE;
    }
}
