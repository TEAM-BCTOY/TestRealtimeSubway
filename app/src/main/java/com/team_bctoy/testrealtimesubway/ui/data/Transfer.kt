package com.team_bctoy.testrealtimesubway.ui.data

import androidx.compose.ui.graphics.Color
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineAirport
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineGCenter
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineGChun
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineInC1
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineInC2
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineNAnsan
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineNSB
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineSB
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineU
import com.team_bctoy.testrealtimesubway.ui.data.Transfer.LineWestSea

/**
 * Project : TestRealtimeSubway
 * File : Transfer
 * Created by Ganggion on 2025-02-27
 *
 * Description:
 * - 전철 호선 Enum
 *
 * Copyright @2025 TEAM-BCTOY. All rights reserved
 */

/**
 * 전철 호선 Enum
 * @property [LineU] 의정부 경전철
 * @property [LineSB] 수인분당선
 * @property [LineNSB] 신분당선
 * @property [LineSillim] 신림선
 * @property [LineNAnsan] 신안산선
 * @property [LineInC1] 인천1호선
 * @property [LineInC2] 인천2호선
 * @property [LineAirport] 공항철도
 * @property [LineWestSea] 서해선
 * @property [LineGCenter] 경의중앙선
 * @property [LineGChun] 경춘선
 */
enum class Transfer(val alias: String) {
    Line1("1"), Line2("2"), Line3("3"), Line4("4"), Line5("5"), Line6("6"), Line7("7"), Line8("8"), Line9("9"),
    LineU("U"), LineSB("수인\n분당"), LineNSB("신분당"), LineSinlim("신림"), LineNAnsan("신안산"), LineInC1("인천1"), LineInC2("인천2"), LineAirport("공항"),
    LineWestSea("서해"), LineGCenter("경의\n중앙"), LineGChun("경춘")
}

fun Transfer.getColor(): Color {
     return when(this) {
        Transfer.Line1 -> { Color(0xFF0052A4) }
        Transfer.Line2 -> { Color(0xFF00A84D) }
        Transfer.Line3 -> { Color(0xFFEF7C1C) }
        Transfer.Line4 -> { Color(0xFF00A5DE) }
        Transfer.Line5 -> { Color(0xFF996CAC) }
        Transfer.Line6 -> { Color(0xFFCD7C2F) }
        Transfer.Line7 -> { Color(0xFF747F00) }
        Transfer.Line8 -> { Color(0xFFE6186C) }
        Transfer.Line9 -> { Color(0xFFBDB092) }
        Transfer.LineU -> { Color(0xFFFDA600) }
        Transfer.LineSB -> { Color(0xFFF5A200) }
        Transfer.LineNSB -> { Color(0xFFD4003B) }
        Transfer.LineSinlim -> { Color(0xFF6789CA) }
        Transfer.LineNAnsan -> { Color(0xFFF04938) }
        Transfer.LineInC1 -> { Color(0xFF7CA8D5) }
        Transfer.LineInC2 -> { Color(0xFFED8B00) }
        Transfer.LineAirport -> { Color(0xFF0065B3) }
        Transfer.LineWestSea -> { Color(0xFF81A914) }
        Transfer.LineGCenter -> { Color(0xFF77C4A3) }
        Transfer.LineGChun -> { Color(0xFF0C8E72) }
    }
}
