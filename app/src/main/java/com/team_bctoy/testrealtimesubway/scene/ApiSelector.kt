package com.team_bctoy.testrealtimesubway.scene

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team_bctoy.testrealtimesubway.api.data.toInfo
import com.team_bctoy.testrealtimesubway.api.data.toInfoString
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme
import kotlinx.coroutines.launch

@Composable
fun ApiSelector(
    modifier: Modifier = Modifier,
    sbHost: SnackbarHostState? = null
) {
    val apiViewModel: ApiSelectorViewModel = viewModel()
    val realtimeArrivalList by apiViewModel.realtimeArrival.collectAsState()
    val realtimePosition by apiViewModel.realtimePosition.collectAsState()
    val realtimeArrivalToStationName by apiViewModel.springRealtimeArrival.collectAsState()
    var inputSubwayName by remember { mutableStateOf("") }
    var inputSubwayLineName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

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
                text = "2. 서울시 지하철 실시간 도착정보 API(SpringBoot)\n\t - 역 기준 정보 제공",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "3. 서울시 지하철 실시간 열차 위치정보",
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(4.dp)
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
                                sbHost?.showSnackbar("서울시 API 에서 가져옴")
                            }
                            apiViewModel.callRealtimeSubwayArrival(inputSubwayName)
                        },
                        buttonText = "실시간\n도착정보",
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    DefaultButton(
                        onClick = {
                            scope.launch {
                                sbHost?.showSnackbar("테스트서버 API 에서 가져옴")
                            }
                            apiViewModel.callRealtimeArrivalToStationName(inputSubwayName)
                        },
                        buttonText = "실시간\n도착정보(spring)",
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
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
                        scope.launch {
                            sbHost?.showSnackbar("서울시 API 에서 가져옴")
                        }
                        apiViewModel.callRealtimePosition(inputSubwayLineName)
                    },
                    buttonText = "실시간 열차\n위치정보",
                )
            }
        }
        if(realtimeArrivalList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(realtimeArrivalList) { item ->
                    TrackingTrain(info = item.toInfo())
                }
            }
        } else if(realtimePosition.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(realtimePosition) { item ->
                    Text(
                        text = item.toInfoString()
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(realtimeArrivalToStationName) { item ->
                    TrackingTrain(info = item.toInfo())
                }
            }
        }
    }
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TrackingTrain(
    modifier: Modifier = Modifier,
    info: RealtimeArrivalInfo
) {
    with(info) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$destinationAndDirection $trainKind 열차",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = beforeInfo,
                        fontWeight = FontWeight.Bold
                    )
                    if(isLast) {
                        Text(
                            text = "막차입니다!!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Red,
                        )
                    }
                }
            }
            Text(
                text = arrivalCode,
                modifier = Modifier.padding(8.dp)
            )
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

@Preview(showBackground = true)
@Composable
fun PreviewTrackingTrainIsLast() {
    val mock = RealtimeArrivalInfo(
        searchStation = "부천",
        upAndDown = "상행",
        destinationAndDirection = "의정부 - 소사방면",
        trainKind = "일반",
        beforeInfo = "[3]번째 전역",
        nowSubwayStationName = "부개",
        arrivalCode = "운행중",
        isLast = true
    )
    TrackingTrain(info = mock)
}

@Preview(showBackground = true)
@Composable
fun PreviewTrackingTrainIsNotLast() {
    val mock = RealtimeArrivalInfo(
        searchStation = "부천",
        upAndDown = "상행",
        destinationAndDirection = "의정부 - 소사방면",
        trainKind = "일반",
        beforeInfo = "[3]번째 전역",
        nowSubwayStationName = "부개",
        arrivalCode = "운행중",
        isLast = false
    )
    TrackingTrain(info = mock)
}