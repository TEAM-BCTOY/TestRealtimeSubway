package com.team_bctoy.testrealtimesubway.api.data

import com.team_bctoy.testrealtimesubway.scene.RealtimeArrivalInfo
import com.team_bctoy.testrealtimesubway.utils.toArrivalCode

data class ResponseSpringArrivalInfoToStationName(
    val realtimeArrivalList: List<ArrivalInfo>
)

data class ArrivalInfo(
    val subwayId: String,
    val updnLine: String,
    val trainLineNm: String,
    val btrainSttus: String? = null,
    val arvlMsg2: String,
    val arvlMsg3: String,
    val statnNm: String,
    val arvlCd: String? = null,
    val lstcarAt: String? = null
)

fun ArrivalInfo.toInfo() : RealtimeArrivalInfo {
    return RealtimeArrivalInfo(
        searchStation = this.statnNm, // 조회한 역
        upAndDown = this.updnLine,
        destinationAndDirection = this.trainLineNm, // 종착역 과 방면
        trainKind = this.btrainSttus ?: "No Data", // 열차 종류
        beforeInfo =  this.arvlMsg2.split("(")[0], // 몇번째 전인지
        nowSubwayStationName = this.arvlMsg3, // 현재 지하철이 어디있는지
        arrivalCode = this.arvlCd?.toArrivalCode(this.statnNm) ?: "No Data", // 도착 코드
        isLast = this.lstcarAt.toBoolean() // 막차일때는 1 - true
    )
}