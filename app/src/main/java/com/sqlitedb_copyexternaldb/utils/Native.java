package com.sqlitedb_copyexternaldb.utils;

public class Native {
    public native String getName();

    static {
        System.loadLibrary("test");
    }
}
