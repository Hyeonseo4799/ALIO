package com.skogkatt.news.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skogkatt.ui.pretendard

@Composable
internal fun SearchBar(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = Color(0xFFF1F1F2),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(10.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
        ),
        singleLine = true,
        decorationBox = @Composable { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.Gray,
                )
                Box(modifier = Modifier.padding(start = 4.dp)) {
                    if (text.isEmpty()) {
                        Text(
                            text = "검색어를 입력하세요.",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    SearchBar(
        text = text,
        onValueChange = { text = it },
    )
}
