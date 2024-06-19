package com.skogkatt.news.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skogkatt.ui.R
import com.skogkatt.ui.pretendard
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EditorPicksCard(
    imageUrl: String,
    title: String,
    relativeTime: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Box {
            GlideImage(
                imageModel = { imageUrl },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                imageOptions = ImageOptions(
                    colorFilter = ColorFilter.tint(
                        color = Color(0x4D000000),
                        blendMode = BlendMode.Darken,
                    )
                ),
                previewPlaceholder = painterResource(id = R.drawable.img_placeholder),
            )
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = pretendard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Text(
                text = relativeTime,
                modifier = Modifier.padding(24.dp),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = pretendard,
            )
        }
    }
}

@Preview
@Composable
fun EditorPicksCardPreview() {
    EditorPicksCard(
        imageUrl = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg",
        title = "중앙 은행은 대출자들이 구제를 기다리는 동안 금리를 4.35%로 동결합니다.",
        relativeTime = "10분 전",
        onClick = { },
    )
}
