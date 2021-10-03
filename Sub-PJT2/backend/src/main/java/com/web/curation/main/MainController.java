package com.web.curation.main;

import com.web.curation.diary.Diary;
import com.web.curation.diary.DiaryAnalyticsSentiment;
import com.web.curation.diary.DiaryAnalyticsSentimentDao;
import com.web.curation.diary.DiaryService;
import com.web.curation.music.MusicInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/main")
public class MainController {

    private MainService mainService;
    private DiaryService diaryService;

    @GetMapping("/{id}/totalcnt")
    public ResponseEntity<Map<String, Object>> getTotalCnt(@PathVariable String id){
        HashMap<String, Object> resultMap = new HashMap<>();

        int totalCnt = mainService.getTotalCnt(id);

        resultMap.put("message", "총 일기 작성 수");
        resultMap.put("totalCnt", totalCnt);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/{id}/{startDate}/{endDate}")
    public ResponseEntity<Map<String, Object>> MonthlyDiaryInfo(@PathVariable String id,
                                                                @PathVariable String startDate,
                                                                @PathVariable String endDate){
        HashMap<String, Object> resultMap = new HashMap<>();

        //1. uid와 month로 diary 테이블에서 diaryid와 일기 작성 날짜 가져오기 (리스트, is_deleted=false)
        //2. diaryid로 diaryanalyticssentiment 가져오기 (긍부정, 정확도)
        //3. diaryid로 music_info 가져오기
        List<MainDiaryDto> resultList = new ArrayList<>();

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<Diary> diaryList = mainService.getMontlyDiary(id, start, end);
        if(diaryList.isEmpty()){
            resultMap.put("message", "해당 사용자의 일기 정보가 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        for(Diary diary : diaryList){
            MainDiaryDto mainDiaryDto = new MainDiaryDto();

            int diaryId = diary.getDiaryId();
            DiaryAnalyticsSentiment diaryAnalyticsSentiment = diaryService.getDiaryAnalyticsSentiment(diaryId);
            MusicInfo musicInfo = diaryService.getMusicInfo(diaryId);

            mainDiaryDto.setDate(diary.getDiaryDate());
            mainDiaryDto.setSentiment(diaryAnalyticsSentiment.getSentiment());
            mainDiaryDto.setAccuracy(diaryAnalyticsSentiment.getAccuracy());
            mainDiaryDto.setMusic_title(musicInfo.getMusicTitle());
            mainDiaryDto.setMusic_artist(musicInfo.getMusicArtist());

            resultList.add(mainDiaryDto);
        }
        resultMap.put("message", "월별 일기 정보");
        resultMap.put("data", resultList);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

}
