package com.web.curation.music;

import com.web.curation.diary.Diary;

public class MusicAdapater {
	
	public static MusicDto entityToDto(
			Diary diary,
			MusicInfo musicInfo
			) {
		return new MusicDto.MusicDtoBuilder()
				.date(diary.getDiaryDate())
				.mid(musicInfo.getMid())
				.musicAritst(musicInfo.getMusicArtist())
				.musicGenre(musicInfo.getMusicGenre())
				.videoId(musicInfo.getVideoId())
				.musicTitle(musicInfo.getMusicTitle())
				.filePath(musicInfo.getFilePath())
				.build();
	}

}
