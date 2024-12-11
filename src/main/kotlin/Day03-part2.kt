package org.example

fun main(){
    val unionPattern = "do\\(\\)|don't\\(\\)|mul\\(([0-9]{1,3}),([0-9]{1,3})\\)".toRegex()
    var shouldConsider = true
    var sum = 0
    readInput("input03").forEach { line ->
        unionPattern.findAll(line).forEach { matchResult ->
            when(matchResult.value){
                "do()" -> shouldConsider = true
                "don't()" -> shouldConsider = false
                else ->
                    if(shouldConsider) {
                        val (left, right) = matchResult.destructured
                        sum += left.toInt().times(right.toInt())
                    }
            }
        }
    }
    println(sum)
}