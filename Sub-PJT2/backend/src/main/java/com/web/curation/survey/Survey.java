package com.web.curation.survey;

import com.web.curation.member.emotion.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id
    private String genre;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false, length = 32)
    private String musicTitle;

    @Column(nullable = false, length = 32)
    private String musicArtist;
}
