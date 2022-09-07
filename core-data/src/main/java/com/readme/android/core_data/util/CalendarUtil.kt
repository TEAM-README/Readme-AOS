package com.readme.android.core_data.util

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtil {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
    private val feedDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)

    fun convertDateToFeedDate(date: String): String {
        val cal = Calendar.getInstance(Locale.KOREA).apply {
            time = requireNotNull(simpleDateFormat.parse(date))
        }

        return feedDateFormat.format(cal.time)
    }
}
