package com.team_bctoy.testrealtimesubway.api.data

data class RealtimePosition(
    val beginRow: Any,
    val curPage: Any,
    val directAt: String,
    val endRow: Any,
    val lastRecptnDt: String,
    val lstcarAt: String,
    val pageRow: Any,
    val recptnDt: String,
    val rowNum: Int,
    val selectedCount: Int,
    val statnId: String,
    val statnNm: String,
    val statnTid: String,
    val statnTnm: String,
    val subwayId: String,
    val subwayNm: String,
    val totalCount: Int,
    val trainNo: String,
    val trainSttus: String,
    val updnLine: String
)