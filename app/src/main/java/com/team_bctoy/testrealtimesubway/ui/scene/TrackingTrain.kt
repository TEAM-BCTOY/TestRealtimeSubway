package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team_bctoy.testrealtimesubway.ui.data.RealtimeArrivalInfo

/**
 * Project : TestRealtimeSubway
 * File : TrackingTrain
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 실시간 열차 정보 - 역 기준 열자 화면
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */
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