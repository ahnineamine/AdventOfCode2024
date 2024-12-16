package org.example

fun main(){
    val input = readInput("input06").map { line -> line.toCharArray() }
    val guard = '^'
    val obstacle = '#'
    var currentRow = input.indexOfFirst { guard in it }
    var currentColumn = input[currentRow].indexOfFirst { it == guard }
    var direction = Direction.UP
    val visitedPositions = mutableSetOf(currentRow to currentColumn)


    while(currentRow +  direction.coordinates.first  in input.indices && currentColumn + direction.coordinates.second in input[currentRow].indices){
        val nextRow = currentRow + direction.coordinates.first
        val nextCol = currentColumn + direction.coordinates.second

        if (input[nextRow][nextCol] == obstacle) {
            direction = direction.changeDirection()
        }
        currentRow = nextRow
        currentColumn = nextCol
        visitedPositions.add(currentRow to currentColumn)
    }
    println(visitedPositions.size)
}

// x, y coordinates
enum class Direction(val coordinates: Pair<Int, Int>) {
    UP(-1 to 0),
    DOWN(1 to 0),
    RIGHT(0 to 1),
    LEFT(0 to -1);

    fun changeDirection() = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
}