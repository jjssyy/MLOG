package com.web.curation.survey;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.web.curation.error.CustomException;
import com.web.curation.error.ErrorCode;
import com.web.curation.member.emotion.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private SurveyService surveyService;
    private UserEmotionService userEmotionService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> surveyStart(){
        Map<String, Object> resultMap = new HashMap<>();
        List<Survey> surveyList = surveyService.getAllSurvey();

        resultMap.put("message", "설문조사 리스트");
        resultMap.put("Survey", surveyList);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Map<String, Object>> surveySave(@PathVariable("id") String uid,
                                                          @RequestParam List<String> neutralList, @RequestParam List<String> joyList,
                                                          @RequestParam List<String> sadnessList, @RequestParam List<String> angerList,
                                                          @RequestParam List<String> fearList){
        Map<String, Object> resultMap = new HashMap<>();

        //시작전에 이미 설문조사를 진행한 사용자인지 체크
        Optional<List<UserEmotion>> userEmotionList = userEmotionService.getUserEmotion(uid);
        if(userEmotionList.isPresent() && userEmotionList.get().size()==12){
            throw new CustomException(ErrorCode.ALREADY_SURVEYED);
        }

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(String s : neutralList){
            if(map.get(s) == null){
                ArrayList<String> list = new ArrayList<>();
                map.put(s, list);
            }
            map.get(s).add("neutral");
        }
        for(String s : joyList){
            if(map.get(s) == null){
                ArrayList<String> list = new ArrayList<>();
                map.put(s, list);
            }
            map.get(s).add("joy");
        }
        for(String s : sadnessList){
            if(map.get(s) == null){
                ArrayList<String> list = new ArrayList<>();
                map.put(s, list);
            }
            map.get(s).add("sadness");
        }
        for(String s : angerList){
            if(map.get(s) == null){
                ArrayList<String> list = new ArrayList<>();
                map.put(s, list);
            }
            map.get(s).add("anger");
        }
        for(String s : fearList){
            if(map.get(s) == null){
                ArrayList<String> list = new ArrayList<>();
                map.put(s, list);
            }
            map.get(s).add("fear");
        }

        for(Genre genre : Genre.values()){
            float neutral = 0;
            float joy = 0;
            float sadness = 0;
            float anger = 0;
            float fear = 0;

            surveyService.initialSurvey(genre, uid, neutral, joy, sadness, anger, fear);
        }

        for(String s : map.keySet()){
            Genre genre= Genre .valueOf(s);
            float neutral = 0;
            float joy = 0;
            float sadness = 0;
            float anger = 0;
            float fear = 0;

            for(String emotion : map.get(s)){
                if(emotion.equals("neutral")){
                    neutral = 10;
                }
                if(emotion.equals("joy")){
                    joy = 10;
                }
                if(emotion.equals("sadness")){
                    sadness = 10;
                }
                if(emotion.equals("anger")){
                    anger = 10;
                }
                if(emotion.equals("fear")){
                    fear = 10;
                }
            }
            surveyService.saveSurvey(genre, uid, neutral, joy, sadness, anger, fear);
        }

        resultMap.put("message", "설문조사 결과 저장 성공");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/reset/{id}")
    public ResponseEntity<Map<String, Object>> resetSurvey(@PathVariable String id){
        Map<String, Object> resultMap = new HashMap<>();
        surveyService.resetSurvey(id);

        resultMap.put("message", "설문조사 초기화 성공");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
