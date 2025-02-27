package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.team_bctoy.testrealtimesubway.ui.data.Line7
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ApiSelector(
    modifier: Modifier = Modifier,
    sbHost: SnackbarHostState? = null
) {
    val apiViewModel: ApiSelectorViewModel = viewModel()
    val realtimeArrivalList by apiViewModel.realtimeArrival.collectAsState()
    val realtimePosition by apiViewModel.realtimePosition.collectAsState()
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
                        val snackbarText = if(inputSubwayLineName.contains("7")) "7호선 테스트 화면" else "다른호선은 텍스트로만 보입니다."
                        scope.launch {
                            sbHost?.showSnackbar(snackbarText)
                        }
                        apiViewModel.callRealtimePosition(inputSubwayLineName)
                    },
                    buttonText = "실시간 열차\n위치정보",
                )
            }
        }
        if (realtimeArrivalList.isNotEmpty()) {
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
            if (inputSubwayLineName.contains("7")) {
                FlowRow(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Line7().list.forEach { item ->
                        TrainLinePosition(
                            lineData = item,
                            isFirst = Line7().list.indexOf(item) == 0
                        )
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
                            text = item.toString()
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