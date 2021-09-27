package com.web.curation.member.emotion;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserEmotionService {
    private UserEmotionDao userEmotionDao;

    public Optional<UserEmotion> getUserEmotion(String uid){
        Optional<UserEmotion> userEmotion = userEmotionDao.getUserEmotionByUidAndIs_deletedFalse(uid);
        userEmotion = Optional.ofNullable(null);

        return userEmotion;
    }
}
