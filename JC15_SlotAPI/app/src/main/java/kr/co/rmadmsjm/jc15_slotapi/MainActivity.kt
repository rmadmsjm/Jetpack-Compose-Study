package kr.co.rmadmsjm.jc15_slotapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc15_slotapi.ui.theme.JC15_SlotAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC15_SlotAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SlotEx()
                }
            }
        }
    }
}

// 1. `Row`를 `@Composable` 함수로 분리
// `checked`의 값, `Text`의 `text`를 인자로 전달
@Composable
fun CheckBoxWithRow(
    checked: MutableState<Boolean>,
    checkBoxTitle: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        Text(
            text = checkBoxTitle,
            modifier = Modifier.clickable { checked.value = !checked.value }
        )
    }
}

// 2. `@Composable` 함수에서 `@Composable () -> Unit` 타입으로 `content`를 받아오기
// `Row`의 `Text`를 뺴고 `content()` 넣기
// `Row`에 `Modifier.clickable`를 넣어 전체 클릭 가능하게 하기
@Composable
fun CheckBoxWithSlot(
    checked: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { checked.value = !checked.value }
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        content()
    }
}

// 3. `content`의 타입을 `@Composable RowScope.() -> Unit`로 변경
// 이렇게 다른 콤포저블 컨텐트를 넣는 방식이 Slot API
@Composable
fun CheckBoxWithSlot2(
    checked: MutableState<Boolean>,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { checked.value = !checked.value }
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        content()
    }
}

// 4. 상태를 바꾸는 람다를 `@Composable` 함수의 인자로 빼기
// 인자에서 MutableState대신 boolean 값으로 변경
@Composable
fun CheckBoxWithSlot3(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onCheckedChange() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() }
        )
        content()
    }
}

@Composable
fun SlotEx() {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }
    var checked4 by remember { mutableStateOf(false) }

//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Checkbox(
//            checked = checked1.value,
//            onCheckedChange = { checked1.value = it }
//        )
//        Text(
//            text = "텍스트 1",
//            modifier = Modifier.clickable { checked1.value = !checked1.value }
//        )
//    }
//
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Checkbox(
//            checked = checked2.value,
//            onCheckedChange = { checked2.value = it }
//        )
//        Text(
//            text = "텍스트 2",
//            modifier = Modifier.clickable { checked2.value = !checked2.value }
//        )
//    }

    Column {
        CheckBoxWithRow(
            checked = checked1,
            checkBoxTitle = "체크 박스 1"
        )
        CheckBoxWithRow(
            checked = checked2,
            checkBoxTitle = "체크 박스 2"
        )
        Spacer(modifier = Modifier.size(10.dp))
        CheckBoxWithSlot(
            checked = checked1,
        ) {
            Text(text = "체크 박스 3")
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "계정")
        }
        CheckBoxWithSlot(
            checked = checked2,
        ) {
            Text(text = "체크 박스 4")
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "계정")
        }
        Spacer(modifier = Modifier.size(10.dp))
        CheckBoxWithSlot2(
            checked = checked1,
        ) {
            Text(text = "체크 박스 5")
            Icon(imageVector = Icons.Filled.Call, contentDescription = "통화")
        }
        CheckBoxWithSlot2(
            checked = checked2,
        ) {
            Text(text = "체크 박스 6")
            Icon(imageVector = Icons.Filled.Call, contentDescription = "통화")
        }
        Spacer(modifier = Modifier.size(10.dp))
        CheckBoxWithSlot3(
            checked = checked3,
            onCheckedChange = {
                checked3 = !checked3
            }
        ) {
            Text(text = "체크 박스 7")
            Icon(imageVector = Icons.Filled.Call, contentDescription = "통화")
        }
        CheckBoxWithSlot3(
            checked = checked4,
            onCheckedChange = {
                checked4 = !checked4
            }
        ) {
            Text(text = "체크 박스 8")
            Icon(imageVector = Icons.Filled.Call, contentDescription = "통화")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC15_SlotAPITheme {
        SlotEx()
    }
}