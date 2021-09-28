package com.web.curation.music;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicDao extends JpaRepository<MusicInfo,String> {

	MusicInfo getMusicInfoByMusicTitleAndMusicArtistAndMusicGenre(
			String MusicTitle, String MusicArtist, String MusicGenre);
	
}
