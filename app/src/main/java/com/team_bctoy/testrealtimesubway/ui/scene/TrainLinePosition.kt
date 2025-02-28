package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team_bctoy.testrealtimesubway.R
import com.team_bctoy.testrealtimesubway.api.data.PositionInfo
import com.team_bctoy.testrealtimesubway.api.data.arrive
import com.team_bctoy.testrealtimesubway.api.data.beforeDeparture
import com.team_bctoy.testrealtimesubway.api.data.departure
import com.team_bctoy.testrealtimesubway.api.data.enter
import com.team_bctoy.testrealtimesubway.ui.data.LineData
import com.team_bctoy.testrealtimesubway.ui.data.LineList
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine
import com.team_bctoy.testrealtimesubway.ui.data.getColor
import com.team_bctoy.testrealtimesubway.ui.data.mock7

/**
 * Project : TestRealtimeSubway
 * File : TrainLinePosition
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 실시간 열차정보 화면
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */

@Composable
fun TrainLinePosition(lineData: LineData, isFirst: Boolean, isLeft: Boolean) {
    Column(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!isFirst) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(16.dp)
                        .padding(start = 4.dp)
                        .drawBehind {
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height / 3),
                                end = Offset(size.width, size.height / 3),
                                strokeWidth = 4f
                            )
                        }
                ) {
                    if(lineData.positionInfo?.trainSttus == enter || lineData.positionInfo?.trainSttus == beforeDeparture) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_train),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 2.dp)
                                .graphicsLayer(scaleX = if(isLeft) 1f else -1f)
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(lineData.transfer != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        lineData.transfer.forEach {
                            TransferCard(it)
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.size(16.dp))
                }
                Text(
                    text = lineData.name
                )
                if(lineData.positionInfo?.trainSttus == arrive || lineData.positionInfo?.trainSttus == departure) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_train),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                            .graphicsLayer(scaleX = if(isLeft) 1f else -1f)
                    )
                } else {
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
fun TransferCard(
    transfer: TrainLine
) {
    Box(
        modifier = Modifier.size(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = transfer.getColor(),
                radius = size.minDimension / 2,
                center = center
            )
        }
        Text(
            text = transfer.alias,
            color = Color.White,
            fontSize = if(transfer.alias.length == 1) 12.sp else 5.sp,
            lineHeight = 6.sp
        )
    }
}

@Preview
@Composable
fun PreviewTransferCard() {
    Row(
       horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TransferCard(TrainLine.Line1)
        TransferCard(TrainLine.Line4)
        TransferCard(TrainLine.Line7)
        TransferCard(TrainLine.LineSB)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewTrainLinePosition() {
    val mockData = LineList().mock7()
    val mockPositionInfo = listOf(
        PositionInfo(
            subwayNm = "7호선",
            statnNm = "수락산",
            statnTnm = "장암",
            trainSttus = "1",
            lstcarAt = "0",
            directAt = "",
            recptnDt = "2025-02-28 13:43:57"
        ),
        PositionInfo(
            subwayNm = "7호선",
            statnNm = "어린이대공원(세종대)",
            statnTnm = "온수",
            trainSttus = "0",
            lstcarAt = "0",
            directAt = "",
            recptnDt = "2025-02-28 13:43:57"
        )
    )
    FlowRow(
        modifier = Modifier.fillMaxSize()
    ) {
        mockData.forEachIndexed { idx, item ->
            val collectPosInfo = mockPositionInfo.find { it.statnNm == item.name }
            val isFirst = idx == 0
            val destinationIdx = mockData.indexOf(mockData.find { it.name == collectPosInfo?.statnTnm })
            val nowPosIdx = mockData.indexOf(mockData.find { it.name == collectPosInfo?.statnNm })
            val isLeft = destinationIdx < nowPosIdx
            item.positionInfo = collectPosInfo
            TrainLinePosition(item, isFirst, isLeft)
        }
    }
}