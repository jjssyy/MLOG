package com.web.curation.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryMusic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int diaryId;
	
	@Column(nullable = false)
	private int mid;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "diaryId")
	private Diary diary;
	
	
	
}
