package com.web.curation.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String uid;
    private String email;
    private String emailCompany;
    private String authToken;
    private String filePath;
    private String nickname;
    private boolean hasSurveyed;
}
