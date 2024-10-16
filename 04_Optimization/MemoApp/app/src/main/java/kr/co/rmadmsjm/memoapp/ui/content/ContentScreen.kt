package kr.co.rmadmsjm.memoapp.ui.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kr.co.rmadmsjm.memoapp.ui.theme.MemoAppTheme

@Composable
fun ContentScreen() {
}

@Composable
fun MemoTitle() {
}

@Composable
fun MemoContent() {
}

@Preview(showSystemUi = true)
@Composable
fun ContentScreenPreview() {
    MemoAppTheme {
        ContentScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MemoTitlePreview() {
    MemoAppTheme {
        MemoTitle()
    }
}

@Preview(showBackground = true)
@Composable
fun MemoContentPreview() {
    MemoAppTheme {
        MemoContent()
    }
}