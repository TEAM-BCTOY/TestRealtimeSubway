package com.team_bctoy.testrealtimesubway.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team_bctoy.testrealtimesubway.api.NetworkClient
import com.team_bctoy.testrealtimesubway.api.data.RealtimeArrival
import com.team_bctoy.testrealtimesubway.api.data.RealtimePosition
import com.team_bctoy.testrealtimesubway.api.data.ResponseRealtimePosition
import com.team_bctoy.testrealtimesubway.api.data.ResponseRealtimeStationArrival
import com.team_bctoy.testrealtimesubway.utils.LogUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiSelectorViewModel : ViewModel() {
    private val _realtimeArrivalList = MutableStateFlow<List<RealtimeArrival>>(emptyList())
    val realtimeArrival: StateFlow<List<RealtimeArrival>> get() = _realtimeArrivalList

    private val _realtimePositionList = MutableStateFlow<List<RealtimePosition>>(emptyList())
    val realtimePosition: StateFlow<List<RealtimePosition>> get() = _realtimePositionList

    // 지하철 실시간 도착정보 API 호출
    fun callRealtimeSubwayArrival() {
        _realtimeArrivalList.value = emptyList()
        _realtimePositionList.value = emptyList()
        NetworkClient.getApiInstance().getRealtimeSubwayArrivalInfo(0, 10, "부천").enqueue(object : Callback<ResponseRealtimeStationArrival> {
            override fun onResponse(
                call: Call<ResponseRealtimeStationArrival>,
                response: Response<ResponseRealtimeStationArrival>
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

            override fun onFailure(call: Call<ResponseRealtimeStationArrival>, tr: Throwable) {
                call.cancel()
                LogUtil.e("Error :: ", tr)
            }
        })
    }

    // 실시간 열차 위치정보 API 호출
    fun callRealtimePosition() {
        _realtimeArrivalList.value = emptyList()
        _realtimePositionList.value = emptyList()
        NetworkClient.getApiInstance().getRealtimePositionInfo(0, 10, "7호선").enqueue(object : Callback<ResponseRealtimePosition> {
            override fun onResponse(
                call: Call<ResponseRealtimePosition>,
                response: Response<ResponseRealtimePosition>
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

            override fun onFailure(call: Call<ResponseRealtimePosition>, tr: Throwable) {
                call.cancel()
                LogUtil.e("Error :: ", tr)
            }
        })
    }

    // 지하철 실시간 도착정보 일괄 API 호출
    fun callRealtimeArrivalAll() {
        _realtimeArrivalList.value = emptyList()
        _realtimePositionList.value = emptyList()
        NetworkClient.getApiInstance().getRealtimePositionAllInfo().enqueue(object : Callback<ResponseRealtimeStationArrival> {
            override fun onResponse(
                call: Call<ResponseRealtimeStationArrival>,
                response: Response<ResponseRealtimeStationArrival>
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

            override fun onFailure(call: Call<ResponseRealtimeStationArrival>, tr: Throwable) {
                call.cancel()
                LogUtil.e("Error :: ", tr)
            }
        })
    }
}