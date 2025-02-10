package com.team_bctoy.testrealtimesubway.api.data

/**
 * 실시간 도착정보 Response Data
* @property [totalCount]
* @property [rowNum]
* @property [selectedCount]
* @property [subwayId] 지하철 호선 ID
* @property [updnLine] 상하행선 구분
* @property [trainLineNm] 도착지 방면
* @property [statnFid] 이전 지하철역 ID
* @property [statnTid] 다음 지하철역 ID
* @property [statnId] 지하철역 ID
* @property [statnNm] 지하철역명
* @property [trnsitCo] 환승 노선 수
* @property [ordkey] 도착예정열차 순번 - (상하행코드(1자리), 순번(첫번째, 두번째 열차 , 1자리), 첫번째 도착예정 정류장 - 현재 정류장(3자리), 목적지 정류장, 급행여부(1자리))
* @property [subwayList] 연계호선 ID
* @property [statnList] 연계 지하철역 ID
* @property [btrainSttus] 열차 종류 - 급행, ITX, 일반, 특급
* @property [barvlDt] 열차 도착 예정 시간(초)
* @property [btrainNo] 열차번호
* @property [bstatnId] 종착지하철역 ID
* @property [bstatnNm] 종착 지하철역명
* @property [recptnDt] 열차도착정보를 생성한 시각
* @property [arvlMsg2] 첫번째 도착 메시지(도착, 출발, 진입 등)
* @property [arvlMsg3] 두번째 도착 메시지
* @property [arvlCd] 도착 코드(0 : 진입, 1 : 도착, 2: 출발, 3: 전역 출발, 4: 전역 진입, 5: 전역 도착, 99: 운행중)
* @property [lstcarAt] 막차 여부
* @property [beginRow]
* @property [curPage]
* @property [endRow]
* @property [pageRow]
* @property [subwayHeading]
* @property [subwayNm]
* @property [trainCo]
 */
data class RealtimeArrival(
    val totalCount: Int,
    val rowNum: Int,
    val selectedCount: Int,
    val subwayId: String,
    val updnLine: String,
    val trainLineNm: String,
    val statnFid: String,
    val statnTid: String,
    val statnId: String,
    val statnNm: String,
    val trnsitCo: String,
    val ordkey: String,
    val subwayList: String,
    val statnList: String,
    val btrainSttus: String,
    val barvlDt: String,
    val btrainNo: String,
    val bstatnId: String,
    val bstatnNm: String,
    val recptnDt: String,
    val arvlMsg2: String,
    val arvlMsg3: String,
    val arvlCd: String,
    val lstcarAt: String,
    val beginRow: Any?,
    val curPage: Any?,
    val endRow: Any?,
    val pageRow: Any?,
    val subwayHeading: Any?,
    val subwayNm: Any?,
    val trainCo: Any?
)

data class RealtimeArrivalInfo(
    val searchStation: String,
    val destination: String,
    val trainKind: String,
    val beforeInfo: String,
    val nowSubwayStationName: String,
    val arrivalCode: String,
    val isLast: Boolean,
)

fun RealtimeArrival.toInfo() : RealtimeArrivalInfo {
    return RealtimeArrivalInfo(
        searchStation = this.statnNm, // 조회한 역
        destination = this.bstatnNm, // 종착역
        trainKind = this.btrainSttus, // 열차 종류
        beforeInfo =  this.arvlMsg2.split("(")[0], // 몇번째 전인지
        nowSubwayStationName = this.arvlMsg3, // 현재 지하철이 어디있는지
        arrivalCode = this.arvlCd.toArrivalCode(this.statnNm), // 도착 코드
        isLast = this.lstcarAt.toBoolean() // 막차일때는 1 - true
    )
}

fun String.toArrivalCode(searchStation: String) : String {
    return when(this) {
        "0" -> "$searchStation 역 진입중"
        "1" -> "$searchStation 역 도착"
        "2" -> "$searchStation 역 출발"
        "3" -> "전역 출발"
        "4" -> "전역 진입"
        "5" -> "전역 도착"
        "99" -> "운행중"
        else -> ""
    }
}