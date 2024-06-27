package com.skogkatt.news.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skogkatt.ui.R
import com.skogkatt.ui.pretendard
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun HeaderImage(
    imageUrl: String,
    relativeTime: String,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        GlideImage(
            imageModel = { imageUrl },
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
            imageOptions = ImageOptions(
                colorFilter = ColorFilter.tint(
                    color = Color(0x4D000000),
                    blendMode = BlendMode.Darken,
                )
            ),
            previewPlaceholder = painterResource(id = R.drawable.img_placeholder),
        )
        IconButton(
            onClick = navigateToBack,
            modifier = Modifier.padding(4.dp),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White
            ),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back",
            )
        }
        Text(
            text = relativeTime,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
        )
    }
}

@Preview
@Composable
private fun HeaderImagePreview() {
    HeaderImage(
        imageUrl = "https://media.guim.co.uk/fe3089b924e907625af3b3d3d82a7efae9f20cb7/0_41_3235_1941/500.jpg",
        relativeTime = "10분 전",
        navigateToBack = { },
    )
}
