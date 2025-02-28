package com.team_bctoy.testrealtimesubway.ui.data

import com.team_bctoy.testrealtimesubway.api.data.PositionInfo

/**
 * Project : TestRealtimeSubway
 * File : Line8
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 호선별 역 리스트
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */

/**
 * 7호선 리스트
 */
data class LineList(
    var list: List<LineData> = emptyList()
)

fun LineList.mock7() : List<LineData> = listOf(
    LineData(name = "장암", transfer = null),
    LineData(name = "도봉산", transfer = listOf(TrainLine.Line1)),
    LineData(name = "수락산", transfer = null),
    LineData(name = "마들", transfer = null),
    LineData(name = "노원", transfer = listOf(TrainLine.Line4)),
    LineData(name = "중계", transfer = null),
    LineData(name = "하계", transfer = null),
    LineData(name = "공릉(서울산업대입구)", transfer = null),
    LineData(name = "태릉입구", transfer = listOf(TrainLine.Line6)),
    LineData(name = "먹골", transfer = null),
    LineData(name = "중화", transfer = null),
    LineData(name = "상봉", transfer = listOf(TrainLine.LineGCenter, TrainLine.LineGChun)),
    LineData(name = "면목", transfer = null),
    LineData(name = "사가정", transfer = null),
    LineData(name = "용마산", transfer = null),
    LineData(name = "중곡", transfer = null),
    LineData(name = "군자(능동)", transfer = listOf(TrainLine.Line5)),
    LineData(name = "어린이대공원(세종대)", transfer = null),
    LineData(name = "건대입구", transfer = listOf(TrainLine.Line2)),
    LineData(name = "뚝섬유원지", transfer = null),
    LineData(name = "청담", transfer = null),
    LineData(name = "강남구청", transfer = listOf(TrainLine.LineSB)),
    LineData(name = "학동", transfer = null),
    LineData(name = "논현", transfer = listOf(TrainLine.LineNSB)),
    LineData(name = "반포", transfer = null),
    LineData(name = "고속터미널", transfer = listOf(TrainLine.Line3, TrainLine.Line9)),
    LineData(name = "내방", transfer = null),
    LineData(name = "이수", transfer = listOf(TrainLine.Line4)),
    LineData(name = "남성", transfer = null),
    LineData(name = "숭실대입구(살피재)", transfer = null),
    LineData(name = "상도", transfer = null),
    LineData(name = "장승배기", transfer = null),
    LineData(name = "신대방삼거리", transfer = null),
    LineData(name = "보라매", transfer = listOf(TrainLine.LineSinlim)),
    LineData(name = "신풍", transfer = null),
    LineData(name = "대림", transfer = listOf(TrainLine.Line2)),
    LineData(name = "남구로", transfer = null),
    LineData(name = "가산디지털단지", transfer = listOf(TrainLine.Line1)),
    LineData(name = "철산", transfer = null),
    LineData(name = "광명사거리", transfer = null),
    LineData(name = "천왕", transfer = null),
    LineData(name = "온수", transfer = listOf(TrainLine.Line1)),
    LineData(name = "까치울", transfer = null),
    LineData(name = "부천종합운동장", transfer = listOf(TrainLine.LineWestSea)),
    LineData(name = "춘의", transfer = null),
    LineData(name = "신중동", transfer = null),
    LineData(name = "부천시청", transfer = listOf(TrainLine.LineInC1)),
    LineData(name = "상동", transfer = null),
    LineData(name = "삼산체육관", transfer = null),
    LineData(name = "굴포천", transfer = null),
    LineData(name = "부평구청", transfer = null),
    LineData(name = "산곡", transfer = null),
    LineData(name = "석남", transfer = listOf(TrainLine.LineInC2)),
)

/**
 * 호선 정보
 * @property [name] 역명
 * @property [transfer] 환승 가능 호선
 * @property [stationName] 현재 지하철역명
 * @property [destinationName] 종착지하철역명
 * @property [posStatus] 열차 상태 구분
 * @property [expressStatus] 급행 여부
 * @property [isLast] 막차 여부 - true : 막차
 */
data class LineData(
    val name: String,
    val transfer: List<TrainLine>?,
    var positionInfo: PositionInfo? = null
)
