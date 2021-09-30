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
public class DiaryAnalytics {

	@Id
	private int diaryId;
	
	@Column(nullable = false)
	private float neutral;
	
	@Column(nullable =  false)
	private float joy;

	@Column(nullable = false)
	private float sadness;
	
	@Column(nullable =  false)
	private float anger;
	
	@Column(nullable =  false)
	private float fear;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "diaryId")
	private Diary diary;
	
	public void updaetemotion(float[] emotion) {
		this.neutral=emotion[0];
		this.joy=emotion[1];
		this.sadness=emotion[2];
		this.anger=emotion[3];
		this.fear=emotion[4];
	}
}
