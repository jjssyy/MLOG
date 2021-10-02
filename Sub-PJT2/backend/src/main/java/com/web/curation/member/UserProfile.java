package com.web.curation.member;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	@Id
	private String uid;

	@Column(nullable = false, length = 256)
	private String filePath;
	
	@Column(nullable = false, length = 12)
	private String nickname;

	@Column(nullable = false)
	private boolean hasSurveyed;

	@OneToOne
	@MapsId
	@JoinColumn(name = "uid")
	private UserAuth userAuth;

	public void setHasSurveyedTrue() {
		this.hasSurveyed=true;
	}
	public void setHasSurveyedFalse() {
		this.hasSurveyed=false;
	}
}
