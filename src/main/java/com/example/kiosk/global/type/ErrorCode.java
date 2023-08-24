package com.example.kiosk.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    EXIST_ACCOUNT_EMAIL("이미 가입된 이메일입니다."),
    NOT_FOUND_ID("찾을 수 없는 아이디 번호입니다."),
    NOT_RESERVE_TIME("예약할 수 없는 시간입니다."),
    NOT_ARRIVE_TIME("도착 처리할 수 없는 시간입니다."),
    NOT_CANCEL_TIME("취소 처리 할 수 없는 시간입니다.")
    ;

    private String description;
}
