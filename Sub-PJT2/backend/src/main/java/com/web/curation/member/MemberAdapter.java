package com.web.curation.member;

public class MemberAdapter {
    public static MemberDto entityToDto(UserProfile userProfile, UserAuth userAuth){
        return new MemberDto.MemberDtoBuilder()
                .uid(userAuth.getUid())
                .email(userAuth.getEmail())
                .emailCompany(userAuth.getEmailCompany())
                .filePath(userProfile.getFilePath())
                .nickname(userProfile.getNickname())
                .hasSurveyed(userProfile.isHasSurveyed())
                .build();
    }
}
