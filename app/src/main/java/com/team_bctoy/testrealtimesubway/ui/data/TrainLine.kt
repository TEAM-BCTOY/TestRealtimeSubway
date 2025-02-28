package com.team_bctoy.testrealtimesubway.ui.data

import androidx.compose.ui.graphics.Color
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineAirport
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineGCenter
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineGChun
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineInC1
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineInC2
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineNAnsan
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineNSB
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineSB
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineU
import com.team_bctoy.testrealtimesubway.ui.data.TrainLine.LineWestSea
import com.team_bctoy.testrealtimesubway.ui.theme.Line1
import com.team_bctoy.testrealtimesubway.ui.theme.Line2
import com.team_bctoy.testrealtimesubway.ui.theme.Line3
import com.team_bctoy.testrealtimesubway.ui.theme.Line4
import com.team_bctoy.testrealtimesubway.ui.theme.Line5
import com.team_bctoy.testrealtimesubway.ui.theme.Line6
import com.team_bctoy.testrealtimesubway.ui.theme.Line7
import com.team_bctoy.testrealtimesubway.ui.theme.Line8
import com.team_bctoy.testrealtimesubway.ui.theme.Line9
import com.team_bctoy.testrealtimesubway.ui.theme.LineAirport
import com.team_bctoy.testrealtimesubway.ui.theme.LineGCenter
import com.team_bctoy.testrealtimesubway.ui.theme.LineGChun
import com.team_bctoy.testrealtimesubway.ui.theme.LineInC1
import com.team_bctoy.testrealtimesubway.ui.theme.LineInC2
import com.team_bctoy.testrealtimesubway.ui.theme.LineNAnsan
import com.team_bctoy.testrealtimesubway.ui.theme.LineNSB
import com.team_bctoy.testrealtimesubway.ui.theme.LineSB
import com.team_bctoy.testrealtimesubway.ui.theme.LineSinlim
import com.team_bctoy.testrealtimesubway.ui.theme.LineU
import com.team_bctoy.testrealtimesubway.ui.theme.LineWestSea

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
enum class TrainLine(val alias: String) {
    Line1("1"), Line2("2"), Line3("3"), Line4("4"), Line5("5"), Line6("6"), Line7("7"), Line8("8"), Line9("9"),
    LineU("U"), LineSB("수인\n분당"), LineNSB("신분당"), LineSinlim("신림"), LineNAnsan("신안산"), LineInC1("인천1"), LineInC2("인천2"), LineAirport("공항"),
    LineWestSea("서해"), LineGCenter("경의\n중앙"), LineGChun("경춘")
}

fun TrainLine.getColor(): Color {
     return when(this) {
        TrainLine.Line1 -> { Line1 }
        TrainLine.Line2 -> { Line2 }
        TrainLine.Line3 -> { Line3 }
        TrainLine.Line4 -> { Line4 }
        TrainLine.Line5 -> { Line5 }
        TrainLine.Line6 -> { Line6 }
        TrainLine.Line7 -> { Line7 }
        TrainLine.Line8 -> { Line8 }
        TrainLine.Line9 -> { Line9 }
        TrainLine.LineU -> { LineU }
        TrainLine.LineSB -> { LineSB }
        TrainLine.LineNSB -> { LineNSB }
        TrainLine.LineSinlim -> { LineSinlim }
        TrainLine.LineNAnsan -> { LineNAnsan }
        TrainLine.LineInC1 -> { LineInC1 }
        TrainLine.LineInC2 -> { LineInC2 }
        TrainLine.LineAirport -> { LineAirport }
        TrainLine.LineWestSea -> { LineWestSea }
        TrainLine.LineGCenter -> { LineGCenter }
        TrainLine.LineGChun -> { LineGChun }
    }
}
