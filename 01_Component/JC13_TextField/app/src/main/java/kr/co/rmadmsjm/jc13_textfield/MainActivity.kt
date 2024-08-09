package kr.co.rmadmsjm.jc13_textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc13_textfield.ui.theme.JC13_TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC13_TextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var name by remember { mutableStateOf("rmadmsjm") }

    Column {
        Column(modifier = Modifier.padding(16.dp)) {
            // 1. Text 위에 TextField 만들기
            // value와 onValueChanged는 비우기
            TextField(
                value = "rmadmsjm",
                onValueChange = {}
            )
            Text(text = "Hello Android")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 2. Text에 Android 대신 TextField 입력 출력
            // mutableStateOf("") 필드 사용
            TextField(
                value = name,
                onValueChange = {
                    name = it
                }
            )

            // 3. TextField에 label 추가
            // 내용에는 `Text("Name")`

            // 4. TextField와 Text 사이에 Spacer를 넣어 8.dp 간격 주기

            // 5. TextField를 OutlinedTextField로 변경

            Text(text = "Hello Android")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 3. TextField에 label 추가
            // 내용에는 `Text("Name")`
            TextField(
                value = name,
                label = {
                    Text(text = "Name")
                },
                onValueChange = {
                    name = it
                }
            )

            // 4. TextField와 Text 사이에 Spacer를 넣어 8.dp 간격 주기

            // 5. TextField를 OutlinedTextField로 변경

            Text(text = "Hello $name")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 4. TextField와 Text 사이에 Spacer를 넣어 8.dp 간격 주기
            TextField(
                value = name,
                label = {
                    Text(text = "Name")
                },
                onValueChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.size(8.dp))


            Text(text = "Hello $name")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 5. TextField를 OutlinedTextField로 변경
            OutlinedTextField(
                value = name,
                label = {
                    Text(text = "Name")
                },
                supportingText = {
                    Text(text = "이름")
                },
                onValueChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Hello $name")
        }
        Column(modifier = Modifier.padding(16.dp)) {
            // 5. TextField를 OutlinedTextField로 변경
            OutlinedTextField(
                value = name,
                label = {
                    Text(text = "Name")
                },
                supportingText = {
                    Text(text = "이름")
                },
                textStyle = MaterialTheme.typography.bodySmall,
                onValueChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Hello $name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC13_TextFieldTheme {
        Greeting()
    }
}