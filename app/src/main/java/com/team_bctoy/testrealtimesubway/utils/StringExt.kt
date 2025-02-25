package com.team_bctoy.testrealtimesubway.utils

fun String.toArrivalCode(searchStation: String) : String {
    return when(this) {
        "0" -> "$searchStation 역 진입중"
        "1" -> "$searchStation 역 도착"
        "2" -> "$searchStation 역 출발"
        "3" -> "전역 출발"
        "4" -> "전역 진입"
        "5" -> "전역 도착"
        "99" -> "운행중"
        else -> throw IllegalArgumentException("열차 코드를 확인해주세요")
    }
}