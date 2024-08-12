package kr.co.rmadmsjm.jc02_constraintset

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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import kr.co.rmadmsjm.jc02_constraintset.ui.theme.JC02_ConstraintSetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC02_ConstraintSetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintSetEx()
                }
            }
        }
    }
}

@Composable
fun ConstraintSetEx() {
    val constraintSet = ConstraintSet {
        // 1. createRefFor로 레드, 마젠타, 그린, 옐로 박스를 위한 레퍼런스 만들기
        // 파라미터 id로 레퍼런스의 이름을 작성
        // eg. redBox
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val yellowBox = createRefFor("yellowBox")

        // 2. `constrain`을 열고, 만들었던 레퍼런스를 인자로 넣기
        // 레드, 마젠타, 그린, 옐로 박스 레퍼런스에 대해 `constrain` 적용
        // 후행 람다의 내용은 기존에 `constrainAs`에 적어둔 것을 참고
        constrain(redBox) {
            bottom.linkTo(parent.bottom, 10.dp)
            end.linkTo(parent.end, 30.dp)
        }
        constrain(magentaBox) {
            start.linkTo(parent.start, 10.dp)
            end.linkTo(parent.end, 30.dp)
        }
        constrain(greenBox) {
            centerTo(parent)
        }
        constrain(yellowBox) {
            start.linkTo(greenBox.end)
            top.linkTo(greenBox.bottom)
        }
    }

    // 5. ConstraintLayout의 첫 인자로 ConstraintSet 전달
    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        // 3. `ConstraintLayout`내에서 생성한 레퍼런스와 `constrainAs` 모디파이어 삭제
//        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()

        // 4. Box마다 `layoutId`를 설정
        // 파라미터는 `ConstraintSet`에 만든 레퍼런스의 id로 작성
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
//                .constrainAs(redBox) {
//                    bottom.linkTo(parent.bottom, 10.dp)
//                    end.linkTo(parent.end, 30.dp)
//                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
//                .constrainAs(magentaBox) {
//                    start.linkTo(parent.start, 10.dp)
//                    end.linkTo(parent.end, 30.dp)
//                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
//                .constrainAs(greenBox) {
//                    centerTo(parent)
//                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .layoutId("yellowBox")
//                .constrainAs(yellowBox) {
//                    start.linkTo(greenBox.end)
//                    top.linkTo(greenBox.bottom)
//                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC02_ConstraintSetTheme {
        ConstraintSetEx()
    }
}