package kr.co.rmadmsjm.jc03_chainbarrier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kr.co.rmadmsjm.jc03_chainbarrier.ui.theme.JC03_ChainBarrierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC03_ChainBarrierTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayoutEx()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutEx() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (redBox, yellowBox, magentaBox, text) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = 18.dp
                    )
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = 50.dp
                    )
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = 100.dp
                    )
                }
        )

        // 1. `createVerticalChain`, `createHorizontalChain`를 이용해 세 박스의 레퍼런스를 연결
//        createVerticalChain(redBox, yellowBox, magentaBox)
//        createHorizontalChain(redBox, yellowBox, magentaBox)

        // 2. `createHorizontalChain`를 사용하고 `chainStyle` 키워드 파라미터 추가
        // `ChainStyle.Packed`,`ChainStyle.Spread`, `ChainStyle.SpreadInside`등 지정
        createHorizontalChain(
            redBox, yellowBox, magentaBox,
            chainStyle = ChainStyle.SpreadInside
        )

        // 3. 세 박스의 top을 parent.top에 연결하고 각각 다른 마진 설정

        // 4. `createBottomBarrier`로 배리어를 만들기
        // 이는 모든 박스들의 하단을 포함하는 배리어
        var barrier = createBottomBarrier(redBox, yellowBox, magentaBox)

        // 5. `Text` 하나 만들고 top을 박스 베리어로 지정하기
        Text(
            text = "최악의 상황에 주먹을 뻗는다 몇 번이든 상대가 나가떨어질 때까지 큰 감동과 눈물을 선사해 주마 보는 이로 하여금 절대 잊혀지지 않도록",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(barrier)
            }
        )

        // 6. 체이닝 방향이나 베리어 방향을 바꾸며 다양하게 테스트
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC03_ChainBarrierTheme {
        ConstraintLayoutEx()
    }
}