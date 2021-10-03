package com.web.curation.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.curation.diary.Diary;
import com.web.curation.diary.DiaryAdapater;
import com.web.curation.diary.DiaryAnalytics;
import com.web.curation.diary.DiaryAnalyticsDao;
import com.web.curation.diary.DiaryAnalyticsSentiment;
import com.web.curation.diary.DiaryAnalyticsSentimentDao;
import com.web.curation.diary.DiaryDao;
import com.web.curation.diary.DiaryDto;
import com.web.curation.diary.DiaryMusic;
import com.web.curation.diary.DiaryMusicDao;
import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import com.web.curation.music.MusicInfo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ProfileService {

	DiaryDao diaryDao;
	MemberAuthDao memberAuthDao;
	DiaryMusicDao diaryMusicDao;
	DiaryAnalyticsDao diaryAnalyticsDao;
	DiaryAnalyticsSentimentDao diaryAnalyticsSentimentDao;
	public List<MusicInfo> getMyPlayList(String id){
		List<MusicInfo> result=new ArrayList<MusicInfo>();
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(id);
		List<Diary> tmp_DiaryList=diaryDao.getDiaryByUserAuth(userAuth);
		for(int i=0;i<tmp_DiaryList.size();i++) {
			DiaryMusic currentDiaryMusic=diaryMusicDao.getDiaryMusicByDiary(tmp_DiaryList.get(i));
			result.add(currentDiaryMusic.getMusicInfo());
		}
		return result;
	}
	
	public List<DiaryDto> getMyDiary(String id){
		List<DiaryDto> result =new ArrayList<DiaryDto>();
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(id);
		List<Diary> tmp_DiaryList=diaryDao.getDiaryByUserAuth(userAuth);
		for(int i=0;i<tmp_DiaryList.size();i++) {
			DiaryDto tmpDiaryDto=new DiaryDto();
			DiaryAnalytics diaryAnalytics = diaryAnalyticsDao.getDiaryAnalyticsByDiary(tmp_DiaryList.get(i));
			DiaryAnalyticsSentiment diaryAnalyticsSentiment=diaryAnalyticsSentimentDao.getDiaryAnalyticsSentimentByDiary(tmp_DiaryList.get(i));
			tmpDiaryDto=DiaryAdapater.entityToDto(diaryAnalytics, diaryAnalyticsSentiment, tmp_DiaryList.get(i), null);
			result.add(tmpDiaryDto);
		}
		
		return result;
	}
	
	public List<DiaryAnalyticsSentiment> getMYReportSentiment(String id,LocalDate startDate,LocalDate endDate){
		List<DiaryAnalyticsSentiment> result =new ArrayList<DiaryAnalyticsSentiment>();
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(id);
		List<Diary>  tmpDiaryList=diaryDao.findDiaryByUserAuthAndIsDeletedIsFalseAndDiaryDateBetween(userAuth, startDate, endDate);
		for(int i=0;i<tmpDiaryList.size();i++) {
			DiaryAnalyticsSentiment diaryAnalyticsSentiment=diaryAnalyticsSentimentDao.getDiaryAnalyticsSentimentByDiary(tmpDiaryList.get(i));
		
			result.add(diaryAnalyticsSentiment);
		}
		return result;
	}
	public int getMYReportDiary(String id,LocalDate startDate,LocalDate endDate) {
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(id);
		List<Diary>  tmpDiaryList=diaryDao.findDiaryByUserAuthAndIsDeletedIsFalseAndDiaryDateBetween(userAuth, startDate, endDate);		
		return tmpDiaryList.size();
	}
}
