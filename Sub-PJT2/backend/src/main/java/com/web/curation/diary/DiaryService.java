package com.web.curation.diary;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.MemberProfileDao;
import com.web.curation.member.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class DiaryService {
	private DiaryDao diaryDao;
	private DiaryAnalyticsDao diaryAnalyticsDao;
	private DiaryAnalyticsSentimentDao diaryAnalyticsSentimentDao;
	private DiaryMusicDao diaryMusicDao;
	
	public DiaryDto getDiaryByUidAndDiaryDate(String Uid, LocalDate DiaryDate) {
		DiaryDto result =new DiaryDto(); 
		Diary diary=diaryDao.getDiaryByUidAndDiaryDateAndIsDeletedIsFalse(Uid, DiaryDate);
		if(diary!=null) {
			DiaryAnalytics diaryAnalytics = diaryAnalyticsDao.getDiaryAnalyticsByDiaryId(diary.getDiaryId());
//			DiaryAnalyticsSentiment diaryAnalyticsSentiment=diaryAnalyticsSentimentDao.getDiaryAnalyticsSentimentByDiaryId(diary.getDiaryId());
//			DiaryMusic diaryMusic=diaryMusicDao.getDiaryMusicByDiaryId(diary.getDiaryId());
//			result=DiaryAdapater.entityToDto(diaryAnalytics, diaryAnalyticsSentiment, diary, diaryMusic);
			result=DiaryAdapater.entityToDto(diaryAnalytics, null, diary, null);
		}
		return result;
	}
	public void WriteDiary(String Uid,String content,LocalDate date,float[] emotion) {
		
		Diary diary_info=Diary.builder()
				.uid(Uid)
				.content(content)
				.diaryDate(date)
				.isDeleted(false)
				.build();
		Diary diary=diaryDao.save(diary_info);
		DiaryAnalytics diaryAnalytics=DiaryAnalytics.builder()
				.diary(diary)
				.neutral(emotion[0])
				.joy(emotion[1])
				.sadness(emotion[2])
				.anger(emotion[3])
				.fear(emotion[4])
				.build();
		diaryAnalyticsDao.save(diaryAnalytics);
	}
}
