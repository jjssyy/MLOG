package com.web.curation.diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryAnalyticsDao extends JpaRepository<DiaryAnalytics, String>{
	
	DiaryAnalytics getDiaryAnalyticsByDiaryId(int DiaryId);

}
