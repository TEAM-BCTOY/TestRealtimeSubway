package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team_bctoy.testrealtimesubway.ui.data.Line7
import com.team_bctoy.testrealtimesubway.ui.data.LineData
import com.team_bctoy.testrealtimesubway.ui.data.Transfer
import com.team_bctoy.testrealtimesubway.ui.data.getColor

/**
 * Project : TestRealtimeSubway
 * File : ResponsiveLazyColumn
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 추후 기입
 *
 * Copyright @2025 UBIPLUS. All rights reserved
 */

@Composable
fun TrainLinePosition(lineData: LineData, isFirst: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(!isFirst) {
            Canvas(modifier = Modifier.size(width = 16.dp, height = 8.dp)) {
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2)
                )
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
            }
            Text(
                text = lineData.name
            )
        }
    }
}

@Composable
fun TransferCard(
    transfer: Transfer
) {
    Box(
        modifier = Modifier.size(24.dp),
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
            fontSize = if(transfer.alias.length == 1) 16.sp else 8.sp,
            lineHeight = 8.sp
        )
    }
}

@Preview
@Composable
fun PreviewTransferCard() {
    Row(
       horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TransferCard(Transfer.Line1)
        TransferCard(Transfer.Line4)
        TransferCard(Transfer.Line7)
        TransferCard(Transfer.LineSB)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewTrainLinePosition() {
    val mockData = Line7().list
    FlowRow(
        modifier = Modifier.fillMaxSize()
    ) {
        mockData.forEach { item ->
            TrainLinePosition(
                lineData = item,
                isFirst = mockData.indexOf(item) == 0
            )
        }
    }
}