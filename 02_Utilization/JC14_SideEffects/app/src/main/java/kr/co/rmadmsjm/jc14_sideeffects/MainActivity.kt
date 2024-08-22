package kr.co.rmadmsjm.jc14_sideeffects

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.launch
import kr.co.rmadmsjm.jc14_sideeffects.ui.theme.JC14_SideEffectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC14_SideEffectsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EffectEx(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun EffectEx(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // 1. `LaunchedEffect`을 이용해서 스낵바 사용
    // 파라미터에는 `scaffoldState.snackbarHostState` 전달
    // "헬로 컴포즈" 출력
    // ⭐ `LaunchedEffect`는 `CouroutineScope`를 만들기 때문에 스코프를 별도로 만들 필요 없음
    // snackbarHostState가 변경되었을 때 리셋됨
    LaunchedEffect(key1 = snackbarHostState) {
        snackbarHostState.showSnackbar(message = "헬로 컴포즈")
    }

    // 2. `DisposableEffect`를 호출하고 파리미터로 `lifecycleOwner` 전달

    // `LifecycleEventObserver`를 상속받고 두 상태에 대해 로그를 남기기
    // `Lifecycle.Event.ON_START`, `Lifecycle.Event.ON_STOP`

    // 블록 내에서 `lifecycleOwner.lifecycle.addObserver`로 옵저버를 추가하고 onDispose에서 옵저버 제거

    // lifecycleOwner가 바뀌었을 때 리셋됨
    // 리셋되었을 때 dispose가 되고, dispose가 되면 onDispose 블록 호출
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // SAM: Single Abstract Method
            when(event) {
                Lifecycle.Event.ON_CREATE -> Log.d("MySideEffect", "ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d("MySideEffect", "ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d("MySideEffect", "ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d("MySideEffect", "ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d("MySideEffect", "ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d("MySideEffect", "ON_DESTROY")
                Lifecycle.Event.ON_ANY -> Log.d("MySideEffect", "ON_ANY")
            }
        }

        // observer 셋팅
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            // observer 제거
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("MySideEffect", "onDispose: observer 제거")
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("스낵바 등장!")
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "스낵바")
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "show Snackbar")
            }
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Side Effect!!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC14_SideEffectsTheme {
        EffectEx()
    }
}