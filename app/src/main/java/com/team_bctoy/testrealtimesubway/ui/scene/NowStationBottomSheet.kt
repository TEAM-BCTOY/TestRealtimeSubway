package com.team_bctoy.testrealtimesubway.ui.scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Project : TestRealtimeSubway
 * File : NowStationBottomSheet
 * Created by Gangglion on 2025-03-06
 *
 * Description:
 * - 노선도에서 역 클릭 시 나타나는 BottomSheet 정의
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowStationBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: ApiSelectorViewModel,
    beforeStation: String,
    nextStation: String,
    clickPosition: String
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    ModalBottomSheet(
        modifier = modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { viewModel.clearClickStation() }
    ) {
        Row(
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