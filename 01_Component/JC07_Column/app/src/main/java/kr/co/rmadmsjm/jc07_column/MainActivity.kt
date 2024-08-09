package kr.co.rmadmsjm.jc07_column

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc07_column.ui.theme.JC07_ColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC07_ColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColumnEx()
                }
            }
        }
    }
}

@Composable
fun ColumnEx() {
    Column {
        Column(
            modifier = Modifier
                .size(100.dp)
                .border(width = 1.dp, color = Color.Blue)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // 1. horizontalAlignment 적용
        // ⭐ Alignment: 레이아웃의 진행 방향과 수직으로 적용
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(100.dp)
                .border(width = 1.dp, color = Color.Red)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // 2. verticalArrangement 적용
        // SpaceAround, SpaceEvenly, SpaceBetween
        // ⭐ Arrangement: 레이아웃의 진행 방향으로 적용
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .size(100.dp)
                .border(width = 1.dp, color = Color.Green)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // 3. Text에 Modifier.align 적용
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .size(100.dp)
                .border(width = 1.dp, color = Color.Gray)
        ) {
            Text(
                text = "첫 번째",
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = "두 번째",
                modifier = Modifier.align(Alignment.Start)
            )
            Text(text = "세 번째")
        }

        // Row: verticalAlignment, horizontalArrangement
        // Coulum: horizontalAlignment, verticalArrangement
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .size(200.dp, 100.dp)
                .border(width = 1.dp, color = Color.Yellow)
        ) {
            Text(
                text = "첫 번째",
                modifier = Modifier.align(Alignment.Top)
            )
            Text(
                text = "두 번째",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(text = "세 번째")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC07_ColumnTheme {
        ColumnEx()
    }
}