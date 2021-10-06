package com.web.curation.main;

import com.web.curation.diary.Diary;
import com.web.curation.diary.DiaryAnalyticsSentiment;
import com.web.curation.music.MusicInfo;

public class MainDiaryAdapter {
    public static MainDiaryDto entityToDto(Diary diary, DiaryAnalyticsSentiment diaryAnalyticsSentiment, MusicInfo musicInfo){
        return new MainDiaryDto.MainDiaryDtoBuilder()
                .date(diary.getDiaryDate().toString())
                .sentiment(diaryAnalyticsSentiment.getSentiment())
                .accuracy(diaryAnalyticsSentiment.getAccuracy())
                .music_title(musicInfo.getMusicTitle())
                .music_artist(musicInfo.getMusicArtist())
                .build();
    }
}
