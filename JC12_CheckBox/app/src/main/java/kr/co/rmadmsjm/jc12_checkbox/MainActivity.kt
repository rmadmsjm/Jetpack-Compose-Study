package kr.co.rmadmsjm.jc12_checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc12_checkbox.ui.theme.JC12_CheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC12_CheckBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckBoxEx()
                }
            }
        }
    }
}

@Composable
fun CheckBoxEx() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 1. Checkbox
            // checked 속성은 false
            // onCheckedChange는 비워두기
            // checked 속성이 false면 클릭해도 변경되지 않음
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )
            Text(text = "프로그래머입니까?")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 2. onCheckedChange에서 boolean 값 변수 바꾸기
            // checked에서 그 값을 반영 -> 잘 안 됨
            // 리컴포지션, 다시 그려지는 절차가 있을 때만 내용이 바뀜
            // 리컴포지션이 가능할 때 = 상태 값이 바뀔 때
            // 변수 checked는 상태 값이 아님
            // compose에서의 상태 값 = mutableStateOf()
            var checked = false

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                }
            )
            Text(text = "프로그래머입니까?")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 3. boolean 대신 remember { mutableStateOf(false) } 사용해 상태 도입
            // value 프로퍼티를 이용
            val checked = remember { mutableStateOf(false) }

            Checkbox(
                checked = checked.value,
                onCheckedChange = {
                    checked.value = !checked.value
                }
            )
            Text(text = "프로그래머입니까?")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 4. delegated properties로 변경
            var checked by remember { mutableStateOf(false) }

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                }
            )

            // 5. destruction으로 상태를 받아서 사용

            Text(text = "프로그래머입니까?")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 5. destruction으로 상태를 받아서 사용
            // 비구조화
            val (checked, setChecked) = remember { mutableStateOf(false) }

            Checkbox(
                checked = checked,
                onCheckedChange = setChecked
            )
            Text(
                text = "프로그래머입니까?",
                modifier = Modifier.clickable {
                    setChecked(!checked)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultGPreview() {
    JC12_CheckBoxTheme {
        CheckBoxEx()
    }
}