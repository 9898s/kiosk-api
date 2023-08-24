package com.example.kiosk.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    EXIST_CUSTOMER_EMAIL("이미 가입된 고객 이메일입니다."),
    NOT_FOUND_CUSTOMER_ID("찾을 수 없는 고객 아이디 번호입니다."),
    ALREADY_DELETE_CUSTOMER("이미 삭제된 고객 계정입니다."),

    EXIST_MANAGER_EMAIL("이미 가입된 점장 이메일입니다."),
    NOT_FOUND_MANAGER_ID("찾을 수 없는 점장 아이디 번호입니다."),
    ALREADY_DELETE_MANAGER("이미 삭제된 점장 계정입니다."),

    NOT_FOUND_SHOP_ID("찾을 수 없는 매장 아이디 번호입니다."),

    NOT_FOUND_RESERVE_ID("찾을 수 없는 예약 아이디 번호입니다."),
    ALREADY_ARRIVE_STATUS("이미 도착한 상태입니다."),
    ALREADY_CANCEL_STATUS("이미 취소한 상태입니다."),
    NOT_RESERVE_TIME("예약할 수 없는 시간입니다."),
    NOT_ARRIVE_TIME("도착 처리할 수 없는 시간입니다."),
    NOT_CANCEL_TIME("취소 처리 할 수 없는 시간입니다."),

    NOT_FOUND_REVIEW_ID("찾을 수 없는 리뷰 아이디 번호입니다."),
    NOT_ARRIVE_STATUS("도착 상태의 예약이 아닙니다."),
    ALREADY_DELETE_REVIEW("이미 삭제된 리뷰입니다."),
    ALREADY_WRITE_REVIEW("이미 리뷰가 작성된 예약 번호입니다.")
    ;

    private String description;
}
