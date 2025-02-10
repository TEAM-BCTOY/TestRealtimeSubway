package com.team_bctoy.testrealtimesubway.api.data

data class ResponseRealtimePosition(
    val errorMessage: ErrorMessage,
    val realtimePositionList: List<RealtimePosition>
)