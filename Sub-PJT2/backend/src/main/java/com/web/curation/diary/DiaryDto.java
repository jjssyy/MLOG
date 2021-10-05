package com.web.curation.diary;

import java.time.LocalDate;

import com.web.curation.member.MemberDto;
import com.web.curation.music.MusicInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {
	
	private int diary_id;
	private String uid;
	private LocalDate diaryDate;
	private String content;
	private LocalDate regDate;
	private boolean isDeleted;
	private float neutral;
	private float joy;
	private float sadness;
	private float anger;
	private float fear;
	private float sentiment;
	private float accuracy;
	private int mid;
	private String musicTitle;
	private String musicArtist;
	private String videoId;
	private String musicGenre;
	private String filePath;
	
	
	
	
	

}
