package com.web.curation.main;

import com.web.curation.diary.Diary;
import com.web.curation.diary.DiaryAnalyticsSentiment;
import com.web.curation.diary.DiaryAnalyticsSentimentDao;
import com.web.curation.diary.DiaryDao;
import com.web.curation.error.CustomException;
import com.web.curation.error.ErrorCode;
import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MainService {

    private MemberAuthDao memberAuthDao;
    private DiaryDao diaryDao;
    private DiaryAnalyticsSentimentDao diaryAnalyticsSentimentDao;

    public int getTotalCnt(String uid){
        UserAuth userAuth = memberAuthDao.findById(uid)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        int totalCnt = diaryDao.countByUserAuthAndIsDeletedIsFalse(userAuth);

        return totalCnt;
    }

    public List<Diary> getMontlyDiary(String uid, LocalDate start, LocalDate end){
        UserAuth userAuth = memberAuthDao.findById(uid)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<Diary> diary = diaryDao.findDiaryByUserAuthAndIsDeletedIsFalseAndDiaryDateBetween(userAuth, start, end);

        return diary;
    }
}
