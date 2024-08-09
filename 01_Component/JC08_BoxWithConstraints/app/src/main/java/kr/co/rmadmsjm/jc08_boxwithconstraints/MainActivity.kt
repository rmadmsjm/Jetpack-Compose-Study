package kr.co.rmadmsjm.jc08_boxwithconstraints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc08_boxwithconstraints.ui.theme.JC08_BoxWithConstraintsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC08_BoxWithConstraintsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Outer()
                }
            }
        }
    }
}

@Composable
fun Outer() {
    // 4. Column에 width 지정해 제한
    // 부모 레이아웃의 크기를 명확히 지정하지 않은 경우 자식 컴포넌트가 자신의 크기를 제대로 결정하지 못할 수 있음
    Column(
        modifier = Modifier.size(600.dp)
    ) {
        // 2. Inner의 인자로 Modifier.widthIn(min = 100.dp) 전달
        // heightIn도 전달
        // 각각 인자의 max값도 전달
        Inner(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 160.dp)
                .heightIn(min = 200.dp, max = 300.dp)
                .border(width = 1.dp, color = Color.Red)
        )
        Inner(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 160.dp)
                .heightIn(min = 200.dp, max = 100.dp)
                .border(width = 1.dp, color = Color.Green)
        )
    }
}

// 1. Inner 인자로 modifier 전달
// 기본 값은 Modifier로 지정
// 파라미터로 받은 modifier를 BoxWithConstrains로 전달
@Composable
private fun Inner(modifier: Modifier = Modifier) {
    // BoxWithConstraints
    // 자주 쓰이진 않지만, 어떤 길이에 맞춰 유동적으로 width 및 height가 몇 이상일 때 어떤 걸 넣고자 할 경우 사용함
    BoxWithConstraints(
        modifier = modifier
    ) {
        // 3. maxHeight 값이 150dp가 넘을 때만 추가로 텍스트 출력
        if (maxHeight > 150.dp) {
            Text(
                text = "여기 꽤 길군요!",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Text("maxW:$maxWidth maxH:$maxHeight minW: $minWidth minH:$minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC08_BoxWithConstraintsTheme {
        Outer()
    }
}