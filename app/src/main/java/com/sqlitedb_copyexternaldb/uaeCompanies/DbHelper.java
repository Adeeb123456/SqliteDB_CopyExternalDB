package com.sqlitedb_copyexternaldb.uaeCompanies;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ae.lateston.uaecompanies.model.AboutUs;
import ae.lateston.uaecompanies.model.CategoryItem;
import ae.lateston.uaecompanies.model.CommonModel;
import ae.lateston.uaecompanies.model.Faq;
import ae.lateston.uaecompanies.utils.AppConst;
import ae.lateston.uaecompanies.utils.AppPref;

public class DbHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "uae_companies_database";


    private int lang = 0;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLiteDatabase", "onCreate");

        // creating required tables

        db.execSQL(AppConst.CREATE_TABLE_COMPANY);
        db.execSQL(AppConst.CREATE_TABLE_VIDEOS);
        db.execSQL(AppConst.CREATE_TABLE_CITY);
        db.execSQL(AppConst.CREATE_TABLE_DISTRICT);
        db.execSQL(AppConst.CREATE_TABLE_FAQ);
        db.execSQL(AppConst.CREATE_TABLE_CATEGORY);
        db.execSQL(AppConst.CREATE_TABLE_CATEGORY_HEALTH);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_ABOUT_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_VIDEOS);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_DISTRICT);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_FAQ);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + AppConst.TABLE_CATEGORY_HEALTH);




        // create new tables
        onCreate(db);
    }


    public void insertCategory(List<CategoryItem> categories) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (categories == null || categories.size() == 0)
            return;
        int size = categories.size();
        try {
            db.delete(AppConst.TABLE_CATEGORY, null, null);
            for (int i = 0; i < size; i++) {
                if(categories.get(i).getCategoryId()>0) {
                    ContentValues values = new ContentValues();
                    values.put(AppConst.idParam, categories.get(i).getCategoryId());
                    values.put(AppConst.categoryNameEn, categories.get(i).getCategoryNameEn() != null ? categories.get(i)
                            .getCategoryNameEn().trim() : "");
                    values.put(AppConst.categoryColor, categories.get(i).getColor());
                    values.put(AppConst.descriptionEn, categories.get(i).getDescriptionEn());
                    values.put(AppConst.bannerImage, categories.get(i).getBannerImage());
                    db.insert(AppConst.TABLE_CATEGORY, null, values);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void insertCategoryHealth(List<CategoryItem> categories) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (categories == null || categories.size() == 0)
            return;
        int size = categories.size();
        try {
            db.delete(AppConst.TABLE_CATEGORY_HEALTH, null, null);
            for (int i = 0; i < size; i++) {
             //   if(categories.get(i).getCategoryId()>0) {
                    ContentValues values = new ContentValues();
                    values.put(AppConst.idParam, categories.get(i).getCategoryId());
                    values.put(AppConst.categoryNameEn, categories.get(i).getCategoryNameEn() != null ? categories.get(i)
                            .getCategoryNameEn().trim() : "");
                    values.put(AppConst.categoryColor, categories.get(i).getColor());
                    values.put(AppConst.descriptionEn, categories.get(i).getDescriptionEn());
                    values.put(AppConst.bannerImage, categories.get(i).getBannerImage());
                    db.insert(AppConst.TABLE_CATEGORY_HEALTH, null, values);
              //  }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





    public void insertRegion(List<CommonModel> categories, boolean isCities) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (categories == null || categories.size() == 0)
            return;
        int size = categories.size();
        try {
            if (isCities) {
                db.delete(AppConst.TABLE_CITY, null, null);
            } else {
                db.delete(AppConst.TABLE_DISTRICT, null, null);
            }

            for (int i = 0; i < size; i++) {
                ContentValues values = new ContentValues();
                values.put(AppConst.cityId, categories.get(i).getCityId());
                if (isCities) {
                    values.put(AppConst.countryId, categories.get(i).getCountryId());
                    values.put(AppConst.cityNameEn, categories.get(i).getCityName() != null ? categories.get(i)
                            .getCityName().trim() : "");
                    db.insert(AppConst.TABLE_CITY, null, values);
                } else {
                    values.put(AppConst.districtId, categories.get(i).getDistrictId());
                    values.put(AppConst.districtNameEn, categories.get(i).getDistrictName() != null ? categories.get(i)
                            .getDistrictName().trim() : "");
                    db.insert(AppConst.TABLE_DISTRICT, null, values);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public List<CommonModel> getRegion(boolean isCity, int cityId, int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<CommonModel> cityList = null;
        String selectQuery = "Select * From " + (isCity ? AppConst.TABLE_CITY : AppConst.TABLE_DISTRICT + " WHERE " + AppConst.cityId + "=" + cityId);

        try {
            Log.d("qry", selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);
            cityList = new ArrayList<>();
            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    CommonModel map = new CommonModel();
                    map.setCityId(cursor.getInt(cursor
                            .getColumnIndex(AppConst.cityId)));
                    if (isCity) {
                        map.setCountryId(cursor.getInt(cursor
                                .getColumnIndex(AppConst.countryId)));
                        map.setCityName(cursor.getString(cursor
                                .getColumnIndex(AppConst.cityNameEn)));
                        if (map.getCityId() == id) {
                            map.setChecked(true);
                        }
                    } else {
                        map.setDistrictId(cursor.getInt(cursor
                                .getColumnIndex(AppConst.districtId)));
                        map.setDistrictName(cursor.getString(cursor
                                .getColumnIndex(AppConst.districtNameEn)));
                        if (map.getDistrictId() == id) {
                            map.setChecked(true);
                        }
                    }
                    cityList.add(map);
                }
                cursor.close();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cityList;

    }


    public List<CategoryItem> getCategories(int parentId) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<CategoryItem> cityList = null;
        String selectQuery = "Select * From " + AppConst.TABLE_CATEGORY;
        if (lang == 0) {
            selectQuery = selectQuery + " WHERE " + AppConst.categoryNameEn
                    + " IS NOT NULL AND TRIM(" + AppConst.categoryNameEn
                    + ") <> ''";
        } else if (lang == 1) {
            selectQuery = selectQuery + " WHERE " + AppConst.categoryNameAr
                    + " IS NOT NULL AND TRIM(" + AppConst.categoryNameAr
                    + ") <> ''";
        }
        try {
            Log.d("qry", selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);
            cityList = new ArrayList<>();
            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    CategoryItem map = new CategoryItem();
                    map.setCategoryId(cursor.getInt(cursor
                            .getColumnIndex(AppConst.idParam)));
                    map.setCategoryNameEn(cursor.getString(cursor
                            .getColumnIndex(AppConst.categoryNameEn)));
                    if (parentId == map.getCategoryId()) {
                        map.setChecked(true);
                    }
                    cityList.add(map);
                }
                cursor.close();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cityList;

    }

    public List<CategoryItem> getCategoriesHealth(ArrayList<Integer> categoryIds, boolean isAllSelected) {

        SQLiteDatabase db = this.getWritableDatabase();
        List<CategoryItem> cityList = null;
        String selectQuery = "Select * From " + AppConst.TABLE_CATEGORY_HEALTH;
        if (lang == 0) {
            selectQuery = selectQuery + " WHERE " + AppConst.categoryNameEn
                    + " IS NOT NULL AND TRIM(" + AppConst.categoryNameEn
                    + ") <> ''";
        } else if (lang == 1) {
            selectQuery = selectQuery + " WHERE " + AppConst.categoryNameAr
                    + " IS NOT NULL AND TRIM(" + AppConst.categoryNameAr
                    + ") <> ''";
        }
        try {
            Log.d("qry", selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);
            cityList = new ArrayList<>();
            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    CategoryItem map = new CategoryItem();
                    map.setCategoryId(cursor.getInt(cursor
                            .getColumnIndex(AppConst.idParam)));
                    map.setCategoryNameEn(cursor.getString(cursor
                            .getColumnIndex(AppConst.categoryNameEn)));


                    for(int j=0;j<categoryIds.size();j++){
                        if (categoryIds.get(j) == map.getCategoryId()) {
                            map.setChecked(true);
                        }
                    }



                    cityList.add(map);
                }
                cursor.close();
            }
            Log.i("debug1","isAllSelected "+isAllSelected);
            if(cityList.size()==categoryIds.size()+1){
cityList.get(0).setChecked(true);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cityList;

    }


    public void insertAboutUs(AboutUs ab) {

        SQLiteDatabase db = this.getWritableDatabase();

        if (ab == null)
            return;

        try {
            db.delete(AppConst.TABLE_ABOUT_COMPANY, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ContentValues values = new ContentValues();
            values.put(AppConst.idParam, ab.getId());
            values.put(AppConst.image, ab.getImage() != null ? ab.getImage() : "");
            values.put(AppConst.phone, ab.getPhone() != null ? ab.getPhone() : "");
            values.put(AppConst.email, ab.getEmail() != null ? ab.getEmail() : "");
            values.put(AppConst.fb, ab.getFb() != null ? ab.getFb() : "");
            values.put(AppConst.tw, ab.getTw() != null ? ab.getTw() : "");
            values.put(AppConst.gp, ab.getGp() != null ? ab.getGp() : "");
            values.put(AppConst.ig, ab.getIg() != null ? ab.getIg() : "");
            values.put(AppConst.gpsDistance, ab.getGpsDistance());
            values.put(AppConst.aboutUsEn, ab.getAboutUsEn() != null ? ab.getAboutUsEn().trim() : "");
            values.put(AppConst.termsConditionsEn, ab.getTermsConditionsEn() != null ? ab.getTermsConditionsEn().trim() : "");
            values.put(AppConst.policyProceduresEn, ab.getPolicyProceduresEn() != null ? ab.getPolicyProceduresEn().trim() : "");
            values.put(AppConst.addressEn, ab.getAddressEn() != null ? ab.getAddressEn().trim() : "");
            db.insert(AppConst.TABLE_ABOUT_COMPANY, null, values);
            AppPref.putValueByKey(AppConst.gpsDistance, ab.getGpsDistance());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public AboutUs getAboutCompany() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + AppConst.TABLE_ABOUT_COMPANY;
        if (lang == 1) {
            selectQuery = selectQuery + " WHERE " + AppConst.aboutUsAr
                    + " IS NOT NULL AND TRIM(" + AppConst.aboutUsAr
                    + ") <> ''" + " ORDER BY " + AppConst.idParam + " DESC LIMIT 1";
        } else {
            selectQuery = selectQuery + " ORDER BY " + AppConst.idParam
                    + " DESC LIMIT 1";
        }
        AboutUs ab = null;
        try {
            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                ab = new AboutUs();
                ab.setId(c.getInt(c.getColumnIndex(AppConst.idParam)));
                ab.setImage((c.getString(c
                        .getColumnIndex(AppConst.image))));
                ab.setEmail((c.getString(c
                        .getColumnIndex(AppConst.email))));
                ab.setPhone((c.getString(c
                        .getColumnIndex(AppConst.phone))));
                ab.setFb((c.getString(c
                        .getColumnIndex(AppConst.fb))));
                ab.setTw((c.getString(c
                        .getColumnIndex(AppConst.tw))));
                ab.setIg((c.getString(c
                        .getColumnIndex(AppConst.ig))));
                ab.setGp((c.getString(c
                        .getColumnIndex(AppConst.gp))));

                if (lang == 0) {
                    ab.setAboutUsEn((c.getString(c
                            .getColumnIndex(AppConst.aboutUsEn))));
                    ab.setAddressEn((c.getString(c
                            .getColumnIndex(AppConst.addressEn))));
                    ab.setTermsConditionsEn((c.getString(c
                            .getColumnIndex(AppConst.termsConditionsEn))));
                    ab.setPolicyProceduresEn((c.getString(c
                            .getColumnIndex(AppConst.policyProceduresEn))));
                } else {
                    ab.setAboutUsEn((c.getString(c
                            .getColumnIndex(AppConst.aboutUsAr))));
                    ab.setAddressEn((c.getString(c
                            .getColumnIndex(AppConst.addressAr))));
                    ab.setTermsConditionsEn((c.getString(c
                            .getColumnIndex(AppConst.termsConditionsAr))));
                    ab.setPolicyProceduresEn((c.getString(c
                            .getColumnIndex(AppConst.policyProceduresAr))));
                }
                c.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ab;
    }


//    public void insertVideoList(List<VideoItem> videoItems) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try {
//            if (videoItems == null || videoItems.size() == 0)
//                return;
//
//            int size = videoItems.size();
//            db.delete(AppConst.TABLE_VIDEOS, null, null);
//
//            for (int i = 0; i < size; i++) {
//                ContentValues values = new ContentValues();
//                values.put(AppConst.idParam, videoItems.get(i).getId());
//                values.put(AppConst.video, videoItems.get(i)
//                        .getVideoUrl());
//                values.put(AppConst.image, videoItems.get(i)
//                        .getThumbnailUrl());
//                values.put(AppConst.titleEn, videoItems.get(i).getTitleEn() != null ? videoItems.get(i)
//                        .getTitleEn().trim() : "");
//                db.insert(AppConst.TABLE_VIDEOS, null,
//                        values);
//
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public List<VideoItem> getVideoList() {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        List<VideoItem> cityList = null;
//        String selectQuery = "Select * From " + AppConst.TABLE_VIDEOS;
//        if (lang == 0) {
//            selectQuery = selectQuery + " WHERE " + AppConst.titleEn
//                    + " IS NOT NULL AND TRIM(" + AppConst.titleEn
//                    + ") <> ''";
//        } else if (lang == 1) {
//            selectQuery = selectQuery + " WHERE " + AppConst.titleAr
//                    + " IS NOT NULL AND TRIM(" + AppConst.titleAr
//                    + ") <> ''";
//        }
//        try {
//            Log.d("qry", selectQuery);
//            Cursor cursor = db.rawQuery(selectQuery, null);
//            if (cursor != null && cursor.getCount() != 0) {
//                cityList = new ArrayList<VideoItem>(cursor.getCount());
//                while (cursor.moveToNext()) {
//                    VideoItem map = new VideoItem();
//                    map.setId(cursor.getInt(cursor
//                            .getColumnIndex(AppConst.idParam)));
//                    map.setThumbnailUrl(cursor.getString(cursor
//                            .getColumnIndex(AppConst.image)));
//                    map.setVideoUrl(cursor.getString(cursor
//                            .getColumnIndex(AppConst.video)));
//                    if (lang == 0) {
//                        map.setTitleEn(cursor.getString(cursor
//                                .getColumnIndex(AppConst.titleEn)));
//                    } else {
//                        map.setTitleEn(cursor.getString(cursor
//                                .getColumnIndex(AppConst.titleAr)));
//                    }
//                    cityList.add(map);
//
//                }
//                cursor.close();
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return cityList;
//
//    }

    public void insertFaqs(List<Faq> faqList) {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            if (faqList == null || faqList.size() == 0)
                return;

            int size = faqList.size();
            db.delete(AppConst.TABLE_FAQ, null, null);

            for (int i = 0; i < size; i++) {
                ContentValues values = new ContentValues();
                values.put(AppConst.idParam, faqList.get(i).getId());
                if (lang == 0) {
                    values.put(AppConst.questionEn, faqList.get(i).getQuestEn() != null ? faqList.get(i)
                            .getQuestEn().trim() : "");
                    values.put(AppConst.answerEn, faqList.get(i).getAnsEn() != null ? faqList.get(i)
                            .getAnsEn().trim() : "");
                }
                db.insert(AppConst.TABLE_FAQ, null,
                        values);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Faq> getFaqs() {

        SQLiteDatabase db = this.getWritableDatabase();
        List<Faq> cityList = null;
        String selectQuery = "Select * From " + AppConst.TABLE_FAQ;
        if (lang == 0) {
            selectQuery = selectQuery + " WHERE " + AppConst.questionEn
                    + " IS NOT NULL AND TRIM(" + AppConst.questionEn
                    + ") <> ''";
        } else if (lang == 1) {
            selectQuery = selectQuery + " WHERE " + AppConst.questionAr
                    + " IS NOT NULL AND TRIM(" + AppConst.questionAr
                    + ") <> ''";
        }
        try {
            Log.d("qry", selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null && cursor.getCount() != 0) {
                cityList = new ArrayList<Faq>(cursor.getCount());
                while (cursor.moveToNext()) {
                    Faq map = new Faq();
                    map.setId(cursor.getInt(cursor
                            .getColumnIndex(AppConst.idParam)));
                    if (lang == 0) {
                        map.setQuestEn(cursor.getString(cursor
                                .getColumnIndex(AppConst.questionEn)));
                        map.setAnsEn(cursor.getString(cursor
                                .getColumnIndex(AppConst.answerEn)));
                    }
                    cityList.add(map);

                }
                cursor.close();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cityList;

    }

    public String getAboutColumn(String column) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + column + " FROM " + AppConst.TABLE_ABOUT_COMPANY;
        if (lang == 1) {
            selectQuery = selectQuery + " WHERE " + AppConst.aboutUsAr
                    + " IS NOT NULL AND TRIM(" + AppConst.aboutUsAr
                    + ") <> ''" + " ORDER BY " + AppConst.idParam + " DESC LIMIT 1";
        } else {
            selectQuery = selectQuery + " ORDER BY " + AppConst.idParam
                    + " DESC LIMIT 1";
        }
        String data = null;
        try {

            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                data = c.getString(c
                        .getColumnIndex(column));
                c.close();
            }
        } catch (Exception e) {

        }
        return data;
    }

    public void truncateTable() {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(AppConst.TABLE_ABOUT_COMPANY, null, null);
            db.delete(AppConst.TABLE_DISTRICT, null, null);
            db.delete(AppConst.TABLE_CITY, null, null);
            db.delete(AppConst.TABLE_FAQ, null, null);
            db.delete(AppConst.TABLE_VIDEOS, null, null);
            db.delete(AppConst.TABLE_CATEGORY, null, null);
            db.delete(AppConst.TABLE_CATEGORY_HEALTH, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
