package kr.co.rmadmsjm.jc10_networkimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kr.co.rmadmsjm.jc10_networkimage.ui.theme.JC10_NetworkImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC10_NetworkImageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoilEx()
                }
            }
        }
    }
}

@Composable
fun CoilEx() {
    Column {
        // 3. rememberImagePainter를 이용해 Image의 painter 설정
        // Compose 한국어 문서의 추천, but Deprecated
        // 이미지 URI: https://raw.githubusercontent.com/Fastcampus-JetpackCompose-1/part1-chapter3/main/part1-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg
        val painter =
            rememberImagePainter(data = "https://raw.githubusercontent.com/Fastcampus-JetpackCompose-1/part1-chapter3/main/part1-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg")
        Image(
            painter = painter,
            contentDescription = "엔텔로프 캐년"
        )

        // 4. AsyncImage 이용
        // coil 문서 추천
        // model에 주소를 적으면 됨
        AsyncImage(
            model = "https://raw.githubusercontent.com/Fastcampus-JetpackCompose-1/part1-chapter3/main/part1-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
            contentDescription = "엔텔로프 캐년"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC10_NetworkImageTheme {
        CoilEx()
    }
}