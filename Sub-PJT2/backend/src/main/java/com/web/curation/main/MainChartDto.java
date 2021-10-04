package com.web.curation.main;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainChartDto {
    private List<Float> sentiment;
    private int Count;
}
