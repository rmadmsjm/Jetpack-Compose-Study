package kr.co.rmadmsjm.memoapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.rmadmsjm.memoapp.model.Memo
import kr.co.rmadmsjm.memoapp.model.memos
import kr.co.rmadmsjm.memoapp.ui.theme.MemoAppTheme

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val memoList = remember { memos }
        val onClickMemo: (Int) -> Unit = {
        }

        Column {
            WriteMemo()
            MemoList(
                onClickMemo = onClickMemo,
                memoList = memoList
            )
        }
    }
}

@Composable
fun WriteMemo() {
    val inputMemoValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(100.dp)
    ) {
        OutlinedTextField(
            value = inputMemoValue,
            onValueChange = {},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(text = "작성")
        }
    }
}

@Composable
fun MemoList(
    onClickMemo: (Int) -> Unit,
    memoList: List<Memo>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(
            count = memoList.size
        ) {
            Card(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = "1111",
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MemoAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun WriteMemoPreivew() {
    MemoAppTheme {
        WriteMemo()
    }
}

@Preview(showBackground = true)
@Composable
fun MemoListPreview() {
    MemoAppTheme {
        MemoList(
            {},
            memos
        )
    }
}