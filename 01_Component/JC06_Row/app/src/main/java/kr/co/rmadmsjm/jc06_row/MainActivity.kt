package kr.co.rmadmsjm.jc06_row

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc06_row.ui.theme.JC06_RowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC06_RowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RowEx()
                }
            }
        }
    }
}

@Composable
fun RowEx() {
    Column {
        Row(
            modifier = Modifier
                .height(40.dp)
                .border(width = 1.dp, color = Color.Red)
        ) {
            Text(text = "첫 번째!")
            Text(text = "두 번째!")
            Text(text = "세 번째!")
        }

        // 1. Text에 align 설정
        // Alignment.Top, CenterVertically, Bottom
        // Row: 가로로 배치
        // align: 수직 방향으로 배치
        Row(
            modifier = Modifier
                .height(40.dp)
                .border(width = 1.dp, color = Color.Blue)
        ) {
            Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "두 번째!", modifier = Modifier.align(Alignment.CenterVertically))
            Text(text = "세 번째!", modifier = Modifier.align(Alignment.Bottom))
        }

        // 2. Row에 verticalAlignment 설정
        // Text에 align을 사용할 때와 쓰이는 값이 같음
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .height(40.dp)
                .border(width = 1.dp, color = Color.Green)
        ) {
            Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "두 번째!")
            Text(text = "세 번째!")
        }

        // 3. Row width를 200dp로 설정
        // Row에 horizontalArrangement에 Arrangement.Center를 설정
        // Start, End, SpaceAround, SpaceBetween, SpaceEvenly
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(width = 200.dp, height = 40.dp)
                .border(width = 1.dp, color = Color.Yellow)
        ) {
            Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "두 번째!")
            Text(text = "세 번째!")
        }

        // 4. horizontalArrangement를 제거, 각 Text에 Modifier.weight 설정
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .size(width = 200.dp, height = 40.dp)
                .border(width = 1.dp, color = Color.Magenta)
        ) {
            Text(
                text = "첫 번째!",
                modifier = Modifier
                    .align(Alignment.Top)
                    .weight(3f)
            )
            Text(
                text = "두 번째!",
                modifier = Modifier.weight(2f)
            )
            Text(
                text = "세 번째!",
                modifier = Modifier.weight(3f)
            )
        }

        // 5. Text 대신 Icon 넣기
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .size(width = 200.dp, height = 40.dp)
                .border(width = 1.dp, color = Color.Gray)
        ) {
            Text(
                text = "첫 번째!",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Top)
                    .weight(3f)
                    .background(color = Color.Gray)
            )
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "CheckCircle",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "세 번째!",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .background(Color.DarkGray)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC06_RowTheme {
        RowEx()
    }
}