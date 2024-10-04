package kr.co.rmadmsjm.jc05_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.rmadmsjm.jc05_navigation.ui.theme.JC05_NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC05_NavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyNav(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// 2. `navController` 파라미터 만들기
// `NavHostController` 타입에 기본 값은 `rememberNavController()`
@Composable
fun MyNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // 3. `NavHost` 만들기
    // `navController`, `"Home"`, `modifier`를 전달

    // 4. `composable("Home")`를 만들고 안에 "Office로 이동" 버튼 만들기

    // 5. `composable("Office")`를 만들고 텍스트 넣기
    // "Office로 이동" 버튼에 `navController.navigate("Office")`를 넣기

    // 6. `Playground`를 만들고 `Home`, `Office`, `Playgorund`를 서로 연결

    // 7. Home, Office, Playgorund, Home, Office, Playgorund 순으로 이동한 후 백버튼을 계속 눌러서 이동 확인

    // 8. navigate에 후행 람다로 `popUpTo("Home")`을 넣고 스택 이동 확인

    // 9. `popUpTo`의 후행 람다에 `inclusive = true`를 넣고 스택 이동 확인

    // 10. `Home`에서 `Home`으로 가는 버튼을 만들고 `launchSingleTop = true` 설정

    // 11. "Argument/{userId}"를 라우트로 받는 composable 만들기
    // `arguments?.get("userId")`을 받아 출력
    // "Argument/fastcampus"로 이동하는 버튼을 만들기

    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        composable("Home") {
            Column {
                Text(text = "Home")
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Office로 이동")
                }
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Playground로 이동")
                }
                Button(onClick = {
                    navController.navigate("Argument/rmadmsjm") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "아이디 이동")
                }
            }
        }
        composable("Office") {
            Column {
                Text(text = "Office")
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Playground로 이동")
                }
            }
        }
        composable("Playground") {
            Column {
                Text(text = "Playground")

                Button(onClick = {
                    navController.navigate("Playground") {
                        launchSingleTop = true
                    }
                }) {
                    Text(text = "Playground로 이동")
                }
            }
        }
        composable("Argument/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            Text(text = "userId: $userId")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    JC05_NavigationTheme {
        MyNav()
    }
}