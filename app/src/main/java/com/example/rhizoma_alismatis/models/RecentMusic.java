package com.example.rhizoma_alismatis.models;

public class RecentMusic {
    public String MusicId;
    public String MusicName;
    public String MusicCover;
    public String MusicFrom;

    public RecentMusic(String musicId, String musicName, String musicCover, String musicFrom)
    {
        MusicId = musicId;
        MusicName = musicName;
        MusicCover = musicCover;
        MusicFrom = musicFrom;
    }

    public RecentMusic(){
        MusicId = "";
        MusicName = "";
        MusicCover = "";
        MusicFrom = "";
    }

    public static final String RECENT_MUSIC = "recent_music";
    public static final String RECENT_MUSIC_ID = "recent_music_id";
    public static final String RECENT_MUSIC_NAME = "recent_music_name";
    public static final String RECENT_MUSIC_COVER = "recent_music_cover";
    public static final String RECENT_MUSIC_FROM = "recent_music_from";
}