package com.gabrielhayon.reddit.client.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun getDifferenceInHours(from: Date, to: Date): Int {
            val differenceInMillis = to.time - from.time
            return millisToHours(differenceInMillis).toInt()
        }

        fun getTimestampDate(timestamp: Long) : Date{
            val calendar = Calendar.getInstance().apply {
                timeZone = TimeZone.getTimeZone("UTC")
                timeInMillis = 1000 * timestamp
            }

            return calendar.time
        }

        private fun millisToHours(millis: Long): Long = millis.div(1000).div(60).div(60)
    }
}