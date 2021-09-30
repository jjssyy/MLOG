package com.web.curation.main;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/main")
public class MainController {

    private MainService mainService;

    @GetMapping("/{id}/totalcnt")
    public ResponseEntity<Map<String, Object>> getTotalCnt(@PathVariable String id){
        HashMap<String, Object> resultMap = new HashMap<>();

        int totalCnt = mainService.getTotalCnt(id);

        resultMap.put("message", "총 일기 작성 수");
        resultMap.put("totalCnt", totalCnt);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
