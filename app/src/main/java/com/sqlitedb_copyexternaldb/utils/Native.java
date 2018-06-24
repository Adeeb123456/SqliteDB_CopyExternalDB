package com.ufo.learnchinese2.utils;

public class Native {
    public native String getName();

    static {
        System.loadLibrary("test");
    }
}
