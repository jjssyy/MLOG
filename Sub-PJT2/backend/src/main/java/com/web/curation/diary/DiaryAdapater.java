package com.web.curation.diary;

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
				.build();
	}
	

}
