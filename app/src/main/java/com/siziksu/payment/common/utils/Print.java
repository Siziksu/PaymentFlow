package com.siziksu.payment.common.utils;

import android.util.Log;

import com.siziksu.payment.common.Constants;

public final class Print {

    private Print() {}

    public static void info(String string) {
        Log.i(Constants.TAG, string);
    }

    public static void error(String string) {
        Log.e(Constants.TAG, string);
    }

    public static void error(String string, Throwable throwable) {
        Log.e(Constants.TAG, string, throwable);
    }
}
