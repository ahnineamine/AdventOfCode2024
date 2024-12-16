package org.example

fun main(){
    val grid = readInput("input04")
    val patterns = listOf(listOf("M","S","S","M"), listOf("M","M","S","S"), listOf("S","M", "M", "S"), listOf("S","S","M","M"))

    val xDirections = listOf(
        -1 to -1,
        -1 to 1,
        1 to 1,
        1 to -1
    )

    fun checkPattern(centerRow: Int, centerCol: Int, directions: List<Pair<Int, Int>>): Boolean {
        if(directions.any { (dx, dy) ->
            val row = centerRow + dx
            val col = centerCol + dy
            row !in grid.indices || col !in grid[row].indices
        } || grid[centerRow][centerCol] != 'A') return false

        return patterns.any{ pattern ->
            directions.zip(pattern).all { (direction, letter) ->
                val (dx, dy) = direction
                val row = centerRow + dx
                val col = centerCol + dy
                grid[row][col].toString() == letter
            }
        }
    }


    val count = grid.indices.sumOf { row ->
        grid[0].indices.count { column ->
            checkPattern(row, column, xDirections)
        }
    }

    println(count)
}