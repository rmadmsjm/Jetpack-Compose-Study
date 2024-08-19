package kr.co.rmadmsjm.jc10_bottomappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kr.co.rmadmsjm.jc10_bottomappbar.ui.theme.JC10_BottomAppBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC10_BottomAppBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomAppBarEx()
                }
            }
        }
    }
}

@Composable
fun BottomAppBarEx() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var counter by remember { mutableStateOf(0) }

    // 1. `Scaffold`에 `scaffoldState`를 설정
    // -> 없어짐
    // 스낵바 사용 시, 다음과 같이 사용
    // val snackbarHostState = remember { SnackbarHostState() }

    // 2. `bottomBar` 파라미터에 `BottomAppBar` 설정
    // 내용은 텍스트와 버튼 설정
    // 버튼에는 `snackBar`를 연동해 메시지 출력

    // 3. 더하기와 빼기 버튼을 만들고 `MutableState` 만들기
    // `Scaffold`의 `content`에 `Text`를 넣어 카운터 출력

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "hello")
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message = "나는 스낵바다!")
                            }
                        }
                    ) {
                        Text(text = "Snackbar!")
                    }
                    Button(
                        onClick = { counter++ }
                    ) {
                        Text(text = "증가")
                    }
                    Button(
                        onClick = { counter-- }
                    ) {
                        Text(text = "감소")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "BottomAppBar 만들기!")
            Text(text = "counter: $counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC10_BottomAppBarTheme {
        BottomAppBarEx()
    }
}