package org.example

fun main(){
    val pattern = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)".toRegex()
    val sumOfMultiplications = readInput("input03").sumOf { line ->
        pattern.findAll(line).sumOf { match ->
            match.groupValues.let {
                it[1].toInt().times(it[2].toInt())
            }
        }
    }

    println(sumOfMultiplications)
}