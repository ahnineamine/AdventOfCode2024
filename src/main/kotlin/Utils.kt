package org.example

import java.io.File

fun readInput(filename: String) = File("src/main/kotlin","$filename.txt").readLines()