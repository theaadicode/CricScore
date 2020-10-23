package com.aditya.cricketrecord;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aditya.cricketrecord.ScoreContract.ScoreEntry;

public class ScoreDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "scores.db";
    private static final String INTEGER =" INTEGER ";
    private static final String NOT_NULL = " NOT NULL ";
    private static final String TEXT = " TEXT ";

    private static final int  DATABASE_VERSION = 1;

    public ScoreDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY ="CREATE TABLE " + ScoreEntry.TABLE_NAME + "(" + ScoreEntry._ID + INTEGER + NOT_NULL + "PRIMARY KEY AUTOINCREMENT ,"+
                    ScoreEntry.COLUMN_TEAM_A + TEXT + NOT_NULL + "," +
                    ScoreEntry.COLUMN_TEAM_B + TEXT + NOT_NULL + "," +
                    ScoreEntry.COLUMN_RUNS_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_RUNS_B + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_BALLS_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_BALLS_B + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_FOUR_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_FOUR_B + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_SIX_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_SIX_B + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_WICKET_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_WICKET_B + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_EXTRAS_A + INTEGER + NOT_NULL + "," +
                    ScoreEntry.COLUMN_EXTRAS_B + INTEGER + NOT_NULL + ");";
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
