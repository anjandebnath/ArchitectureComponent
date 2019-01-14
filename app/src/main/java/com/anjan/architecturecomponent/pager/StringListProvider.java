package com.anjan.architecturecomponent.pager;

import com.anjan.architecturecomponent.ListObject;

import java.util.List;

/**
 * Created by Anjan Debnath on 1/4/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */

// PagedKey
public class StringListProvider {

    private List<ListObject> list;

    public StringListProvider(List<ListObject> list) {
        this.list = list;
    }

    public List<ListObject> getStringList(int page, int pageSize) {

        int initialIndex = page * pageSize;
        int finalIndex = initialIndex + pageSize;

        //TODO manage out of range index
        return list.subList(initialIndex, finalIndex);
    }
}
