package kr.co.rmadmsjm.jc09_snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kr.co.rmadmsjm.jc09_snackbar.ui.theme.JC09_SnackBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC09_SnackBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnackbarEx()
                }
            }
        }
    }
}

@Composable
fun SnackbarEx() {
    var counter by remember { mutableStateOf(0) }

    // 간단하게 사용하기 위해서는 Scaffold 안에서 사용하는 것이 편함

    // 1. scaffoldState를 만들고 Scaffold에 설정
    // scaffoldState를 만들기 위해 `rememberScaffoldState` 사용
    // -> 없어짐

    // 3. couroutineScope 만들기
    // `rememberCoroutineScope` 사용

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Snackbar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // 2. "더하기" 버튼 만들기
            // action에서 counter를 증가시키기

            // 4. 버튼의 onClick에서 `coroutineScope.launch` 사용

            // 5. 스낵바를 사용하기 위해 `scaffoldState.snackbarHostState.showSnackbar` 사용

            // `message`에 카운터 출력
            // `actionLabel`를 "닫기"로 지정
            // `duration`에 `SnackbarDuration.Short` 사용

            Button(onClick = {
                counter++
                scope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "counter: $counter",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> {
                            // 스낵바가 닫혔을 때의 동작
                        }
                        SnackbarResult.ActionPerformed -> {
                            // 사용자가 "닫기" 버튼을 클릭했을 때의 동작
                        }
                    }
                }
            }) {
                Text(text = "더하기")
            }

            // LaunchedEffect
            // 처음에만 호출을 하고, 상태가 바뀌기 전에는 호출하지 않음
            LaunchedEffect(snackbarHostState) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "LaunchedEffect",
                        duration = SnackbarDuration.Short
                    )
                }
            }


            // 컴포저블 형태 Snackbar
            // 배치 및 사라지게 하는 것도 개발자 책임
            // 직접 사용하는 것보다 SnackbarHostState를 사용해 Snackbar를 사용하는 것이 좋음
//            Snackbar {
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC09_SnackBarTheme {
        SnackbarEx()
    }
}