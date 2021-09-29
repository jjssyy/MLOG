package com.web.curation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음*/
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 중복된 데이터 존재 */
    //FEEDLIKE_DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "피드 좋아요가 이미 존재합니다."),

    ;
    private final HttpStatus httpStatus;
    private final String detail;
}