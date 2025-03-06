package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/**
 * Project : TestRealtimeSubway
 * File : NowStationDialog
 * Created by Gangglion on 2025-03-06
 *
 * Description:
 * - 노선도에서 역 클릭 시 나타나는 Dialog 정의
 *
 * Copyright @2025 TEAM_BCTOY. All rights reserved
 */

@Composable
fun NowStationDialog(
    modifier: Modifier = Modifier,
    viewModel: ApiSelectorViewModel? = null,
    beforeStation: String,
    nextStation: String,
    clickPosition: String
) {
    Dialog(onDismissRequest = { viewModel?.clearClickStation() }) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = beforeStation,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = clickPosition,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = nextStation,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNowStationDialog() {
    NowStationDialog(
        beforeStation = "전역", 
        nextStation = "다음역", 
        clickPosition = "선택역",
    )
}