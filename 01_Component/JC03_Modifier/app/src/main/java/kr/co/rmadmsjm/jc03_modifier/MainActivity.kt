package kr.co.rmadmsjm.jc03_modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import kr.co.rmadmsjm.jc03_modifier.ui.theme.JC03_ModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC03_ModifierTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModifierEx()
                }
            }
        }
    }
}

@Composable
fun ModifierEx() {
    Column {
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 1. Modifier.fillMaxSize()
        Button(
            onClick = {},
            //modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 2. fillMaxSize대신 Modifier.height 설정
        Button(
            onClick = {},
            modifier = Modifier.height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 3. height와 width 설정
        Button(
            onClick = {},
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 4. size로 설정
        // height와 width 같이 설정할 경우, size로 설정
        Button(
            onClick = {},
            modifier = Modifier.size(200.dp, 80.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 5. background 설정
        // ⭐ 버튼 background 색상은 Modifier.background로 바꿀 수 없음
        Button(
            onClick = {},
            modifier = Modifier
                .size(150.dp, 80.dp)
                .background(Color.Red)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 6. colors 파라미터 사용해 background 설정
        // ButtonDefaults.buttonColors 설정
        // backgroundColor -> containerColor로 변경: 버튼 자체 색상
        // contentColor: 버튼에 들어가는 이미지나 텍스트에 영향을 줌
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Yellow
            ),
            onClick = {},
            modifier = Modifier.size(150.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 7. padding 설정
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Black
            ),
            onClick = {},
            modifier = Modifier.size(200.dp).padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // 8. Button enabled를 false로 설정, Text의 modifier에 clickable 설정
        Button(
            onClick = {},
            enabled = false,
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text(
                modifier = Modifier.clickable {},
                text = "Search"
            )
        }

        // 9. Text의 modifier에 offset 설정, x 파라미터 설정
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            onClick = {},
            modifier = Modifier.size(150.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.background(Color.Red)
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
                    .background(Color.Green)
            )
            Text(
                modifier = Modifier.offset(x = 20.dp, y = 10.dp)
                    .background(Color.Blue),
                text = "Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC03_ModifierTheme {
        ModifierEx()
    }
}