package kr.co.rmadmsjm.jc05_box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import kr.co.rmadmsjm.jc05_box.ui.theme.JC05_BoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC05_BoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BoxEx()
                }
            }
        }
    }
}

@Composable
fun BoxEx() {
    Column {
        // Box 주요 용도
        // Box 자체를 만들기 위해
        // 프레임 레이아웃과 같이 중첩시키기 위해
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(width = 2.dp, color = Color.Red)
        ) {
            Text(
                text = "Hello World",
                modifier = Modifier.align(Alignment.Center))
        }

        // 1. Box 안에 Text 2개 배치
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(width = 2.dp, color = Color.Blue)
        ) {
            Text(text = "Hello World", modifier = Modifier.align(Alignment.TopStart))
            Text(text = "Jetpack", modifier = Modifier.align(Alignment.Center))
            Text(text = "Compose", modifier = Modifier.align(Alignment.BottomEnd))
        }

        // 2. Box 안에 Box 2개 배치, 사이즈 70dp, 색상은 서로 다르게
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(width = 2.dp, color = Color.Green)
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(color = Color.LightGray)
                    .align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(color = Color.DarkGray)
                    .align(Alignment.BottomEnd)
            )
        }

        // 3. 부모 Box의 modifier 설정 제거(콘텐트 사이즈만큼 보여짐)
        // 첫 번째 자식 Box의 사이즈 matchParentSize()로 설정
        // 다음은 fillMaxSize()로 설정
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Magenta)
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = Color.LightGray)
                    .align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color.DarkGray)
                    .align(Alignment.BottomEnd)
            )
        }

        Box(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Cyan)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray)
                    .align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(color = Color.DarkGray)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC05_BoxTheme {
        BoxEx()
    }
}