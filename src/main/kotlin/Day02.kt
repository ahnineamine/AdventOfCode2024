package org.example

import kotlin.math.abs

fun main() {
    val numberOfSafeReports = readInput("input02").count {
        val report = it.split(" ").map { it.toLong() }
        if (report !in listOf(report.sorted(), report.sortedDescending())) return@count false
        report.windowed(2).all {
            abs(it[0] - it[1]) in 1..3
        }
    }

    println(numberOfSafeReports)
}