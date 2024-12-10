package org.example

import kotlin.math.abs

fun main() {

    fun isSafeReport(report: List<Long>) = report in listOf(report.sorted(), report.sortedDescending()) && report.windowed(2).all {
        abs(it[0] - it[1]) in 1..3
    }
    val numberOfSafeReports = readInput("input02").count {
        val report = it.split(" ").map { it.toLong() }

        if(isSafeReport(report)) return@count true

        report.indices.any { indexToRemove ->
            val modifiedReport = report.filterIndexed { index, _ -> index != indexToRemove }
            isSafeReport(modifiedReport)
        }
    }
    println(numberOfSafeReports)
}