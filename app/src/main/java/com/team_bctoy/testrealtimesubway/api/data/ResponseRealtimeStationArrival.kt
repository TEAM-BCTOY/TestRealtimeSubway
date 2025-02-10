package com.team_bctoy.testrealtimesubway.api.data

data class ResponseRealtimeStationArrival(
    val errorMessage: ErrorMessage,
    val realtimeArrivalList: List<RealtimeArrival>
)