package kr.co.rmadmsjm.jc07_customdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kr.co.rmadmsjm.jc07_customdialog.ui.theme.JC07_CustomDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC07_CustomDialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomDialog()
                }
            }
        }
    }
}

@Composable
fun CustomDialog() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = {
                openDialog = true
             }
        ) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    if (openDialog) {
        Dialog(
            onDismissRequest = {
                // 1. dismiss 처리
                openDialog = false
            }
        ) {
            Surface(
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            ) {
                // 2. 컬럼을 만들고 설명 작성

                // 3. 컬럼 안에 로우를 만들어 수평 방향으로 버튼 배열
                // 버튼은 +1, -1, 취소로 구성

                // +1은 counter 증가
                // -1은 counter 감소
                // 취소는 다이얼로그 닫기

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "버튼을 클릭해주세요.\n+1을 누르면 값이 증가합니다.\n-1을 누르면 값이 감소합니다.")
                    Row(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Button(
                            onClick = {
                                counter++
                                openDialog = false
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text(text = "+1")
                        }
                        Button(
                            onClick = {
                                counter--
                                openDialog = false
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text(text = "-1")
                        }
                        Button(
                            onClick = {
                                openDialog = false
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text(text = "취소")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC07_CustomDialogTheme {
        CustomDialog()
    }
}