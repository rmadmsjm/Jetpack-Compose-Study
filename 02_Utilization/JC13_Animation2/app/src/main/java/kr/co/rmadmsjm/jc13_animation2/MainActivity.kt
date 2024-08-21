package kr.co.rmadmsjm.jc13_animation2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc13_animation2.ui.theme.JC13_Animation2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC13_Animation2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Animation2Ex(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Animation2Ex(modifier: Modifier = Modifier) {
    var isDarkMode by remember { mutableStateOf(false) }

    // 이걸 사용하면 관리가 잘 안 됨
    // animateColorAsState(targetValue = )
    // 관리를 위해 사용하는 것: updateTransition

    // 1. `updateTransition` 수행하고 `targetState`를 `isDarkMode`로 설정
    // `transition`으로 리턴을 받기
    // label은 다른 도구에서 디버깅을 하기 위한 라벨
    val transition = updateTransition(targetState = isDarkMode, label = "다크 모드 트랜지션")

    // 2. `transition`에 대해 `animateColor`를 호출해 `backgroundColor`를 받기
    // false 하얀 배경, true 검은 배경
    val backgroundColor by transition.animateColor(label = "다크 모드 배경 색상 애나메이션") { state ->
        when(state) {
            true -> Color.Black
            false -> Color.White
        }
    }

    // 3. 글자 색상 만들기
    val fontColor by transition.animateColor(label = "다크 모드 글자 색상 애니메이션") { state ->
        when(state) {
            true -> Color.White
            false -> Color.Black
        }
    }

    // 4. `animateFloat`를 호출해서 알파 값을 만들기
    val alpha by transition.animateFloat(label = "다크 모드 알파 애니메이션") { state ->
        when(state) {
            true -> 1.0f
            false -> 0.7f
        }
    }

    // 5. 컬럼에 배경과 알파 적용

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .alpha(alpha)
    ) {
        // 6. 라디오 버튼에 글자 색 적용
        RadioButtonWithText(
            text = "일반 모드",
            color = fontColor,
            selected = !isDarkMode
        ) {
            isDarkMode = false
        }
        RadioButtonWithText(
            text = "다크 모드",
            color = fontColor,
            selected = isDarkMode
        ) {
            isDarkMode = true
        }

        // 7. Crossfade를 이용해
        // `isDarkMode`가 true일 경우 `Row`로 항목 표현
        // false일 경우 `Column`으로 표현
//        Row {
//            Box(modifier = Modifier
//                .background(Color.Red)
//                .size(20.dp)) {
//                Text("1")
//            }
//            Box(modifier = Modifier
//                .background(Color.Magenta)
//                .size(20.dp)) {
//                Text("2")
//            }
//            Box(modifier = Modifier
//                .background(Color.Blue)
//                .size(20.dp)) {
//                Text("3")
//            }
//        }

        Crossfade(targetState = isDarkMode) { state ->
            when(state) {
                true -> {
                    Row {
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text(
                                text = "1",
                                color = fontColor
                            )
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text(
                                text = "2",
                                color = fontColor
                            )
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text(
                                text = "3",
                                color = fontColor
                            )
                        }
                    }
                }
                false -> {
                    Column {
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text(
                                text = "A",
                                color = fontColor
                            )
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text(
                                text = "B",
                                color = fontColor
                            )
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text(
                                text = "C",
                                color = fontColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC13_Animation2Theme {
        Animation2Ex()
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    JC13_Animation2Theme {
        RadioButtonWithText(
            text = "라디오 버튼",
            color = Color.Red,
            selected = true,
            onClick = {}
        )
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, color = color)
    }
}