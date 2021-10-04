package com.web.curation.survey;

import com.web.curation.error.CustomException;
import com.web.curation.error.ErrorCode;
import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.MemberProfileDao;
import com.web.curation.member.UserAuth;
import com.web.curation.member.UserProfile;
import com.web.curation.member.emotion.Genre;
import com.web.curation.member.emotion.UserEmotion;
import com.web.curation.member.emotion.UserEmotionDao;
import com.web.curation.member.emotion.UserEmotionPK;
import com.web.curation.model.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SurveyService {
    private SurveyDao surveyDao;
    private MemberAuthDao memberAuthDao;
    private MemberProfileDao memberProfileDao;
    private UserEmotionDao userEmotionDao;

    public List<Survey> getAllSurvey(){
        List<Survey> survey = surveyDao.findAll();
        if(survey.size()<12){
            throw new CustomException(ErrorCode.SURVEY_LIST_NOT_FOUND);
        }
        return survey;
    }
    public void initialSurvey(Genre genre, String uid, float neutral, float joy, float sadness, float anger, float fear) {
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
    public void saveSurvey(Genre genre, String uid, float neutral, float joy, float sadness, float anger, float fear){
        UserEmotionPK embGenre = UserEmotionPK.builder()
                .genre(genre)
                .uid(uid)
                .build();

        UserEmotion userEmotion = UserEmotion.builder()
                .embGenre(embGenre)
                .neutral(neutral)
                .joy(joy)
                .sadness(sadness)
                .anger(anger)
                .fear(fear)
                .build();

        userEmotionDao.save(userEmotion);

        //설문조사 여부 변경
        Optional<UserProfile> userProfile = memberProfileDao.findById(uid);
        userProfile.get().setHasSurveyedTrue();
        memberProfileDao.save(userProfile.get());
    }

    public void resetSurvey(String uid){
        UserAuth userAuth = memberAuthDao.findById(uid)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Optional<UserProfile> userProfile = memberProfileDao.findById(uid);

        Optional<List<UserEmotion>> userEmotionList = userEmotionDao.getUserEmotionByUserAuthAndIsDeletedIsFalse(userAuth);

        if(!userProfile.get().isHasSurveyed() && userEmotionList == null){
            throw new CustomException(ErrorCode.ALREADY_RESET_SURVEYED);
        }

        List<UserEmotion> userEmotion = userEmotionList.get();
        userProfile.get().setHasSurveyedFalse();
        memberProfileDao.save(userProfile.get());

        for(UserEmotion u : userEmotion){
            u.setIsDeletedTrue();
            userEmotionDao.save(u);
        }
    }
}
