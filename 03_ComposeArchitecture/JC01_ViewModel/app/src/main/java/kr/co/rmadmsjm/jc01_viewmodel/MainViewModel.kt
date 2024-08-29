package kr.co.rmadmsjm.jc01_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// 2. `ViewModel`을 상속받은 `ToDoViewModel` 만들기
// 첫 단계에서는 내용을 비워두고 시작

class MainViewModel: ViewModel() {
    // 4. text, setText를 뷰 모델로 옮기기
    // 뷰 모델의 프로퍼티로 변경할 경우에는 destrunction (비구조화, 구조 분해)는 사용할 수 없음
    // `by` 사용
    // `remember`는 제거해야 함
    var text by mutableStateOf("")

    // 5. `toDoList`, `onSubmit`, `onEdit`, `onToggle`, `onDelete`를 모두 뷰 모델로 옮기기
    val toDoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, it))
        text = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(text = newText)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(i)
    }
}