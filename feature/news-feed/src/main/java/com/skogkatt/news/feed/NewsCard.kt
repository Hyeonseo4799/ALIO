package com.skogkatt.news.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skogkatt.ui.R
import com.skogkatt.ui.pretendard
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun NewsCard(
    imageUrl: String,
    title: String,
    relativeTime: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row {
            GlideImage(
                imageModel = { imageUrl },
                modifier = Modifier.size(width = 140.dp, height = 100.dp),
                previewPlaceholder = painterResource(id = R.drawable.img_placeholder)
            )
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = pretendard,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
                Text(
                    text = relativeTime,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = pretendard,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsCardPreview() {
    NewsCard(
        imageUrl = "https://media.guim.co.uk/ae194759ab246c9f9d80ac7ec6209888b31dd133/0_373_5559_3337/500.jpg",
        title = "영국에 대한 투자는 1990 년대 중반 이후 다른 G7 국가를 뒤쫓고 있다고 IPPR은 말합니다.",
        relativeTime = "1시간 전",
        onClick = { },
    )
}
