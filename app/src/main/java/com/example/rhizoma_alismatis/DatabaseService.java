package com.example.rhizoma_alismatis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.rhizoma_alismatis.models.RecentMusic;

import java.util.ArrayList;
import java.util.List;

public class DatabaseService extends SQLiteOpenHelper {
    private volatile static DatabaseService instance;

    private static final String DB_NAME = "rhizoma_alismatis.db";
    private static final int DB_VERSION = 2;

    public DatabaseService(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        System.out.println("DatabaseService::DatabaseService");
    }

    public static DatabaseService getInstance(@Nullable Context context) {
        System.out.println("DatabaseService::getInstance");
        if (instance == null) {
            synchronized (DatabaseService.class) {
                if (instance == null) {
                    instance = new DatabaseService(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DatabaseService::onCreate");
        String create_user_table = "create table " + LocalUserInfo.USER_TABLE_NAME + "(" +
                LocalUserInfo.USER_ID + " text primary key, " +
                LocalUserInfo.USER_TOKEN + " text, " +
                LocalUserInfo.USER_ICON + " text)";
        db.execSQL(create_user_table);

        String create_recent_music_table = "create table " + RecentMusic.RECENT_MUSIC + "(" +
                RecentMusic.RECENT_MUSIC_ID + " text primary key, " +
                RecentMusic.RECENT_MUSIC_NAME + " text, " +
                RecentMusic.RECENT_MUSIC_COVER + " text, " +
                RecentMusic.RECENT_MUSIC_FROM + " text)";
        db.execSQL(create_recent_music_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("DatabaseService::onUpgrade");
        db.execSQL("drop table if exists " + LocalUserInfo.USER_TABLE_NAME);
        db.execSQL("drop table if exists " + RecentMusic.RECENT_MUSIC);
        onCreate(db);
    }

    public void InsertRecentMusic(RecentMusic recentMusic) {
        System.out.println("DatabaseService::InsertRecentMusic");
        SQLiteDatabase db = getWritableDatabase();
        String recentMusicId = recentMusic.MusicId;
        String recentMusicName = recentMusic.MusicName;
        String recentMusicCover = recentMusic.MusicCover;
        String recentMusicFrom = recentMusic.MusicFrom;

        ContentValues values = new ContentValues();
        values.put(RecentMusic.RECENT_MUSIC_ID, recentMusicId);
        values.put(RecentMusic.RECENT_MUSIC_NAME, recentMusicName);
        values.put(RecentMusic.RECENT_MUSIC_COVER, recentMusicCover);
        values.put(RecentMusic.RECENT_MUSIC_FROM, recentMusicFrom);

        db.insert(RecentMusic.RECENT_MUSIC, null, values);
        db.close();
    }

    public List<RecentMusic> GetRecentMusicList() {
        System.out.println("DatabaseService::GetRecentMusicList");
        SQLiteDatabase db = getReadableDatabase();
        List<RecentMusic> recentMusics = new ArrayList<>();

        String[] projection = {
                RecentMusic.RECENT_MUSIC_ID,
                RecentMusic.RECENT_MUSIC_NAME,
                RecentMusic.RECENT_MUSIC_COVER,
                RecentMusic.RECENT_MUSIC_FROM,
        };

        Cursor cursor = db.query(
                RecentMusic.RECENT_MUSIC,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String recentMusicId = cursor.getString(cursor.getColumnIndexOrThrow(RecentMusic.RECENT_MUSIC_ID));
            String recentMusicName = cursor.getString(cursor.getColumnIndexOrThrow(RecentMusic.RECENT_MUSIC_NAME));
            String recentMusicCover = cursor.getString(cursor.getColumnIndexOrThrow(RecentMusic.RECENT_MUSIC_COVER));
            String recentMusicFrom = cursor.getString(cursor.getColumnIndexOrThrow(RecentMusic.RECENT_MUSIC_FROM));
            recentMusics.add(new RecentMusic(recentMusicId, recentMusicName, recentMusicCover, recentMusicFrom));
        }

        cursor.close();
        return recentMusics;
    }

}

class LocalUserInfo {
    public static final String USER_TABLE_NAME = "user_info";
    public static final String USER_ID = "user_id";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_ICON = "user_icon";
}