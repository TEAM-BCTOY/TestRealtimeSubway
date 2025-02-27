package com.team_bctoy.testrealtimesubway.api.data

data class RealtimeStationPosition(
    val realtimePositionList: List<PositionInfo>
)

/**
 * @property [subwayNm] 지하철 호선명
 * @property [statnNm] 지하철역명
 * @property [recptnDt] 최종수신시간
 * @property [statnTnm] 종착지하철역명
 * @property [trainSttus] 열차상태 구분(0: 진입, 1: 도착, 2: 출발, 3: 전역출발)
 * @property [directAt] 급행여부(1 : 급행, 0 : 아님, 7 : 특급)
 * @property [lstcarAt] 막차 여부(1 : 막차, 0 : 아님)
 */
data class PositionInfo(
    val subwayNm: String,
    val statnNm: String,
    val recptnDt: String,
    val statnTnm: String,
    val trainSttus: String,
    val directAt: String?,
    val lstcarAt: String,
) {
    override fun toString(): String {
        val isLast = if(lstcarAt.toBoolean()) "막차" else ""
        with(this) {
            return "$subwayNm $statnTnm 행 $statnNm ${trainSttus.toTrainStatus()} $isLast\n$recptnDt 기준"
        }
    }
}

fun String.toTrainStatus() : String {
    return when(this) {
        "0" -> "진입"
        "1" -> "도착"
        "2" -> "출발"
        "3" -> "전역 출발"
        else -> throw IllegalArgumentException("trainSttus 값에만 해당 함수를 사용할 수 있습니다!")
    }
}