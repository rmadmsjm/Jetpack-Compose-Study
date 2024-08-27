package kr.co.rmadmsjm.jc15_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.jc15_todoapp.ui.theme.JC15_TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC15_TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Composable
fun TopLevel() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember { mutableStateListOf<ToDoData>() }

    // 4. `onSubmit`, `onEdit`, `onToggle`, `onDelete`를 만들어 `ToDo`에 연결

    val onSubmit: (String) -> Unit = { newText ->
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key = key, text = newText))
        setText("")
    }

    val onEdit: (Int, String) -> Unit = { key, editText ->
        val todoKey = toDoList.indexOfFirst { it.key == key }
        toDoList[todoKey] = toDoList[todoKey].copy(text = editText)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val todoKey = toDoList.indexOfFirst { it.key == key }
        toDoList[todoKey] = toDoList[todoKey].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val todoKey = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(todoKey)
    }

    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ToDoInput(
                text = text,
                onTextChange = setText,
                onSubmit = onSubmit
            )

            // 3. `LazyColumn`으로 `toDoList` 표시
            // `key`는 `toDoData`의 `key`를 사용
            // key 셋팅하면 재활용할 때 효과적으로 랜더링함
            LazyColumn {
                items(toDoList, key = { it.key }) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = onEdit,
                        onToggle = onToggle,
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC15_TodoAppTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    JC15_TodoAppTheme {
        ToDoInput("테스트", {}, {})
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        // 1. `Row`에 `toDoData.text`를 출력하고 완료를 체크하는 체크박스, 수정 버튼, 삭제 버튼 만들기

        // 2. `Crossfade`를 통해 `isEditing`을 따라 다른 UI를 보여주기
        // `OutlinedTextField`와 `Button` 넣기

        Crossfade(
            targetState = isEditing,
            label = "수정"
        ) {
            when(it) {
                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        val (editText, setEditText) = remember { mutableStateOf(toDoData.text) }

                        OutlinedTextField(
                            value = editText,
                            onValueChange = setEditText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = {
                                onEdit(toDoData.key, editText)
                                isEditing = false
                            }
                        ) {
                            Text(text = "완료")
                        }
                    }
                }
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { isEditing = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                tint = Color.Black,
                                contentDescription = "수정하기"
                            )
                        }
                        IconButton(
                            onClick = { onDelete(toDoData.key) }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                tint = Color.Red,
                                contentDescription = "삭제하기"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    JC15_TodoAppTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

// immutable
// -> mutableStateListOf는 추가, 삭제와 같이 변경되었을 때만 UI 갱신되기 때문에 immutable
// 항목 하나의 값을 바꾸는 것보다 항목 자체를 바꾸는 것이 UI 갱신에 더 좋음
data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)