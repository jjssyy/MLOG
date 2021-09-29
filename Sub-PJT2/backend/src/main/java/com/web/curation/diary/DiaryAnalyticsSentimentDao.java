package com.web.curation.diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryAnalyticsSentimentDao extends JpaRepository<DiaryAnalyticsSentiment, String>{

	
	DiaryAnalyticsSentiment getDiaryAnalyticsSentimentByDiaryId(int DiaryId);
}
