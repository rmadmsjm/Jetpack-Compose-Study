package kr.co.rmadmsjm.jc11_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.rmadmsjm.jc11_card.ui.theme.JC11_CardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC11_CardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CardEx(cardData)
                    }
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUri = "https://raw.githubusercontent.com/Fastcampus-JetpackCompose-1/part1-chapter3/main/part1-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
            imageDescription = "엔텔로프 캐년",
            author = "Dalinaum",
            description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
        )
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        // elevation = 8.dp로 설정하면 Type mismatch 오류 발생
        // elevation = CardDefaults.cardElevation(8.dp)로 설정
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.padding(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                // 1. `AsyncImage`, `Spacer`, `Column`, `Text`로 레이아웃 만들기
                AsyncImage(
                    model = cardData.imageUri,
                    contentDescription = cardData.imageDescription,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = cardData.description)
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.LightGray),
            modifier = Modifier.padding(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                // 2. `AsyncImage`에 `placeholder` 지정
                // `contentScale`을 `ContentScale.Crop`으로 설정
                // modifier에 `clip(CircleShape)` 설정
                AsyncImage(
                    model = cardData.imageUri,
                    contentScale = ContentScale.Crop,
                    contentDescription = cardData.imageDescription,
                    placeholder = ColorPainter(placeHolderColor),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = cardData.description)
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            border = BorderStroke(1.dp, color = Color.DarkGray),
            modifier = Modifier.padding(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                // 2. `AsyncImage`에 `placeholder` 지정
                // `contentScale`을 `ContentScale.Crop`으로 설정
                // modifier에 `clip(CircleShape)` 설정
                AsyncImage(
                    model = cardData.imageUri,
                    contentScale = ContentScale.Crop,
                    contentDescription = cardData.imageDescription,
                    placeholder = ColorPainter(placeHolderColor),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(5.dp)),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = cardData.description)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC11_CardTheme {
        CardEx(MainActivity.cardData)
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)