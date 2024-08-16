package kr.co.rmadmsjm.jc06_dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import kr.co.rmadmsjm.jc06_dialog.ui.theme.JC06_DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC06_DialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DialogEx()
                }
            }
        }
    }
}

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = true }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // 다이얼로그가 열린 상태에서 다이얼로그 밖을 클릭했을 때 어떻게 할지 설정
                // 1. `openDialog`를 이용해 다이얼로그를 끌 수 있도록 함
                openDialog = false
            },
            confirmButton = {
                // 2. "더하기" 버튼을 만들고 `counter`를 증가시키기
                // 다이얼로그 끄기
                Button(
                    onClick = {
                        counter++
                        openDialog = false
                    }
                ) {
                    Text(text = "더하기")
                }
            },
            dismissButton = {
                // 3. "취소" 버튼을 만들고 다이얼로그 끄기
                Button(
                    onClick = { 
                        openDialog = false
                    }
                ) {
                    Text(text = "취소")
                }
            },
            title = {
                // 4. 타이틀 만들기
                Text(text = "더하기")
            },
            text = {
                // 5. 다이얼로그 설명할 문구 출력
                Text(text = "더하기 버튼을 누르면 카운터가 증가합니다.\n버튼을 눌러주세요.")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC06_DialogTheme {
        DialogEx()
    }
}