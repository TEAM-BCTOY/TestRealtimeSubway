package com.team_bctoy.testrealtimesubway

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team_bctoy.testrealtimesubway.ui.theme.TestRealtimeSubwayTheme

@Composable
fun ApiSelector(
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "테스트하고자 하는 API 를 선택해주세요",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "테스트하고자 하는 API 를 선택해주세요",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "테스트하고자 하는 API 를 선택해주세요",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            DefaultButton(
                onClick = {

                },
                buttonText = "버튼 1"
            )
            DefaultButton(
                onClick = {

                },
                buttonText = "버튼 2"
            )
            DefaultButton(
                onClick = {

                },
                buttonText = "버튼 3"
            )
        }

        ResultText(
            text = "결과가 나타날 영역",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick
    ) {
        Text(
            text = buttonText
        )
    }
}

@Composable
fun ResultText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(modifier = modifier, text = text, textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun PreviewApiSelector() {
    TestRealtimeSubwayTheme {
        ApiSelector()
    }
}