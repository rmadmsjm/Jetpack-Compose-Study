package kr.co.rmadmsjm.jc02_button

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc02_button.ui.theme.JC02_ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC02_ButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonExample(onButtonClicked = {
                        Toast.makeText(this, "send Clicked", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}

@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    Column {
        // 버튼을 만들기 위해서는 클릭 이벤트를 다루는 onClick이 있어야 함
        // 람다에는 디자인 요소 설정
        Button(onClick = {}) {
            Text(text = "Send")
        }

        // 1. Toast 출력
        // 외부에서 onButtonClicked를 설정하고 거기에 위임할 수 있도록 함
        Button(onButtonClicked) {
            Text(text = "Send")
        }

        // 2. Icon 추가
        // imageVector는 Icons.Filled.Send, contentDescription는 null
        // Icons.Filled.Send: material 기본 이미지 벡터
        Button(onButtonClicked) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Text(text = "Send")
        }

        // 3. Spacer 추가
        // modifier에 Modifier.size, 사이즈에 ButtonDefaults.IconSpacing(기본값) 지정
        Button(onButtonClicked) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // 4. enabled
        Button(
            onClick = onButtonClicked,
            enabled = false
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // 5. border 설정
        Button(
            onClick = onButtonClicked,
            border = BorderStroke(10.dp, Color.Red)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // 6. shape 설정
        // CircleShape,RoundedCornerShape,
        Button(
            onClick = onButtonClicked,
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // 7. contentPadding 설정
        Button(
            onClick = onButtonClicked,
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC02_ButtonTheme {
        ButtonExample(onButtonClicked = {})
    }
}