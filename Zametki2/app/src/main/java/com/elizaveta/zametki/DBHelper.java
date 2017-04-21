package com.elizaveta.zametki;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final  String DATABASE_NAME = "zametkiDB";
    public static final  String TABLE_NAME = "zametki";

    public static final  String KEY_ID = "_id";
    public static final  String KEY_TEXT = "text_zametki";


    public DBHelper(Context context) {
//        метод работы с БД
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Запрос на создание таблицы ч полями и их атрибутами
        db.execSQL("create table " + TABLE_NAME + " ( " + KEY_ID + " integer primary key, " + KEY_TEXT + " text " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Запрос на удаление таблицы, если таблица существует
        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);

    }

}
