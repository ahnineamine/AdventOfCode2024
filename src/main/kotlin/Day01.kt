package org.example

import kotlin.math.abs

fun main() {
    val leftList =mutableListOf<Long>()
    val rightList =mutableListOf<Long>()
    readInput("input01").forEach{ line ->
        line.split(" ").let{
            leftList.add(it.first().toLong())
            rightList.add(it.last().toLong())
        }
    }

    require(leftList.size == rightList.size){
        throw Exception("the two lists must have the same size")
    }

    val sum = leftList.sorted().zip(rightList.sorted()){ x, y ->
        abs(x-y)
    }.sum()

    println(sum)

    val similarityScore = leftList.groupingBy { it }.eachCount().map{ (id, count) ->
        id.times(rightList.count { it == id }).times(count)
    }.sum()

    println(similarityScore)
}

