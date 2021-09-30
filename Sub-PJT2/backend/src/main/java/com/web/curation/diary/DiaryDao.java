package com.web.curation.diary;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.member.UserAuth;

public interface DiaryDao extends JpaRepository<Diary, String>{
	
	Diary getDiaryByUserAuthAndDiaryDateAndIsDeletedIsFalse(
			UserAuth userAuth,LocalDate diaryDate);
	Diary getDiaryByDiaryId(int diaryId);
	

}
