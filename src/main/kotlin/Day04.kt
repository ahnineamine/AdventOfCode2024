package org.example

fun main(){
    val grid = readInput("input04")
    val word = "XMAS"

    // Possible directions: horizontal, vertical, diagonal, and backwards
    val directions = listOf(
        0 to 1,
        1 to 0,
        1 to 1,
        1 to -1,
        0 to -1,
        -1 to 0,
        -1 to -1,
        -1 to 1
    )

    fun checkWord(startRow: Int, startCol: Int, dx: Int, dy: Int) = word.indices.all { index ->
        val row = startRow + index * dx
        val col = startCol + index * dy

        row in grid.indices && col in grid[row].indices && (grid[row][col] == word[index])
    }


    val count = grid.indices.flatMap { row ->
        grid[0].indices.map { column ->
            directions.count { (dx, dy) ->
                checkWord(row, column, dx, dy)
            }
        }
    }.sum()

    println(count)
}