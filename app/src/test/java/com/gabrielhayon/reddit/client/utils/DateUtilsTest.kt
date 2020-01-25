package com.gabrielhayon.reddit.client.utils

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateUtilsTest {
    @Test
    fun testGetTimestampdate() {
        val actualDate = Calendar.getInstance()
        actualDate.timeZone = TimeZone.getTimeZone("UTC")
        actualDate.time = DateUtils.getTimestampDate(1579757680)
        //01/23/2020 @ 5:34:40am
        assertEquals(2020, actualDate.get(Calendar.YEAR))
        assertEquals(Calendar.JANUARY, actualDate.get(Calendar.MONTH))
        assertEquals(23, actualDate.get(Calendar.DAY_OF_MONTH))
        assertEquals(5, actualDate.get(Calendar.HOUR_OF_DAY))
        assertEquals(34, actualDate.get(Calendar.MINUTE))
        assertEquals(40, actualDate.get(Calendar.SECOND))
    }

    @Test
    fun testGetDifferenceInHours() {
        val fromDate = Calendar.getInstance()
        fromDate.set(2020, 1, 25, 12, 0, 0)

        val toDate = Calendar.getInstance()
        toDate.set(2020, 1, 25, 15, 59, 0)

        assertEquals(3, DateUtils.getDifferenceInHours(fromDate.time, toDate.time))

        toDate.set(2020, 1, 25, 16, 0, 0)

        assertEquals(4, DateUtils.getDifferenceInHours(fromDate.time, toDate.time))
    }
}
