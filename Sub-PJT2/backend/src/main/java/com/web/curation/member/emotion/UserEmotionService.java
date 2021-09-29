package com.web.curation.member.emotion;

import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserEmotionService {
    private UserEmotionDao userEmotionDao;
    private MemberAuthDao memberAuthDao;

    public Optional<List<UserEmotion>> getUserEmotion(String uid){
        UserAuth userAuth = memberAuthDao.getUserAuthByUid(uid);

        Optional<List<UserEmotion>> userEmotion = userEmotionDao.getUserEmotionByUserAuthAndIsDeletedIsFalse(userAuth);
        userEmotion = Optional.ofNullable(null);

        return userEmotion;
    }
}
