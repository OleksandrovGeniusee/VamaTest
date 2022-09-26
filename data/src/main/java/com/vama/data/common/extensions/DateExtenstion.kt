package com.vama.data.common.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DATE_MONTH_FIRST_FORMAT = "MMM dd, yyyy" // Feb 9, 2021
const val DATE_STANDARD_FORMAT = "yyyy-MM-dd" // 2021-02-09

private val UTC_TIMEZONE = TimeZone.getTimeZone("UTC")

fun dateFromStandardString(source: String): Date? =
    source.parseDate(DATE_STANDARD_FORMAT, UTC_TIMEZONE)

fun Date.monthFirstFormat(): String {
    return SimpleDateFormat(
        DATE_MONTH_FIRST_FORMAT,
        Locale.getDefault()
    ).format(this)
}

fun String.parseDate(formatPattern: String, timeZone: TimeZone = TimeZone.getDefault()): Date? =
    SimpleDateFormat(formatPattern, Locale.getDefault())
        .apply { setTimeZone(timeZone) }
        .parse(this)