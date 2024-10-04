package kr.co.rmadmsjm.jc04_theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc04_theme.ui.theme.JC04_ThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. JC04_ThemeTheme으로 이동
            JC04_ThemeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("안녕하세요. 패스트캠퍼스")
            Text("스안녕하세요. 패스트캠퍼")
            Text("퍼스안녕하세요. 패스트캠")
            Text("캠퍼스안녕하세요. 패스트")
            Text("트캠퍼스안녕하세요. 패스")
            Text("스트캠퍼스안녕하세요. 패")
            Text("패스트캠퍼스안녕하세요.")

            Button(onClick = {}) {
                Text("버튼")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC04_ThemeTheme {
        Greeting()
    }
}