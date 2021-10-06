package com.web.curation.diary;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.member.UserAuth;

public interface DiaryDao extends JpaRepository<Diary, String>{
	
	Diary getDiaryByUserAuthAndDiaryDateAndIsDeletedIsFalse(UserAuth userAuth,LocalDate diaryDate);

	Diary getDiaryByDiaryId(int diaryId);
	
	int countByUserAuthAndIsDeletedIsFalse(UserAuth userAuth);

	List<Diary> findDiaryByUserAuthAndIsDeletedIsFalseAndDiaryDateBetween(UserAuth userAuth, LocalDate start, LocalDate end);
	
	List<Diary> getDiaryByUserAuthAndIsDeletedIsFalse(UserAuth userAuth);
}
