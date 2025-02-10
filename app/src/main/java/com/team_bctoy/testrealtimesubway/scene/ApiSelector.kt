package com.team_bctoy.testrealtimesubway.scene

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team_bctoy.testrealtimesubway.api.data.RealtimeArrivalInfo
import com.team_bctoy.testrealtimesubway.api.data.toInfo
import com.team_bctoy.testrealtimesubway.api.data.toInfoString
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme

@Composable
fun ApiSelector(
    modifier: Modifier = Modifier
) {
    val apiViewModel: ApiSelectorViewModel = viewModel()
    val realtimeArrivalList by apiViewModel.realtimeArrival.collectAsState()
    val realtimePosition by apiViewModel.realtimePosition.collectAsState()
    val resultTextScroll = rememberScrollState(0)

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
                text = "1. 서울시 지하철 실시간 도착정보 API\n    - 역 기준 정보 제공",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "2. 서울시 지하철 실시간 열차 위치정보",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "3. 서울시 지하철 실시간 도착정보(일괄) API\n    - 전체 역에 대한 정보 제공",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "- 테스트 결과, 조회시간이 오래걸려\n사용하지 않을 듯 보임",
                textDecoration = TextDecoration.Underline,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultButton(
                onClick = {
                    apiViewModel.callRealtimeSubwayArrival()
                },
                buttonText = "실시간\n도착정보",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            DefaultButton(
                onClick = {
                    apiViewModel.callRealtimePosition()
                },
                buttonText = "실시간 열차\n위치정보",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            DefaultButton(
                onClick = {
                    apiViewModel.callRealtimeArrivalAll()
                },
                buttonText = "실시간\n도착정보\n(일괄)",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
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
        } else {
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
                text = "$destination 행 $trainKind 열차",
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
        destination = "의정부",
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
        destination = "의정부",
        trainKind = "일반",
        beforeInfo = "[3]번째 전역",
        nowSubwayStationName = "부개",
        arrivalCode = "운행중",
        isLast = false
    )
    TrackingTrain(info = mock)
}