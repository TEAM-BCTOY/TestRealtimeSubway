package com.team_bctoy.testrealtimesubway.ui.data

/**
 * 화면에 쓰이는 데이터
 * @property [searchStation] 조회한 역
 * @property [upAndDown] 상하행 구분
 * @property [destinationAndDirection] 종착역과 방면
 * @property [trainKind] 열차 종류
 * @property [beforeInfo] 몇번째 전인지
 * @property [nowSubwayStationName] 현재 지하철이 어디있는지
 * @property [arrivalCode] 도착 코드
 * @property [isLast] 막차 구분(막차일때 true)
 */
data class RealtimeArrivalInfo(
    val searchStation: String,
    val upAndDown: String,
    val destinationAndDirection: String,
    val trainKind: String,
    val beforeInfo: String,
    val nowSubwayStationName: String,
    val arrivalCode: String,
    val isLast: Boolean,
)
