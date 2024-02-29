import java.io.File

class Day1 {

    //läsa in data till en lista
    private val data: List<String> = File("src/resources/AOC_1_INPUT.txt").readLines()
    private var max = 0
    private var current = 0

    fun findCaloriesPartOne() {
        for (i in data) {
            if (i.isEmpty()) {
                if (current > max) {
                    max = current
                    current = 0
                } else current = 0
            } else current += i.toInt()
        }
        println("Most calories found: $max")
    }

    fun findCaloriesPartTwo(){
        println("Total calories found by the top three elves: " +
            data.joinToString("\n").split("\n\n")
            .map { it.split("\n") }
            .map { s -> s.map { it.toInt() }
            .sum()}.sorted().reversed().take(3).sum())
    }
}

fun main() {
    val day1 = Day1()
    day1.findCaloriesPartOne()
    day1.findCaloriesPartTwo()
}