package kr.co.rmadmsjm.jc02_livedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.co.rmadmsjm.jc02_livedata.ui.theme.JC02_LiveDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC02_LiveDataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopLevel(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class ToDoViewModel : ViewModel() {
    // 2. text 상태를 라이브 데이터로 변경
    // 사용하는 측에서 `text.observeAsState()`로 구독
    // val text = mutableStateOf("")
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    val setText: (String) -> Unit = {
        _text.value = it
    }

    // 3. toDoList 상태를 라이브 데이터로 변경
    // 모든 연산에서 List를 새로 만들어 라이브 데이터로 전달해야 함
    // (초 비추!!)
    // val toDoList = mutableStateListOf<ToDoData>()
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>(_rawToDoList)
    val todoList: LiveData<List<ToDoData>> = _toDoList

    // mutableStateListOf: 추가, 삭제 대입 -> UI가 갱신 됨
    //                     각 항목의 필드가 바뀌었을 때 -> 갱신이 안 됨
    // LiveData<List<x>>.observeAsState(): List가 통째로 다른 List로 바뀌었을 때만 State가 갱신됨

    val onSubmit: (String) -> Unit = {
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoData(key, it))
        _toDoList.value = mutableListOf<ToDoData>().also { list ->
            list.addAll(_rawToDoList)
        }
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = newText)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(done = checked)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value = _rawToDoList.toMutableList()
    }
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel = viewModel()
) {
    Scaffold { innerPadding ->
        Column(
            Modifier.padding(innerPadding)
        ) {
            val items = viewModel.todoList.observeAsState(emptyList()).value

            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = {
                    viewModel.setText
                },
                onSubmit = viewModel.onSubmit
            )
            LazyColumn {
                items(
                    items = items,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JC02_LiveDataTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String, onTextChange: (String) -> Unit, onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text, onValueChange = onTextChange, modifier = Modifier.weight(1f)
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
    JC02_LiveDataTheme {
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
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Crossfade(
            targetState = isEditing,
        ) {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text, modifier = Modifier.weight(1f)
                        )
                        Text("완료")
                        Checkbox(checked = toDoData.done, onCheckedChange = { checked ->
                            onToggle(toDoData.key, checked)
                        })
                        Button(onClick = { isEditing = true }) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = { onDelete(toDoData.key) }) {
                            Text("삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (text, setText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = text, onValueChange = setText, modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            isEditing = false
                            onEdit(toDoData.key, text)
                        }) {
                            Text("완료")
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
    JC02_LiveDataTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int, val text: String, val done: Boolean = false
)