package com.web.curation.diary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.web.curation.member.MemberController;
import com.web.curation.member.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

	private DiaryService diarySerivce;
	
	@GetMapping("/{id}/{date}")
	public ResponseEntity<Map<String, Object>> ReadDiary(
			@PathVariable String id,
			@PathVariable String date
			){
		LocalDate localdate = LocalDate.parse(date,DateTimeFormatter.ISO_DATE);
		
		Map<String, Object> resultmap= new HashMap<String, Object>();
		DiaryDto dto=diarySerivce.getDiaryByUidAndDiaryDate(id, localdate);
		
		
		resultmap.put("status", HttpStatus.OK);
        resultmap.put("message", "일기 정보");
		resultmap.put("diaryInfo",dto);
		
		return new ResponseEntity<>(resultmap,HttpStatus.OK); 
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Map<String, Object>> WriteDiary(
			@PathVariable String id,
			@RequestParam String content,
			@RequestParam String date
			){
		LocalDate localdate= LocalDate.parse(date,DateTimeFormatter.ISO_DATE);
		Map<String, Object> resultMap=new HashMap<>();
		float[] emotion=new float[5];
		emotion=find(content);
		diarySerivce.WriteDiary(id, content, localdate, emotion);
		
		
		resultMap.put("status", HttpStatus.OK);
		resultMap.put("meesage", "success");
		
		return new ResponseEntity<>(resultMap,HttpStatus.OK);
	}

	private float[] find(String content) {
		double[] tmp=new double[5];
		float[] result=new float[5];
		JSONObject data=new JSONObject();
		data.put("content", content);
//		Flask호출부분
		HttpURLConnection conn=null;
		String reqURL= "http://172.30.1.45:5000/predict";
		try {
			URL url = new URL(reqURL);
			conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);//OutputStream을 사용하여 post body 데이터 전송
			//http 요청 실시
			conn.connect();
			System.out.println("http 요청 방식 : "+"POST BODY JSON");
			System.out.println("http 요청 타입 : "+"application/json");
			System.out.println("http 요청 주소 : "+reqURL);
			System.out.println("http 요청 데이터 : "+data);
			System.out.println("");
			//Post방식으로 스트링을통한 JSON전송
			BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(data.toString());
			bw.flush();
			bw.close();
			//서버에서 보낸 응답 데이터 수신 받기
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String returnMsg=in.readLine();
			System.out.println("응답메세지: "+returnMsg);
			
			JSONParser parser =new JSONParser();
			Object obj=parser.parse(returnMsg);
			JSONObject result_data=(JSONObject) obj;
			
			tmp[0]=(double) result_data.get("neutral");
			tmp[1]=(double) result_data.get("joy");
			tmp[2]=(double) result_data.get("sadness");
			tmp[3]=(double) result_data.get("anger");
			tmp[4]=(double) result_data.get("fear");
			result[0]=(float)tmp[0];
			result[1]=(float)tmp[1];
			result[2]=(float)tmp[2];
			result[3]=(float)tmp[3];
			result[4]=(float)tmp[4];
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
