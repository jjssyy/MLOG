package com.web.curation.survey;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private SurveyService surveyService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> surveyStart(){
        Map<String, Object> resultMap = new HashMap<>();
        List<Survey> surveyList = surveyService.getAllSurvey();
        resultMap.put("message", "설문조사 리스트");
        resultMap.put("Survey", surveyList);
        
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
