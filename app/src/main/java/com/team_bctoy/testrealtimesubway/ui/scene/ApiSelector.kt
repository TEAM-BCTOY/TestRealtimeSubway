package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team_bctoy.testrealtimesubway.api.data.toInfo
import com.team_bctoy.testrealtimesubway.ui.component.DefaultButton
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme
import com.team_bctoy.testrealtimesubway.utils.toSubwayLine
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ApiSelector(
    modifier: Modifier = Modifier,
    sbHost: SnackbarHostState? = null
) {
    val apiViewModel: ApiSelectorViewModel = viewModel()
    val realtimeArrivalList by apiViewModel.realtimeArrival.collectAsState()
    val realtimePositionList by apiViewModel.realtimePosition.collectAsState()
    var inputSubwayName by remember { mutableStateOf("") }
    var inputSubwayLineName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val clickPosition by apiViewModel.clickStation.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "테스트하고자 하는 API 를 선택해주세요",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Column(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Text(
                text = "1. 서울시 지하철 실시간 도착정보 API\n\t- 역 기준 정보 제공",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "2. 서울시 지하철 실시간 열차 위치정보\n\t- 호선 기준 정보 제공",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = inputSubwayName,
                    onValueChange = {
                        inputSubwayName = it
                    },
                    label = {
                        Text(text = "역이름 입력")
                    },
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DefaultButton(
                        onClick = {
                            scope.launch {
                                sbHost?.showSnackbar("테스트서버 API 에서 가져옴")
                            }
                            apiViewModel.callRealtimeSubwayArrival(inputSubwayName)
                        },
                        buttonText = "실시간\n도착정보",
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = inputSubwayLineName,
                    onValueChange = {
                        inputSubwayLineName = it
                    },
                    label = {
                        Text(text = "호선 입력")
                    },
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                DefaultButton(
                    onClick = {
                        val snackbarText = if(inputSubwayLineName.contains("7"))
                            "7호선 테스트 화면"
                        else if(inputSubwayLineName.contains("8"))
                            "8호선 테스트 화면"
                        else
                            "다른호선은 텍스트로만 보입니다."
                        scope.launch {
                            sbHost?.showSnackbar(snackbarText)
                        }
                        apiViewModel.callRealtimePosition(inputSubwayLineName)
                    },
                    buttonText = "실시간 열차\n위치정보",
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            if (realtimeArrivalList.isNotEmpty()) {
                LazyColumn() {
                    items(realtimeArrivalList) { item ->
                        TrackingTrain(info = item.toInfo())
                    }
                }
            } else if(realtimePositionList.isNotEmpty()) {
                val isMock = realtimePositionList[0].subwayNm.toSubwayLine() == null
                if(isMock) { // 비어있다면, 텍스트만 그리기
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(realtimePositionList) { item ->
                            Text(
                                text = item.toString()
                            )
                        }
                    }
                } else { // 비어있지 않다면, 목업용 7호선 노선도 그리기
                    val stationList = realtimePositionList[0].subwayNm.toSubwayLine()
                    FlowRow {
                        stationList!!.forEachIndexed { idx, station ->
                            val item = realtimePositionList.find { it.statnNm == station.name }
                            val isFirst = idx == 0
                            val destinationIdx = stationList.indexOf(stationList.find { it.name == item?.statnTnm })
                            val nowPosIdx = stationList.indexOf(stationList.find { it.name == item?.statnNm })
                            val isLeft = destinationIdx < nowPosIdx
                            station.positionInfo = item
                            TrainLinePosition(
                                lineData = station,
                                isFirst = isFirst,
                                isLeft = isLeft,
                                viewModel = apiViewModel
                            )
                        }
                    }

                    if(clickPosition != "" && realtimePositionList[0].subwayNm.contains("7")) {
                        // 선택한 역 index
                        val currentIndex = stationList!!.indexOf(stationList.find{ it.name == clickPosition })
                        val beforeStation = if (currentIndex != 0) stationList[currentIndex - 1].name else ""
                        val nextStation = if(currentIndex != stationList.size - 1) stationList[currentIndex + 1].name else ""

                        NowStationBottomSheet(
                            viewModel = apiViewModel,
                            beforeStation = beforeStation,
                            nextStation = nextStation,
                            clickPosition = clickPosition
                        )
                    } else if(clickPosition != "" && realtimePositionList[0].subwayNm.contains("8")){
                        // 선택한 역 index
                        val currentIndex = stationList!!.indexOf(stationList.find{ it.name == clickPosition })
                        val beforeStation = if (currentIndex != 0) stationList[currentIndex - 1].name else ""
                        val nextStation = if(currentIndex != stationList.size - 1) stationList[currentIndex + 1].name else ""

                        NowStationDialog(
                            viewModel = apiViewModel,
                            beforeStation = beforeStation,
                            nextStation = nextStation,
                            clickPosition = clickPosition
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApiSelector() {
    TestRealtimeSubwayTheme {
        ApiSelector()
    }
}