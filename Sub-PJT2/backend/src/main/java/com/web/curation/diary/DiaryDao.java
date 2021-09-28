package com.web.curation.diary;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryDao extends JpaRepository<Diary, String>{
	
	Diary getDiaryByUidAndDiaryDateAndIsDeletedIsFalse(
			String Uid,LocalDate DiaryDate);
	
	

}
