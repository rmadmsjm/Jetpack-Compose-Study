package kr.co.rmadmsjm.jc01_text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.rmadmsjm.jc01_text.ui.theme.JC01_TextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC01_TextTheme {
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
       Text(text = "Hello $name")

       // 1. 색상 지정
       Text(color = Color.Red, text = "Hello $name")

       // 2. 해쉬값 색상 지정: Color 객체를 이용해 해쉬값으로 색상 전달 (ARGB 순)
       // 키워드 파라미터이기 때문에 순서는 상관 없음
       Text(color = Color(0xffff9944), text = "Hello $name")

       // 3. fontSize 설정
       Text(color = Color.Blue, text = "Hello $name", fontSize = 30.sp)

       // 4. fontWeight 설정
       Text(color = Color.Green, text = "Hello $name", fontSize = 30.sp, fontWeight = FontWeight.Bold)

       // 5. fontFamily 설정
       Text(
           color = Color.Cyan,
           text = "Hello $name",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Serif
       )

       // 6. letterSpacing 설정: sp 단위
       Text(
           color = Color.Gray,
           text = "Hello $name",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Serif,
           letterSpacing = 10.sp
       )

       // 7. maxLines 설정
       Text(
           color = Color.Yellow,
           text = "Hello $name\nHello $name\nHello $name",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Serif,
           letterSpacing = 10.sp,
           maxLines = 2
       )

       // 8. textDecoration 설정
       Text(
           color = Color.Magenta,
           text = "Hello $name",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Serif,
           letterSpacing = 10.sp,
           textDecoration = TextDecoration.Underline
       )

       // 9. textAlign 설정
       // modifier = Modifier.width(200.dp)나
       // modifier = Modifier.size(200.dp)를 설정
       Text(
           modifier = Modifier.width(300.dp),
           color = Color.DarkGray,
           text = "Hello $name",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = FontFamily.Monospace,
           textAlign = TextAlign.Center
       )
   }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC01_TextTheme {
        Greeting("Android")
    }
}