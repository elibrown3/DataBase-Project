package com.example.favoriteshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ShoeDataSource  {

    private SQLiteDatabase database;
    private ShoeDBHelper dbHelper;

    public ShoeDataSource(Context context) { dbHelper = new ShoeDBHelper(context);}

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {dbHelper.close(); }

    public boolean insertShoe(Shoe s) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("shoeBrand", s.getShoeBrand());
            values.put("colorway", s.getColorway());
            values.put("releasedate", s.getReleaseDate());
            didSucceed = database.insert("shoe", null, values) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateShoe(Shoe s) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("shoeBrand", s.getShoeBrand());
            values.put("colorway", s.getColorway());
            values.put("releasedate", s.getReleaseDate());
            Long id = (long) s.getShoeID();
            didSucceed = database.update("shoe", values, "_id="+id, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastShoeID() {
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from shoe";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {

        }
        return lastID;
    }

    public ArrayList<Shoe> getShoes() {
        ArrayList<Shoe> shoes = new ArrayList<>();
        try {
            String query = "Select * from shoe";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Shoe s = new Shoe();
                s.setShoeBrand(cursor.getString(1));
                s.setColorway(cursor.getString(2));
                s.setReleaseDate(cursor.getString(3));
                shoes.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return shoes;
    }

    public Shoe getShoe(int id) {
        Shoe s = new Shoe();
        try {
            String query = "Select * from shoe where _id=" + id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            s.setShoeID(id);
            s.setShoeBrand(cursor.getString(1));
            s.setColorway(cursor.getString(2));
            s.setReleaseDate(cursor.getString(3));
            cursor.close();
        } catch (Exception e) {

        }
        return s;
    }
}