package com.web.curation.survey;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    List<String> neutralList;
    List<String> joyList;
    List<String> sadnessList;
    List<String> angerList;
    List<String> fearList;

}
