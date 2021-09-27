package com.web.curation.survey;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SurveyService {
    private SurveyDao surveyDao;

    public List<Survey> getAllSurvey(){
        List<Survey> survey = surveyDao.findAll();
        return survey;
    }
}
