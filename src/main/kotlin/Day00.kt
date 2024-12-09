package org.example

import kotlin.math.abs

fun main() {
    val firstList =mutableListOf<Long>()
    val secondList =mutableListOf<Long>()
    readInput("input").forEach{ line ->
        line.split(" ").let{
            firstList.add(it.first().toLong())
            secondList.add(it.last().toLong())
        }
    }

    require(firstList.size == secondList.size){
        throw Exception("the two lists must have the same size")
    }

    val sum = firstList.sorted().zip(secondList.sorted()){ x, y ->
        abs(x-y)
    }.sum()

    println(sum)
}

