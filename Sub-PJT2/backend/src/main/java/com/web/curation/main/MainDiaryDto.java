package com.web.curation.main;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainDiaryDto {
    private LocalDate date;
    private float sentiment;
    private float accuracy;
    private String music_title;
    private String music_artist;
}
