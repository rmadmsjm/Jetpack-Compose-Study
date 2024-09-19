package kr.co.rmadmsjm.jc03_compositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc03_compositionlocal.ui.theme.JC03_CompositionLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC03_CompositionLocalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

// 4. `compositionLocalOf`에 `8.dp`를 넣어 `LocalElevation` 할당
val localElevation = compositionLocalOf { 8.dp }

@Composable
fun Greeting() {
    // 1. `CompositionLocalProvider`을 이용하면 특정 블록에 암시적인 값을 설정할 수 있음
    // `CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled)`등 설정
    // `LocalContentAlpha`를 `ContentAlpha.disabled`로 설정하겠다는 뜻
    // `ContentAlpha.medium`, `ContentAlpha.high`, `ContentAlpha.disabled`등 제공할 수 있음
    // `LocalContentColor`도 설정
    // `Color.XXX`을 설정하면 됨

    // 2. 중간 중간에 `LocalContentColor.current` 등의 값을 출력하기
    // 가장 가까운 곳에서 설정한 값을 `current`로 얻을 수 있음

    // 5. Card의 elevation에 `LocalElevation` 적용

    // 6. LocalElevation의 값을 `CompositionLocalProvider`로 바꾸기

    CompositionLocalProvider(value = localElevation provides 8.dp) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(localElevation.current)
        ) {

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.error) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("안녕하세요. 패스트캠퍼스")
                    Text("스안녕하세요. 패스트캠퍼")
                    Text(text = "${LocalContentColor.current}")

                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                        Text("퍼스안녕하세요. 패스트캠")
                        Text("캠퍼스안녕하세요. 패스트")
                        Text("트캠퍼스안녕하세요. 패스")
                        Text(text = "${LocalContentColor.current}")
                    }
                    Text("스트캠퍼스안녕하세요. 패")
                    Text("패스트캠퍼스안녕하세요.")

                    // 3. `LocalContext.current`의 `resources` 출력
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC03_CompositionLocalTheme {
        Greeting()
    }
}