package kr.co.rmadmsjm.jc04_surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc04_surface.ui.theme.JC04_SurfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC04_SurfaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        // Surface는 밑으로 클릭을 전달하지 않음
        // Surface는 가장 기본적으로 UI를 구축하는 기반이 됨
        Surface(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // 1. elevation 설정
        // elevation 파라미터 없어짐
        // shadowElevation: 기존 elevation 파라미터, 그림자 깊이를 설정
        // tonalElevation: 요소의 색조 깊이를 설정
        Surface(
            modifier = Modifier.padding(5.dp),
            shadowElevation = 20.dp,
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            tonalElevation = 50.dp
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // 2. border 설정
        Surface(
            modifier = Modifier.padding(5.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Blue
            )
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // 3. shape 설정
        // CircleShape, RoundedCornerShape
        Surface(
            modifier = Modifier.padding(5.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Blue
            ),
            shape = CircleShape
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // 4. color 설정
        // MaterialTheme.colorScheme에서 primary, error, background, surface, secondary 등 지정
        // contentColor가 자동으로 선택됨
        // primary를 설정하면 onPrimary가, error를 설정하면 onError가 contentColor가 됨
        Surface(
            modifier = Modifier.padding(5.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Blue
            ),
            color = MaterialTheme.colorScheme.primary
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC04_SurfaceTheme {
        Greeting("Android")
    }
}