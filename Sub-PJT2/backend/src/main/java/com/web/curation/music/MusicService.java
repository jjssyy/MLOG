package com.web.curation.music;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.web.curation.member.MemberAuthDao;
import com.web.curation.member.MemberProfileDao;
import com.web.curation.member.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

import javax.swing.border.TitledBorder;

import org.hibernate.internal.build.AllowSysOut;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Slf4j
@AllArgsConstructor
@Service
public class MusicService {
	
	private MusicDao musicDao;
	
	
	public void updateMusic() throws IOException, ParseException {		
		for(int i=3550;i<3575;i++) {
			String Genre="";
			Genre=convertGenre(i);
			if(Genre==null)continue;
			long L=System.currentTimeMillis();
			final String URL="https://www.music-flo.com/api/display/v1/browser/chart/"+i+"/track/list?size=100&timestamp="+L;
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(URL, String.class);
			JSONParser parser = new JSONParser();
			Object obj= parser.parse(response);
			JSONObject jsonObj = (JSONObject) obj;
			JSONObject data=(JSONObject)jsonObj.get("data");
			JSONArray track=(JSONArray) data.get("trackList");
			for(int j=0;j<1;j++) {
				JSONObject music=(JSONObject) track.get(j);
				JSONObject music_artist=(JSONObject) music.get("representationArtist");
				String title=(String) music.get("name");
				String Artist=(String) music_artist.get("name");
				MusicInfo music_info=musicDao.getMusicInfoByMusicTitleAndMusicArtistAndMusicGenre(
						title, Artist, Genre);
				
				if(music_info==null) {
					String videoid=getVideoid(Artist,title);
					System.out.println(Artist+" "+title+" "+videoid+" "+Genre);
					MusicInfo musicInfo=MusicInfo.builder()
							.musicTitle(title)
							.musicArtist(Artist)
							.videoId(videoid)
							.musicGenre(Genre)
							.build();
					musicDao.save(musicInfo);				
				}
				else {
					System.out.println(title+"은 이미존재하는 노래입니다.");
					System.out.println("===================================");
				}
			}	
			
		}
	}

	private String getVideoid(String artist, String title) throws ParseException {
		final String URL="https://www.googleapis.com/youtube/v3/search?part=snippet&q="+artist+" "+title+"&key=AIzaSyAegoKwuARop9nKQSf4JTU8UECOAgAeviw";
		RestTemplate restTemplate=new RestTemplate();
		String response = restTemplate.getForObject(URL,String.class);
		JSONParser parser = new JSONParser();
		Object obj= parser.parse(response);
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray item =(JSONArray) jsonObj.get("items");
		JSONObject track=(JSONObject) item.get(0);
		JSONObject id=(JSONObject) track.get("id");
		String video=(String) id.get("videoId");
//		System.out.println(item.get(0));
		return video;
		
	}

	private String convertGenre(int i) {
		switch(i) {
			case 3550:
				return "ballad";
			case 3551: case 3562:
				return "dance_electronic";
			case 3552: case 3560:
				return "hiphop";
			case 3553: case 3561:
				return "r_n_b";
			case 3554:
				return "trot";
			case 3555:
				return "indie";
			case 3557:
				return "folk_blues";
			case 3558: case 3563: case 3564:
				return "rock_metal";
			case 3559: case 3556:
				return "pop_acoustic";
			case 3566:
				return "classic";
			case 3567:
				return "newage";
			case 3574:
				return "ccm";
			default:
				return null;
		}
		
	}

}
