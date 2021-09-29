package com.web.curation.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyDao extends JpaRepository<Survey, String> {

}
