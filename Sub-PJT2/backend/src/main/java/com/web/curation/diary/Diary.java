package com.web.curation.diary;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.music.MusicInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int diaryId;
	
	@Column(nullable = false, length = 13)
	private String uid;
	
	@Column(nullable = false)
	private LocalDate diaryDate;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	@JsonIgnore
	@CreationTimestamp
	private LocalDate regDate;
	
	@Column( nullable =false)
	private boolean isDeleted;
	

}
