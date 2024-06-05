package com.example.rhizoma_alismatis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.rhizoma_alismatis.models.LocalUserTable;
import com.example.rhizoma_alismatis.models.RecentMusic;
import com.example.rhizoma_alismatis.models.UserInfo;
import org.jetbrains.annotations.NotNull;

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
        String create_user_table = "create table " + LocalUserTable.USER_TABLE_NAME + "(" +
                LocalUserTable.USER_ID + " text primary key, " +
                LocalUserTable.USER_NAME + " text, " +
                LocalUserTable.USER_EMAIL + " text, " +
                LocalUserTable.USER_TOKEN + " text, " +
                LocalUserTable.USER_ICON + " text)";
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
        db.execSQL("drop table if exists " + LocalUserTable.USER_TABLE_NAME);
        db.execSQL("drop table if exists " + RecentMusic.RECENT_MUSIC);
        onCreate(db);
    }

    public boolean InsertUserInfo(UserInfo userInfo) {
        System.out.println("DatabaseService::InsertUserInfo");

        SQLiteDatabase db = getWritableDatabase();
        String userId = userInfo.UserId;
        ContentValues values = getContentValues(userInfo, userId);

        if (db.insert(LocalUserTable.USER_TABLE_NAME, null, values) == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }


    private static @NotNull ContentValues getContentValues(UserInfo userInfo, String userId) {
        String userName = userInfo.UserName;
        String userEmail = userInfo.UserEmail;
        String userToken = userInfo.UserToken;
        String userIcon = userInfo.UserIcon;

        ContentValues values = new ContentValues();
        values.put(LocalUserTable.USER_ID, userId);
        values.put(LocalUserTable.USER_NAME, userName);
        values.put(LocalUserTable.USER_EMAIL, userEmail);
        values.put(LocalUserTable.USER_TOKEN, userToken);
        values.put(LocalUserTable.USER_ICON, userIcon);
        return values;
    }

    public boolean SearchUserExist(String userEmail) {
        System.out.println("DatabaseService::SearchUserExist");

        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {LocalUserTable.USER_EMAIL};

        Cursor cursor = db.query(LocalUserTable.USER_TABLE_NAME,
                projection,
                LocalUserTable.USER_EMAIL + " = ?",
                new String[]{userEmail},
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public UserInfo GetUserInfo(String userEmail) {
        System.out.println("DatabaseService::GetUserInfo");
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {LocalUserTable.USER_ID,
                LocalUserTable.USER_NAME,
                LocalUserTable.USER_EMAIL,
                LocalUserTable.USER_TOKEN,
                LocalUserTable.USER_ICON};
        Cursor cursor = db.query(LocalUserTable.USER_TABLE_NAME,
                projection,
                LocalUserTable.USER_EMAIL + " = ?",
                new String[]{userEmail},
                null,
                null,
                null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        } else {
            cursor.close();
            return null;
        }
        UserInfo result = new UserInfo();
        result.UserId = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_ID));
        result.UserName = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_NAME));
        result.UserEmail = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_EMAIL));
        result.UserToken = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_TOKEN));
        result.UserIcon = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_ICON));
        cursor.close();
        return result;
    }

    public List<UserInfo> GetAllUsers(){
        System.out.println("DatabaseService::GetAllUsers");
        SQLiteDatabase db = getReadableDatabase();
        List<UserInfo> users = new ArrayList<>();
        try {
            String[] projection = {LocalUserTable.USER_ID,
                    LocalUserTable.USER_NAME,
                    LocalUserTable.USER_EMAIL,
                    LocalUserTable.USER_TOKEN,
                    LocalUserTable.USER_ICON};
            Cursor cursor = db.query(LocalUserTable.USER_TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String userId = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_ID));
                    String userName = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_NAME));
                    String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_EMAIL));
                    String userToken = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_TOKEN));
                    String userIcon = cursor.getString(cursor.getColumnIndexOrThrow(LocalUserTable.USER_ICON));
                    users.add(new UserInfo(userId, userName, userEmail, userToken, userIcon));
                    Log.d("Database", userId);
                }
            }
            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public UserInfo UpdateUserInfo(UserInfo userInfo) {
        System.out.println("DatabaseService::UpdateUserInfo");
        SQLiteDatabase db = getWritableDatabase();
        if (db.update(
                LocalUserTable.USER_TABLE_NAME,
                getContentValues(userInfo, userInfo.UserId),
                LocalUserTable.USER_EMAIL + " = ?",
                new String[]{userInfo.UserEmail}) == 0) {
            db.close();
            return null;
        } else {
            db.close();
        }
        return GetUserInfo(userInfo.UserEmail);
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