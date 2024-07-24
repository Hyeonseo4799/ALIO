package com.skogkatt.data.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.math.roundToInt

internal fun String.toTimeAgo(): String {
    val timestamp = Instant.parse(this)
    val now = Clock.System.now()
    val duration = now - timestamp

    val minutes = duration.inWholeMinutes
    val hours = duration.inWholeHours
    val days = duration.inWholeDays
    val weeks = (duration.inWholeDays / 7f).roundToInt()
    val months = (duration.inWholeDays / 30.5f).roundToInt()
    val years = duration.inWholeDays / 365

    return when {
        minutes < 0 -> "방금 전"
        minutes < 60 -> "${minutes}분 전"
        hours < 24 -> "${hours}시간 전"
        days < 7 -> "${days}일 전"
        weeks < 4 -> "${weeks}주 전"
        months < 12 -> "${months}개월 전"
        else -> "${years}년 전"
    }
}