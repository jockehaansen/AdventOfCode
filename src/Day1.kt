import java.io.File

class Day1 {
    //Reading the input as a List<String>
    val data: List<String> = File("src/resources/AOC_1_INPUT.txt").readLines()
    private var max = 0
    private var current = 0

    /*
        -----PART 1-----
        * Iterate through the list and check if the current line is bigger than the
        previously biggest line, if so replace it.
        ----------------
    */

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

    /*
    -----PART 2-----
        * Join as a string to use Split, and split the lines after a newline
        * Then we split into groups when there is an empty line
        * Since we want to sum this, we map into Int
        * Then we sum the sublists -> sort -> reverse for the largest first -> and then sum the three biggest ones
    */
    fun findCaloriesPartTwo(){
        println("Total calories found by the top three elves: " +
            data.joinToString("\n").split("\n\n")
            .map { it.split("\n") }
            .map { s -> s.map { it.toInt() }
            .sum()}.sorted().reversed().take(3).sum())
    }

    /*
        -----New solution based on inspiration from:-----
        https://github.com/avan1235/advent-of-code-2022/blob/master/src/main/kotlin/Day1.kt
        * Using a user-built feature called groupSeparatedBy
        that takes a separator and a transform
        Since I don't have this function, I will use Split and Map again, but save as a new list to
        get all the data from one function resulting in cleaner and more versatile code
    */

    fun inspiredSolution(data:List<String>){
        val outputData = data.joinToString ("\n").split("\n\n")
            .map { it.split("\n") }
            .map { s -> s.map { it.toInt() } }

        println("Most calories found: (INSP) " + outputData.map { it.sum() }.max())
        println("Total calories found by the top three elves: (INSP) " +
                outputData.map { l -> l.sum() }.sortedDescending().take(3).sum())
    }
}

fun main() {
    val day1 = Day1()
    day1.findCaloriesPartOne()
    day1.findCaloriesPartTwo()
    day1.inspiredSolution(day1.data)
}