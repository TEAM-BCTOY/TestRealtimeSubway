package com.team_bctoy.testrealtimesubway.api

import com.team_bctoy.testrealtimesubway.api.data.RealtimeStationArrival
import com.team_bctoy.testrealtimesubway.api.data.RealtimeStationPosition
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkInterface {
    // 지하철 실시간 도착정보(역이름)
    @GET("arrival/{stationName}")
    fun getRealtimeSubwayArrivalInfo(
        @Path("stationName") stationName: String
    ) : Call<RealtimeStationArrival>

    // 지하철 열차 위치정보
    @GET("position/{lineName}")
    fun getRealtimeSubwayPositionInfo(
        @Path("lineName") lineName: String
    ) : Call<RealtimeStationPosition>
}