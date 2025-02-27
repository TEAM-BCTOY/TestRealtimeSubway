package com.team_bctoy.testrealtimesubway.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Project : TestRealtimeSubway
 * File : DefaultButton
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 기본 버튼
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */

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