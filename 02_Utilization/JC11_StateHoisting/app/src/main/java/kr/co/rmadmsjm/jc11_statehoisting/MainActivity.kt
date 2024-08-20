package kr.co.rmadmsjm.jc11_statehoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc11_statehoisting.ui.theme.JC11_StateHoistingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC11_StateHoistingTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    PyeongToSquareMeter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PyeongToSquareMeter(modifier: Modifier = Modifier) {
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }
    var squaremeter by rememberSaveable {
        mutableStateOf((23 * 3.306).toString())
    }

    // 1. remember를 이용해 상태를 만들고 평 값을 입력하면 제곱미터가 출력되도록 화면 구성
    // 평을 제곱미터로 바꾸기 위해서는 3.306 곱하기

    // 좋지 못 한 방법
    // 상태가 전달되는 범위를 가능한 좁혀야 함
    // 상태가 전달되는 범위를 좁히기 위해 상태를 윗단으로 끌어올리는 것: State Hoisting
    /*
    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if (it.isBlank()) {
                    pyeong = ""
                    squaremeter = ""
                    return@OutlinedTextField
                }
                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
                pyeong = it
                squaremeter = (numericValue * 3.306).toString()
            },
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squaremeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            }
        )
    }
     */

    // 2. `Composable` 함수를 만들고 `Column`의 항목을 옮기기
    // 단 상태는 옮기지 않기

    // 파라미터는 아래와 같이 구성
    // `pyeong: String, squareMeter: String, onPyeongChange: (String) -> Unit`

    // UI는 UI만 다루고 있고, 상태를 다루는 것은 생태만 다루고 있음
    // 💡 UI와 상태를 다루는 것이 분리됨
    PyeongToSquareMeterStateless(
        pyeong = pyeong,
        squaremeter = squaremeter
    ) {
        if (it.isBlank()) {
            pyeong = ""
            squaremeter = ""
            return@PyeongToSquareMeterStateless
        }
        val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squaremeter = (numericValue * 3.306).toString()
    }
}

@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squaremeter: String,
    onPyeongchange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongchange,
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squaremeter,
            onValueChange = onPyeongchange,
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    JC11_StateHoistingTheme {
        PyeongToSquareMeter()
    }
}