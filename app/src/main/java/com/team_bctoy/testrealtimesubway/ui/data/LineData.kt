package com.team_bctoy.testrealtimesubway.ui.data

/**
 * Project : TestRealtimeSubway
 * File : LineData
 * Created by Gangglion on 2025-02-27
 *
 * Description:
 * - 호선 정보
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */

/**
 * 호선 정보
 * @property [name] 역명
 * @property [transfer] 환승 가능 호선
 */
data class LineData(
    val name: String,
    val transfer: List<Transfer>?
)
