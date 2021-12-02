package com.example.favoriteshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class ShoeDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "myshoes.db";
    private static int DATABASE_VERSION = 1;

    public ShoeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table shoe (_id integer primary key autoincrement, "
                + "shoeBrand text not null, "
                + "releasedate text, "
                + "colorway text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS shoe");
        onCreate(sqLiteDatabase);
    }
}