package com.web.curation.music;

import com.web.curation.member.MemberDto;

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
public class MusicDto {

	private int mid;
	private String musicTitle;
	private String musicAritst;
	private String videoId;
	private String musicGenre;
}
