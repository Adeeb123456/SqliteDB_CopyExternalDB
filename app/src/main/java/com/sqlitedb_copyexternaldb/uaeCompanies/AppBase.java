package com.sqlitedb_copyexternaldb.uaeCompanies;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import ae.lateston.uaecompanies.utils.AppPref;
import ae.lateston.uaecompanies.ws.ServiceGenerator;
import ae.lateston.uaecompanies.ws.WebService;
import io.fabric.sdk.android.Fabric;

/**
 * Created by USER3 on 1/17/2017.
 */

public class AppBase extends Application {

    Context context;
    public static HashMap<String, Typeface> fontCache = new HashMap<>();
    public static WebService service;
    public static DbHelper db;
    public static File cacheDir;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        AppPref.init(this);
        FacebookSdk.sdkInitialize(this);
        context = this;
        service = ServiceGenerator.createService(WebService.class);
        db = new DbHelper(getBaseContext());
        addFont(getResources().getString(R.string.font_name_helvertica), getResources().getString(R.string.font_helvertica));
        addFont(getResources().getString(R.string.font_name_myriadpro_bold), getResources().getString(R.string.font_myriadpro_bold));
        addFont(getResources().getString(R.string.font_name_myriadpro_regular), getResources().getString(R.string.font_myriadpro_regular));
        setLanguage();

        try {

            if (android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED)) {
                cacheDir = new File(android.os.Environment
                        .getExternalStorageDirectory().toString()
                        + "/Android/data/ae.lateston.uaecompanies/", "Temp");
            } else {
                cacheDir = getCacheDir();
            }
            if (!cacheDir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                cacheDir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void addFont(String alias, String fontName) {
        if (!fontCache.containsKey(alias)) {
            try {
                Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/" + fontName);
                fontCache.put(alias, typeface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static DbHelper getDb() {
        return db;
    }

    public void setLanguage() {

        if (AppPref.getLanguage().equals(AppConst.langEn)) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getApplicationContext().getResources().updateConfiguration(config, null);

        }

        else {

            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getApplicationContext().getResources().updateConfiguration(config, null);

        }
    }


}
