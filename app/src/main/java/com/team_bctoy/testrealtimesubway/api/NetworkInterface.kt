package com.team_bctoy.testrealtimesubway.api

import com.team_bctoy.testrealtimesubway.api.data.ResponseRealtimePosition
import com.team_bctoy.testrealtimesubway.api.data.ResponseRealtimeStationArrival
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkInterface {

    // 지하철 실시간 도착정보
    @GET("json/realtimeStationArrival/{startIdx}/{endIdx}/{stateNNm}")
    fun getRealtimeSubwayArrivalInfo(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("stateNNm") stateNNm: String
    ) : Call<ResponseRealtimeStationArrival>

    // 실시간 열차 위치정보
    @GET("json/realtimePosition/{startIdx}/{endIdx}/{lineNum}")
    fun getRealtimePositionInfo(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("lineNum") lineNum: String
    ) : Call<ResponseRealtimePosition>

    // 지하철 실시간 도착정보(일괄)
    @GET("json/realtimeStationArrival/ALL")
    fun getRealtimePositionAllInfo() : Call<ResponseRealtimeStationArrival>
}