package kr.co.rmadmsjm.jc08_dropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.rmadmsjm.jc08_dropdownmenu.ui.theme.JC08_DropDownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC08_DropDownMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DropDownMenuEx()
                }
            }
        }
    }
}

@Composable
fun DropDownMenuEx() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { expandDropDownMenu = true }) {
            Text("드롭다운 메뉴 열기")
        }
        Text("카운터: $counter")
    }

    // 1. `DropdownMenu`를 만들고 `expanded`를 `expandDropDownMenu`로 등록
    // `onDismissRequest`에 대해서는 `expandDropDownMenu`를 `false`로 바꾸기

    // 2. 두개의 `DropdownMenuItem` 등록
    // `onClick`을 구현하고 내용물은 `Text`로
    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = { expandDropDownMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text(text = "증가") },
            onClick = { counter++ }
        )
        DropdownMenuItem(
            text = { Text(text = "감소") },
            onClick = { counter-- })
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC08_DropDownMenuTheme {
        DropDownMenuEx()
    }
}