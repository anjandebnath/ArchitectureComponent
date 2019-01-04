package com.anjan.architecturecomponent;

/**
 * Created by Anjan Debnath on 1/3/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public abstract class ListObject {
    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL_RIGHT = 1;
    public static final int TYPE_GENERAL_LEFT = 2;

    abstract public int getType(int userId);
}
