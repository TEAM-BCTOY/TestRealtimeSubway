package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team_bctoy.testrealtimesubway.api.NetworkClient
import com.team_bctoy.testrealtimesubway.api.data.ArrivalInfo
import com.team_bctoy.testrealtimesubway.api.data.PositionInfo
import com.team_bctoy.testrealtimesubway.api.data.RealtimeStationArrival
import com.team_bctoy.testrealtimesubway.api.data.RealtimeStationPosition
import com.team_bctoy.testrealtimesubway.utils.LogUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiSelectorViewModel : ViewModel() {
    private val _realtimeArrivalList = MutableStateFlow<List<ArrivalInfo>>(emptyList())
    val realtimeArrival: StateFlow<List<ArrivalInfo>> get() = _realtimeArrivalList

    private val _realtimePositionList = MutableStateFlow<List<PositionInfo>>(emptyList())
    val realtimePosition: StateFlow<List<PositionInfo>> get() = _realtimePositionList

    /**
     * 지하철 실시간 도착정보(역이름 조회) API 호출
     */
    fun callRealtimeSubwayArrival(stationName: String) {
        _realtimeArrivalList.value = emptyList()
        _realtimePositionList.value = emptyList()
        NetworkClient.getApiInstance().getRealtimeSubwayArrivalInfo(stationName).enqueue(object : Callback<RealtimeStationArrival> {
            override fun onResponse(
                call: Call<RealtimeStationArrival>,
                response: Response<RealtimeStationArrival>
            ) {
                if(response.isSuccessful) {
                    val arrivals = response.body()?.realtimeArrivalList ?: emptyList()
                    viewModelScope.launch {
                        _realtimeArrivalList.value = arrivals
                    }
                } else {
                    viewModelScope.launch {
                        _realtimeArrivalList.value = emptyList()
                    }
                }
            }

            override fun onFailure(call: Call<RealtimeStationArrival>, tr: Throwable) {
                call.cancel()
                LogUtil.e("Error :: ", tr)
            }
        })
    }

    // 실시간 열차 위치정보 API 호출
    fun callRealtimePosition(subwayLineName: String) {
        _realtimeArrivalList.value = emptyList()
        _realtimePositionList.value = emptyList()
        val request = if(subwayLineName.contains("호선")) subwayLineName else "${subwayLineName}호선"
        NetworkClient.getApiInstance().getRealtimeSubwayPositionInfo(request).enqueue(object : Callback<RealtimeStationPosition> {
            override fun onResponse(
                call: Call<RealtimeStationPosition>,
                response: Response<RealtimeStationPosition>
            ) {
                if(response.isSuccessful) {
                    val positions = response.body()?.realtimePositionList ?: emptyList()
                    viewModelScope.launch {
                        _realtimePositionList.value = positions
                    }
                } else {
                    viewModelScope.launch {
                        _realtimePositionList.value = emptyList()
                    }
                }
            }

            override fun onFailure(call: Call<RealtimeStationPosition>, tr: Throwable) {
                call.cancel()
                LogUtil.e("Error :: ", tr)
            }
        })
    }
}