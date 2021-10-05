package com.web.curation.diary;

import com.web.curation.music.MusicInfo;

public class DiaryAdapater {
	
	public static DiaryDto entityToDto(
			DiaryAnalytics diaryAnalytics,
			DiaryAnalyticsSentiment diaryAnalyticsSentiment,
			Diary diary,
			DiaryMusic diaryMusic) {
		return new DiaryDto.DiaryDtoBuilder()
				.diary_id(diary.getDiaryId())
				.uid(diary.getUserAuth().getUid())
				.diaryDate(diary.getDiaryDate())
				.content(diary.getContent())
				.regDate(diary.getRegDate())
				.isDeleted(diary.isDeleted())
				.neutral(diaryAnalytics.getNeutral())
				.joy(diaryAnalytics.getJoy())
				.sadness(diaryAnalytics.getSadness())
				.anger(diaryAnalytics.getAnger())
				.fear(diaryAnalytics.getFear())
				.sentiment(diaryAnalyticsSentiment.getSentiment())
				.accuracy(diaryAnalyticsSentiment.getAccuracy())
				.mid(diaryMusic.getMusicInfo().getMid())
				.musicTitle(diaryMusic.getMusicInfo().getMusicTitle())
				.musicArtist(diaryMusic.getMusicInfo().getMusicArtist())
				.musicGenre(diaryMusic.getMusicInfo().getMusicGenre())
				.videoId(diaryMusic.getMusicInfo().getVideoId())
				.filePath(diaryMusic.getMusicInfo().getFilePath())
				.build();
	}
	

}
