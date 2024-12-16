package org.example

fun main(){
    val (orderingRules, updates) = readInput("input05").run {
       val indexToSplit = indexOfFirst { it == "" }
        subList(0, indexToSplit).map { rule -> rule.split("|").map { it.toInt() } } to subList(indexToSplit + 1, size).map { page -> page.split(",").map { it.toInt() } }
    }

    val sum = updates.filter { pagesToProduce ->
         val rulesToCheck = orderingRules.filter { pagesToProduce.containsAll(it) }
        pagesToProduce.all { pageNumber ->
            rulesToCheck.filter { pageNumber in it }.all { rule ->
                pagesToProduce.indexOf(rule.first()) < pagesToProduce.indexOf(rule.last())
            }
        }
    }.sumOf { it.elementAt(it.size / 2)}

    println(sum)
}