package com.ufo.learnchinese2.utils;

import android.app.Activity;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.ufo.learnchinese2.R;
public class Utils {
    public static String CATEGORY_ID = "category_id";
    public static String DEVICE_LANGUAGE = "en";
    public static String GRAMMAR_COLUMN_CONTENT = "content";
    public static String GRAMMAR_COLUMN_ID = "_id";
    public static String GRAMMAR_COLUMN_NAME = "title";
    public static String GRAMMAR_DATABASE_NAME = "enlesslove.dat";
    public static final int GRAMMAR_DATABASE_VERSION = 1;
    public static String GRAMMAR_TABLE = "npt";
    public static String KEY_SEARCH_FRAGMENT = "search_fragment";
    public static String KEY_FAV_FRAGMENT = "fav_fragment";
    public static String LANGUAGE_COLLUNM = "chinese";
    public static String LESSON_TYPE = "lesson";
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static String PHRASE_DATABASE_NAME = "enlesslove.dat";
    public static final int PHRASE_DATABASE_VERSION = 12;
    public static String PHRASE_TABLE = "temp";
    public static final String PREF_PREMIUM_KEY = "premium_key";
    public static String TABLE_GRAMMAR = "NguPhap";
    public static String TABLE_IDIOMS = "ThanhNgu";
    public static String TABLE_LESSONS = "BaiHoc";
    public static String TABLE_READING = "LuyenDoc";
    public static String TABLE_VOCABULARY = "TuVung";
    public static String TABLE_vIDEO = "Videos";
    public static final String TIENGVIET = "Tiếng Việt";
    public static String TITLE = "title";

    public static String validSQL(String str) {
        return str.trim().toLowerCase().replace("'", "''");
    }

    public static void setTransition(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_right, R.animator.slide_out_left);
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void log(String str) {
        Log.d("ufo", str);
    }

    public static boolean getPremiumState(Context context) {
        return context.getSharedPreferences(MY_PREFS_NAME, 0).getBoolean(PREF_PREMIUM_KEY, false);
    }
}
