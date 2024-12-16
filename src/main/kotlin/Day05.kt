package org.example

fun main(){
    val (orderingRules, updates) = readInput("input05").run {
       val indexToSplit = indexOfFirst { it == "" }
        subList(0, indexToSplit).map { rule -> rule.split("|").map { it.toInt() } } to subList(indexToSplit + 1, size).map { page -> page.split(",").map { it.toInt() } }
    }

    fun isValidOrder(pages: List<Int>): Boolean {
        val rulesToCheck = orderingRules.filter { pages.containsAll(it) }
        return pages.all { pageNumber ->
            rulesToCheck.filter { pageNumber in it }.all { rule ->
                pages.indexOf(rule.first()) < pages.indexOf(rule.last())
            }
        }
    }

    fun orderPages(unorderedPages: List<List<Int>>): List<List<Int>> {
        fun topologicalSort(pages: List<Int>): List<Int> {
            // Create a graph of dependencies
            val graph = pages.associateWith { mutableSetOf<Int>() }.toMutableMap()
            val inDegree = pages.associateWith { 0 }.toMutableMap()

            // Populate the graph based on ordering rules
            orderingRules.filter { it.all { page -> pages.contains(page) } }.forEach { rule ->
                val (before, after) = rule.first() to rule.last()
                if (graph[before]?.add(after) == true) {
                    inDegree[after] = inDegree[after]!! + 1
                }
            }

            // Topological sorting using Kahn's algorithm
            val queue = ArrayDeque(pages.filter { inDegree[it] == 0 })
            val result = mutableListOf<Int>()

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                result.add(current)
                graph[current]?.forEach { neighbor ->
                    inDegree[neighbor] = inDegree[neighbor]!! - 1
                    if (inDegree[neighbor] == 0) {
                        queue.addLast(neighbor)
                    }
                }
            }
            return if (result.size == pages.size) result else pages
        }

        return unorderedPages.map { topologicalSort(it) }
    }

    val (correctlyOrdered, incorrectlyOrdered) = updates.partition(::isValidOrder)

    println(correctlyOrdered.sumOf { it.elementAt(it.size / 2)})

    println(orderPages(incorrectlyOrdered).sumOf { it.elementAt(it.size / 2)})
}