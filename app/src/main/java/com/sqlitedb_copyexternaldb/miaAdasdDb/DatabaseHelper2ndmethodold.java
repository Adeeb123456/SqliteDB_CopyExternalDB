package com.sqlitedb_copyexternaldb.miaAdasdDb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHelper2ndmethodold extends SQLiteOpenHelper {
    private static String DB_NAME = "database.db";
    private static String DB_PATH = "/data/data/com.mianasad.myislam/databases/";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DatabaseHelper2ndmethodold(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            File file = new File(myPath);
            if (file.exists() && !file.isDirectory()) {
                checkDB = SQLiteDatabase.openDatabase(myPath, null, 1);
            }
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        if (checkDB != null) {
            return true;
        }
        return false;
    }

    private void copyDataBase() throws IOException {
        try {
            InputStream myInput = this.myContext.getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            while (true) {
                int length = myInput.read(buffer);
                if (length > 0) {
                    myOutput.write(buffer, 0, length);
                } else {
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.myContext, "Something went wrong!", 0).show();
        }
    }

    public void openDataBase() throws SQLException {
        myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 1);
    }

    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<String> getCategories(String Language) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> s = new ArrayList();
        Cursor cursor = db.rawQuery("SELECT " + Language + "_categoryName From category", null);
        while (cursor.moveToNext()) {
            try {
                if (cursor != null) {
                    s.add(cursor.getString(0));
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
              //  db.close();
            }
        }
        return s;
    }

    public ArrayList<SampleSearchModel> GetAllSearchTopics(String Language) {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db.isOpen()){

        }else {
           // openDataBase();
        }
        ArrayList<SampleSearchModel> s = new ArrayList();
        Cursor cursor = db.rawQuery("select " + Language + "_topicName from topic", null);
        while (cursor.moveToNext()) {
            try {
                if (cursor != null) {
                    s.add(new SampleSearchModel(cursor.getString(0)));
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
              //  db.close();
            }
        }
        return s;
    }

    public ArrayList<String> GetAllTopics(String Language) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> s = new ArrayList();
        Cursor cursor = db.rawQuery("select " + Language + "_topicName from topicCategory join topic on topicCategory.topicID = topic.topicID join category on topicCategory.categoryID = category.categoryID ", null);
        while (cursor.moveToNext()) {
            try {
                if (cursor != null) {
                    s.add(cursor.getString(0));
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        return s;
    }

    public ArrayList<String> GetTopicsByCategory(String Category, String Language) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> s = new ArrayList();
        Cursor cursor = db.rawQuery("select " + Language + "_topicName from topicCategory join topic on topicCategory.topicID = topic.topicID join category on topicCategory.categoryID = category.categoryID where category." + Language + "_categoryName = '" + Category + "'", null);
        while (cursor.moveToNext()) {
            try {
                if (cursor != null) {
                    s.add(cursor.getString(0));
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        return s;
    }

    public ArrayList<Dua> GetDuasByTopic(String topic, String Language) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Dua> d = new ArrayList();
        Cursor cursor = db.rawQuery("select dua.duaID," + Language + "_topicName,dua,transliteration,en_meaning,en_reference,ms_meaning,ur_meaning from duaTopic join dua on duaTopic.duaID = dua.duaID join topic on duaTopic.topicID = topic.topicID where topic." + Language + "_topicName = '" + topic + "'", null);
        while (cursor.moveToNext()) {
            try {
                if (cursor != null) {
                    Dua dua = new Dua();
                    dua.id = cursor.getInt(0);
                    dua.title = cursor.getString(1);
                    dua.arabic = cursor.getString(2);
                    dua.transliteration = cursor.getString(3);
                    dua.english = cursor.getString(4);
                    dua.ref = cursor.getString(5);
                    dua.bahasa = cursor.getString(6);
                    dua.urdu = cursor.getString(7);
                    d.add(dua);
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        return d;
    }

    public int GetCategoryIdByName(String category) {
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select categoryId from category where en_CategoryName =  '" + category + "'", null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                    return i;
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        cursor.close();
        db.close();
        return i;
    }

    public int GetTopicIdByName(String topic) {
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select topicID from topic where en_topicName =  '" + topic + "'", null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                    return i;
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        cursor.close();
        db.close();
        return i;
    }

    public Dua getAyatbyMood(String mood) {
        SQLiteDatabase db = getReadableDatabase();
        Dua dua = new Dua();
        Cursor cursor = db.rawQuery("SELECT title,arabic,english,urdu,transliteration,ref From duamood where mood = '" + mood + "'", null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    dua.title = cursor.getString(0);
                    dua.arabic = cursor.getString(1);
                    dua.english = cursor.getString(2);
                    dua.urdu = cursor.getString(3);
                    dua.transliteration = cursor.getString(4);
                    dua.ref = cursor.getString(5);
                    return dua;
                }
            } catch (Exception e) {
                Toast.makeText(this.myContext, "Something went wrong!", 0).show();
            } finally {
                cursor.close();
                db.close();
            }
        }
        cursor.close();
        db.close();
        return null;
    }
}
