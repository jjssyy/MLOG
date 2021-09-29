package com.web.curation.survey;

import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import com.web.curation.member.emotion.Genre;
import com.web.curation.member.emotion.UserEmotion;
import com.web.curation.member.emotion.UserEmotionDao;
import com.web.curation.member.emotion.UserEmotionPK;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SurveyService {
    private SurveyDao surveyDao;
    private MemberAuthDao memberAuthDao;
    private UserEmotionDao userEmotionDao;

    public List<Survey> getAllSurvey(){
        List<Survey> survey = surveyDao.findAll();
        return survey;
    }

    public void saveSurvey(Genre genre, String uid, float neutral, float joy, float sadness, float anger, float fear){
        UserAuth userAuth = memberAuthDao.getUserAuthByUid(uid);
        UserEmotionPK embGenre = UserEmotionPK.builder()
                .genre(genre)
                .build();

        UserEmotion userEmotion = UserEmotion.builder()
                .embGenre(embGenre)
                .userAuth(userAuth)
                .neutral(neutral)
                .joy(joy)
                .sadness(sadness)
                .anger(anger)
                .fear(fear)
                .build();

        userEmotionDao.save(userEmotion);
    }
}
