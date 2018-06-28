package com.sqlitedb_copyexternaldb.learnchines;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;


import com.sqlitedb_copyexternaldb.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Database {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static String CATEGORY_ID = "category_id";
    public static final String ENGLISH_COLUMN = "english";
    public static final String KEY_DB_VER = "db_version";
    private static Database mainDatabase;
    public String PACKAGE = "com.ufo.learnchinese2";
    String categoryColumnName;
    String dbName;
    public int dbVersion;
    String filePath;
    public String folderPath;
    private Context mContext = null;
    private SQLiteDatabase mDatabase = null;
    private SQLiteOpenHelper mOpenHelper = null;
    String phraseColumnName;

    public static Database newInstance(Context context, String str) {
        if (mainDatabase == null) {
            mainDatabase = new Database(context, str);
        }
        return mainDatabase;
    }

    private Database(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data/data/");
        stringBuilder.append(this.PACKAGE);
        stringBuilder.append("/databases");
        this.folderPath = stringBuilder.toString();
        this.filePath = "";
        this.dbName = "";
        this.mContext = context;
        this.dbName = str;
        this.PACKAGE = this.mContext.getPackageName();
      //  StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("data/data/");
        stringBuilder.append(this.PACKAGE);
        stringBuilder.append("/databases");
        this.folderPath = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.folderPath);
        stringBuilder.append("/");
        stringBuilder.append(this.dbName);
        this.filePath = stringBuilder.toString();
        this.categoryColumnName = this.mContext.getResources().getString(R.string.category_name_column);
        this.phraseColumnName = this.mContext.getResources().getString(R.string.phrase_name_column);
        if (this.dbName == Utils.PHRASE_DATABASE_NAME) {
            this.dbVersion = 12;
        } else if (this.dbName == Utils.GRAMMAR_DATABASE_NAME) {
            this.dbVersion = 1;
        }
        copyDBFromAsset(this.mContext, this.dbName);
        this.mOpenHelper = new SQLiteOpenHelper(this.mContext, this.dbName, null, this.dbVersion) {
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }
        };
        open();
    }

    private void copyDBFromAsset(Context context, String str) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!(!isCheckDB(str) || defaultSharedPreferences.getInt(KEY_DB_VER, 1) == this.dbVersion || new File(this.filePath).delete())) {
            System.out.println("eo delete dc db");
        }
        if (!isCheckDB(str)) {
            try {
                File file = new File(this.folderPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                AssetManager assetManager = context.getAssets();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.filePath));
                InputStream fileinPutStream = assetManager.open(str);
         BufferedInputStream       bufferedInputStream = new BufferedInputStream(fileinPutStream);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        fileOutputStream.close();
                        bufferedInputStream.close();
                        SharedPreferences.Editor sharedPreferences =  defaultSharedPreferences.edit();
                        sharedPreferences.putInt(KEY_DB_VER, this.dbVersion);
                        sharedPreferences.apply();
                        return;
                    }
                }
            } catch (Exception context2) {
                context2.printStackTrace();
            }
        }
    }

    private boolean isCheckDB(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data/data/");
        stringBuilder.append(this.PACKAGE);
        stringBuilder.append("/databases/");
        stringBuilder.append(str);
        return new File(stringBuilder.toString()).exists();
    }

    public void open() {
        this.mDatabase = this.mOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (this.mDatabase != null && this.mDatabase.isOpen()) {
            this.mDatabase.close();
        }
        this.mOpenHelper.close();
    }

    public Cursor getAll(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select * from ");
        stringBuilder.append(str);
        return this.mDatabase.rawQuery(stringBuilder.toString(), null);
    }

    public Cursor getListGrammar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select _id, title from ");
        stringBuilder.append(Utils.GRAMMAR_TABLE);
        return this.mDatabase.rawQuery(stringBuilder.toString(), null);
    }

    public String getGrammarContent(int i) {
        String string = this.mContext.getResources().getString(R.string.content_not_found);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select content from ");
        stringBuilder.append(Utils.GRAMMAR_TABLE);
        stringBuilder.append(" where _id = ");
        stringBuilder.append(i);
     Cursor   icur = this.mDatabase.rawQuery(stringBuilder.toString(), null);
        if (icur.moveToFirst()) {
            string = new DecodeUtil(DecodeUtil.keydecode_grammar).decode(
                    icur.getString(icur.getColumnIndex(Utils.GRAMMAR_COLUMN_CONTENT)));
        }
        icur.close();
        return string;
    }

    public ArrayList<PhraseItem> getListPhraseFavorite2() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(Utils.PHRASE_TABLE);
        stringBuilder.append(" where favorite = 1");
        Cursor rawQuery = this.mDatabase.rawQuery(stringBuilder.toString(), null);
        if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
            rawQuery.close();
            return arrayList;
        }
        do {
            PhraseItem phraseItem = new PhraseItem();
            phraseItem.id = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
            DecodeUtil decodeUtil = new DecodeUtil(DecodeUtil.keycode_phrase);
            phraseItem.txtPinyin = decodeUtil.decode(rawQuery.getString(rawQuery.getColumnIndex("pinyin")));
            phraseItem.txtKorean = decodeUtil.decode(rawQuery.getString(rawQuery.getColumnIndex(Utils.LANGUAGE_COLLUNM)));
            phraseItem.favorite = rawQuery.getInt(rawQuery.getColumnIndex("favorite"));
            phraseItem.voice = rawQuery.getString(rawQuery.getColumnIndex("voice"));
            phraseItem.txtVietnamese = rawQuery.getString(rawQuery.getColumnIndex(this.phraseColumnName));
            arrayList.add(phraseItem);
        } while (rawQuery.moveToNext());
        rawQuery.close();
        return arrayList;
    }

    public ArrayList<PhraseItem> searchPhrase2(String str) {
        str = Utils.validSQL(str);
        ArrayList<PhraseItem> arrayList = new ArrayList();
        StringBuilder stringBuilder;
        if (Utils.DEVICE_LANGUAGE.equalsIgnoreCase(Utils.TIENGVIET)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ");
            stringBuilder.append(Utils.PHRASE_TABLE);
            stringBuilder.append(" WHERE ");
            stringBuilder.append(this.phraseColumnName);
            stringBuilder.append(" LIKE '%");
            stringBuilder.append(str);
            stringBuilder.append("%' OR ");
            stringBuilder.append(Utils.LANGUAGE_COLLUNM);
            stringBuilder.append(" LIKE '%");
            stringBuilder.append(str);
            stringBuilder.append("%' OR search LIKE '%");
            stringBuilder.append(str);
            stringBuilder.append("%' limit 30");
            str = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ");
            stringBuilder.append(Utils.PHRASE_TABLE);
            stringBuilder.append(" WHERE ");
            stringBuilder.append(this.phraseColumnName);
            stringBuilder.append(" LIKE '%");
            stringBuilder.append(str);
            stringBuilder.append("%' OR ");
            stringBuilder.append(Utils.LANGUAGE_COLLUNM);
            stringBuilder.append(" LIKE '%");
            stringBuilder.append(str);
            stringBuilder.append("%' limit 30");
            str = stringBuilder.toString();
        }
        Cursor cursor = this.mDatabase.rawQuery(str, null);
        if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
            cursor.close();
            return arrayList;
        }
        do {
            PhraseItem phraseItem = new PhraseItem();
            phraseItem.id = cursor.getInt(cursor.getColumnIndex("_id"));
            DecodeUtil decodeUtil = new DecodeUtil(DecodeUtil.keycode_phrase);
            phraseItem.txtPinyin = decodeUtil.decode(cursor.getString(cursor.getColumnIndex("pinyin")));
            phraseItem.txtKorean = decodeUtil.decode(cursor.getString(cursor.getColumnIndex(Utils.LANGUAGE_COLLUNM)));
            phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
            phraseItem.voice = cursor.getString(cursor.getColumnIndex("voice"));
            phraseItem.txtVietnamese = cursor.getString(cursor.getColumnIndex(this.phraseColumnName));
            arrayList.add(phraseItem);
        } while (cursor.moveToNext());
        cursor.close();
        return arrayList;
    }

    public void updateFavorite(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Integer.valueOf(i2));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("_id = ");
        stringBuilder.append(i);
        this.mDatabase.update(Utils.PHRASE_TABLE, contentValues, stringBuilder.toString(), null);
    }

    public ArrayList<PhraseItem> getListPhraseItem(int i) {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(Utils.PHRASE_TABLE);
        stringBuilder.append(" where category_id = ");
        stringBuilder.append(i);
        Cursor cursor = this.mDatabase.rawQuery(stringBuilder.toString(), null);
        if (i == 0 || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
            cursor.close();
            return arrayList;
        }
        do {
            PhraseItem phraseItem = new PhraseItem();
            phraseItem.id = cursor.getInt(cursor.getColumnIndex("_id"));
            DecodeUtil decodeUtil = new DecodeUtil(DecodeUtil.keycode_phrase);
            phraseItem.txtPinyin = decodeUtil.decode(cursor.getString(cursor.getColumnIndex("pinyin")));
            phraseItem.txtKorean = decodeUtil.decode(cursor.getString(cursor.getColumnIndex(Utils.LANGUAGE_COLLUNM)));
            phraseItem.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
            phraseItem.voice = cursor.getString(cursor.getColumnIndex("voice"));
            phraseItem.txtVietnamese = cursor.getString(cursor.getColumnIndex(this.phraseColumnName));
            arrayList.add(phraseItem);
        } while (cursor.moveToNext());
        cursor.close();
        return arrayList;
    }

    public ArrayList<CategoryItem> getListCategoryItems() {
        ArrayList<CategoryItem> arrayList = new ArrayList();
        Cursor rawQuery = this.mDatabase.rawQuery("select * from category where _id < 100", null);
        if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
            rawQuery.close();
            return arrayList;
        }
        do {
            arrayList.add(new CategoryItem(rawQuery.getString(rawQuery.getColumnIndex(this.categoryColumnName)), rawQuery.getInt(rawQuery.getColumnIndex("_id")), rawQuery.getString(rawQuery.getColumnIndex("thumbnail")), rawQuery.getInt(rawQuery.getColumnIndex("weight"))));
        } while (rawQuery.moveToNext());
        rawQuery.close();
        return arrayList;
    }

    public int[] getAllCatId() {
        int[] iArr = new int[1000];
        Cursor rawQuery = this.mDatabase.rawQuery("select distinct category_id from phrase", null);
        rawQuery.moveToFirst();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            iArr[i] = rawQuery.getInt(rawQuery.getColumnIndex("category_id"));
            if (rawQuery.moveToNext()) {
                i = i2;
            } else {
                rawQuery.close();
                return iArr;
            }
        }
    }

    public ArrayList<PhraseItem> getAllPhraseItem() {
        ArrayList<PhraseItem> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(Utils.PHRASE_TABLE);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(CATEGORY_ID);
        stringBuilder.append(" > 0 and ");
        stringBuilder.append(CATEGORY_ID);
        stringBuilder.append(" < 19 and ");
        stringBuilder.append(ENGLISH_COLUMN);
        stringBuilder.append(" is not null");
        Cursor rawQuery = this.mDatabase.rawQuery(stringBuilder.toString(), null);
        if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
            rawQuery.close();
            return arrayList;
        }
        do {
            PhraseItem phraseItem = new PhraseItem();
            phraseItem.id = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
            DecodeUtil decodeUtil = new DecodeUtil(DecodeUtil.keycode_phrase);
            phraseItem.txtPinyin = decodeUtil.decode(rawQuery.getString(rawQuery.getColumnIndex("pinyin")));
            phraseItem.txtKorean = decodeUtil.decode(rawQuery.getString(rawQuery.getColumnIndex(Utils.LANGUAGE_COLLUNM)));
            phraseItem.favorite = rawQuery.getInt(rawQuery.getColumnIndex("favorite"));
            phraseItem.voice = rawQuery.getString(rawQuery.getColumnIndex("voice"));
            phraseItem.txtVietnamese = rawQuery.getString(rawQuery.getColumnIndex(this.phraseColumnName));
            arrayList.add(phraseItem);
        } while (rawQuery.moveToNext());
        rawQuery.close();
        return arrayList;
    }
}
