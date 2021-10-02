package com.web.curation.diary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web.curation.config.KeyConfig;
import com.web.curation.music.MusicDao;
import com.web.curation.music.MusicInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.UserAuth;
import com.web.curation.member.emotion.Genre;
import com.web.curation.member.emotion.UserEmotion;
import com.web.curation.member.emotion.UserEmotionDao;
import com.web.curation.member.emotion.UserEmotionPK;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class DiaryService {
	private DiaryDao diaryDao;
	private DiaryAnalyticsDao diaryAnalyticsDao;
	private DiaryAnalyticsSentimentDao diaryAnalyticsSentimentDao;
	private DiaryMusicDao diaryMusicDao;
	private MemberAuthDao memberAuthDao;
	private UserEmotionDao userEmotionDao;
	private MusicDao musicDao;

	private final KeyConfig keyConfig;

	
	public DiaryDto getDiaryByUidAndDiaryDate(String Uid, LocalDate DiaryDate) {
		DiaryDto result =new DiaryDto(); 
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(Uid);
		Diary diary=diaryDao.getDiaryByUserAuthAndDiaryDateAndIsDeletedIsFalse(userAuth, DiaryDate);
		if(diary!=null) {
			DiaryAnalytics diaryAnalytics = diaryAnalyticsDao.getDiaryAnalyticsByDiaryId(diary.getDiaryId());
			DiaryAnalyticsSentiment diaryAnalyticsSentiment=diaryAnalyticsSentimentDao.getDiaryAnalyticsSentimentByDiaryId(diary.getDiaryId());
//			DiaryMusic diaryMusic=diaryMusicDao.getDiaryMusicByDiaryId(diary.getDiaryId());
//			result=DiaryAdapater.entityToDto(diaryAnalytics, diaryAnalyticsSentiment, diary, diaryMusic);
			result=DiaryAdapater.entityToDto(diaryAnalytics, diaryAnalyticsSentiment, diary, null);
		}
		return result;
	}
	public void WriteDiary(String Uid,String content,LocalDate date) {
		float[] emotion=new float[5];
		emotion=find(content);
		DiaryDto dto=new DiaryDto();
		dto.setContent(content);;
		dto=getSentiment(dto);
		
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(Uid);
		Diary diary_info=Diary.builder()
				.userAuth(userAuth)
				.content(content)
				.diaryDate(date)
				.isDeleted(false)
				.build();
		Diary diary=diaryDao.save(diary_info);
		DiaryAnalytics diaryAnalytics=DiaryAnalytics.builder()
				.diary(diary)
				.neutral(emotion[0])
				.joy(emotion[1])
				.sadness(emotion[2])
				.anger(emotion[3])
				.fear(emotion[4])
				.build();
		diaryAnalyticsDao.save(diaryAnalytics);
		DiaryAnalyticsSentiment diaryAnalyticsSentiment=DiaryAnalyticsSentiment.builder()
				.diary(diary)
				.accuracy(dto.getAccuracy())
				.sentiment(dto.getSentiment())
				.build();
		diaryAnalyticsSentimentDao.save(diaryAnalyticsSentiment);
		
	}
	
	public void modifyDiary(int diary_id,String content,LocalDate date) {
		float[] emotion = new float[5];
		emotion = find(content);
		DiaryDto dto=new DiaryDto();
		dto.setContent(content);;
		dto=getSentiment(dto);
		Diary deleteDiary=diaryDao.getDiaryByDiaryId(diary_id);
		deleteDiary.deletediary();
		UserAuth userAuth=deleteDiary.getUserAuth();
		Diary diary_info=Diary.builder()
				.userAuth(userAuth)
				.content(content)
				.diaryDate(date)
				.isDeleted(false)
				.build();
		Diary diary=diaryDao.save(diary_info);
		DiaryAnalytics diaryAnalytics=DiaryAnalytics.builder()
				.diary(diary)
				.neutral(emotion[0])
				.joy(emotion[1])
				.sadness(emotion[2])
				.anger(emotion[3])
				.fear(emotion[4])
				.build();
		DiaryAnalyticsSentiment diaryAnalyticsSentiment=DiaryAnalyticsSentiment.builder()
				.diary(diary)
				.accuracy(dto.getAccuracy())
				.sentiment(dto.getSentiment())
				.build();
		diaryAnalyticsSentimentDao.save(diaryAnalyticsSentiment);
		diaryAnalyticsDao.save(diaryAnalytics);
		diaryDao.save(deleteDiary);
		
	}
	
	public void deleteDiary(int diary_id) {
		Diary deleteDiary=diaryDao.getDiaryByDiaryId(diary_id);
		deleteDiary.deletediary();
		diaryDao.save(deleteDiary);
	}
	
	public MusicInfo[] getMusicList(int diary_id,String uid) {
		MusicInfo[] result=new MusicInfo[5];
		float[] Diary_Anlatics=new float[5];
		DiaryAnalytics emotion=diaryAnalyticsDao.getDiaryAnalyticsByDiaryId(diary_id);
		Diary_Anlatics[0]=emotion.getNeutral();
		Diary_Anlatics[1]=emotion.getJoy();
		Diary_Anlatics[2]=emotion.getSadness();
		Diary_Anlatics[3]=emotion.getAnger();
		Diary_Anlatics[4]=emotion.getFear();
		UserAuth userAuth=memberAuthDao.getUserAuthByUid(uid);
		Optional<List<UserEmotion>> optional=userEmotionDao.getUserEmotionByUserAuthAndIsDeletedIsFalse(userAuth);
		List<UserEmotion> userEmotionList=optional.get();
		rank[] rank=new rank[12];
		for(int i=0;i<12;i++) {
			UserEmotion userEmotion=userEmotionList.get(i);
			UserEmotionPK emGenre=userEmotion.getEmbGenre();
			Genre genre=emGenre.getGenre();
			String genreName=genre.toString();
			float[] compare=new float[5];
			compare[0]=userEmotion.getNeutral();
			compare[1]=userEmotion.getJoy();
			compare[2]=userEmotion.getSadness();
			compare[3]=userEmotion.getAnger();
			compare[4]=userEmotion.getFear();
			
			float score=cosineSimilarity(Diary_Anlatics, compare);
			rank tmp=new rank(genreName,score);
			rank[i]=tmp;	
		}	
		Arrays.sort(rank);
		for(int i=0;i<12;i++) {
			System.out.println(rank[i].cosine);
		}
		for(int i=0;i<3;i++) {
			String genre=rank[i].genre;
			List<MusicInfo> musicList=musicDao.getMusicInfoByMusicGenre(genre);
			int random=(int) (Math.random()*musicList.size());
			//중간에 체크하는 과정 추가.해야함
			result[i]=musicList.get(random);
		}
		int v=0;
		int CheckDuplicate=0;
		for(int i=3;i<5;i++) {
			while(true) {
				int min=4;
				int max=12;
				v=(int) (Math.random()*(max-min)+min);
				System.out.println(v);
				if(CheckDuplicate-v==0)continue;
				else break;
			}
			String genre=rank[v].genre;
			List<MusicInfo> musicList=musicDao.getMusicInfoByMusicGenre(genre);
			int random_value=(int) (Math.random()*musicList.size());
			//중간에 체크하는 과정 추가.해야함
			result[i]=musicList.get(random_value);
			CheckDuplicate+=v;
		}
		
		return result;
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


	/**
	 * getSentiment의 설명을 작성
	 * diaryDto의 content를 GCP로 보내
	 * diaryDto의 accuracy, sentiment에 값을 저장
	**/
	public DiaryDto getSentiment(DiaryDto diaryDto){
		JSONObject data=new JSONObject();
		JSONObject document=new JSONObject();
		document.put("type", "PLAIN_TEXT");
		document.put("content", diaryDto.getContent());
		data.put("document", document);

		System.out.println(keyConfig.getGCPKey());

		HttpURLConnection conn=null;
		String reqURL= "https://language.googleapis.com/v1/documents:analyzeSentiment?key=" + keyConfig.getGCPKey();
		try {
			URL url = new URL(reqURL);
			conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", Integer.toString(data.toJSONString().length()));
			conn.setDoOutput(true);
			conn.connect();

			//Post방식으로 스트링을통한 JSON전송
			BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(data.toString());
			bw.flush();
			bw.close();

			//서버에서 보낸 응답 데이터 수신 받기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			StringBuilder result = new StringBuilder();

			while ((line = br.readLine()) != null) {
				result.append(line);
			}

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result.toString());

			JsonObject documentSentiment = element.getAsJsonObject().get("documentSentiment").getAsJsonObject();

			String magnitude = documentSentiment.getAsJsonObject().get("magnitude").getAsString();
			String score = documentSentiment.getAsJsonObject().get("score").getAsString();

			diaryDto.setAccuracy(Float.parseFloat(magnitude));
			diaryDto.setSentiment(Float.parseFloat(score));
		}catch(Exception e) {
			e.printStackTrace();
		}

		return diaryDto;
	}
	public DiaryAnalyticsSentiment getDiaryAnalyticsSentiment(int DiaryID){
		DiaryAnalyticsSentiment diarySentiment = diaryAnalyticsSentimentDao.getDiaryAnalyticsSentimentByDiaryId(DiaryID);

		return diarySentiment;
	}

	public MusicInfo getMusicInfo(int diaryId){
		DiaryMusic diaryMusic = diaryMusicDao.getDiaryMusicByDiaryId(diaryId);
		if(diaryMusic == null){
			System.out.println("null 값임");
		}
		MusicInfo musicInfo = diaryMusic.getMusicInfo();
		return musicInfo;
	}
	
	static public class rank implements Comparable<rank>{
		String genre;
		float cosine;
		public rank(String genre, float cosine) {
			super();
			this.genre = genre;
			this.cosine = cosine;
		}
		@Override
		public int compareTo(rank o) {
			if(this.cosine>o.cosine)return -1;
			return 1;
		}
		
	}
	
	public static float cosineSimilarity(float[] vectorA, float[] vectorB) {
	    float dotProduct = 0;
	    float normA = 0;
	    float normB = 0;
	    for (int i = 0; i < vectorA.length; i++) {
	        dotProduct += vectorA[i] * vectorB[i];
	        normA += Math.pow(vectorA[i], 2);
	        normB += Math.pow(vectorB[i], 2);
	    }   
	    double tmp=dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	    float result=(float)tmp;
	    return result;
	}
}


