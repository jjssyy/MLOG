package com.web.curation.main;

import com.web.curation.diary.DiaryDao;
import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MainService {

    private MemberAuthDao memberAuthDao;
    private DiaryDao diaryDao;

    public int getTotalCnt(String uid){
        UserAuth userAuth = memberAuthDao.getUserAuthByUid(uid);
        int totalCnt = diaryDao.countByUserAuthAndIsDeletedIsFalse(userAuth);
        return totalCnt;
    }

}
