import java.io.File
class Day2 {
    val data: List<String> = File("src/resources/AOC_2_INPUT").readLines()

    /*
        ----PART 1----
        A = ROCK, B = PAPER, C = SCISSORS
        X = ROCK, Y = PAPER, Z = SCISSORS
        --------------
    */
    private fun playRound1(opponent: Char, me: Char): Int {
        val winningPoint = 6
        val drawPoint = 3
        val defeatPoint = 0
        when (me) {
            'X' -> if (opponent == 'A') return 1 + drawPoint
            else if (opponent == 'B') return 1 + defeatPoint
            else return 1 + winningPoint

            'Y' -> if (opponent == 'A') return 2 + winningPoint
            else if (opponent == 'B') return 2 + drawPoint
            else return 2 + defeatPoint

            'Z' -> if (opponent == 'A') return 3 + defeatPoint
            else if (opponent == 'B') return 3 + winningPoint
            else return 3 + drawPoint
        }
        return 0
    }

    //Iterera över varje sträng (runda), och räkna ut poäng via playRound funktionen
    fun calculateScoreForRound1(data: List<String>) {
        var points = 0
        for (string in data) {
            points += playRound1(string[0], string[2])
        }
        println("The total points you would receive is $points")
    }


    /*
        ----PART 2----
        A = ROCK, B = PAPER, C = SCISSORS
        X = LOSE, Y = DRAW, Z = WIN
        --------------
    */

    private fun playRound2(opponent: Char, condition: Char): Int {
        val winningPoint = 6
        val drawPoint = 3
        val defeatPoint = 0
        when (condition) {
            'X' -> if (opponent == 'A') return 3 + defeatPoint
                else if (opponent=='B') return 1 + defeatPoint
                else return 2 + defeatPoint

            'Y' -> if (opponent=='A') return 1 + drawPoint
                else if (opponent=='B') return 2 + drawPoint
                else return 3 + drawPoint

            'Z' -> if (opponent=='A') return 2 + winningPoint
                else if (opponent=='B') return 3 + winningPoint
                else return 1 + winningPoint
        }
        return 0
    }

    //Iterera över varje sträng (runda), och räkna ut poäng via playRound funktionen
    fun calculateScoreForRound2(data: List<String>) {
        var points = 0
        for (string in data) {
            points += playRound2(string[0], string[2])
        }
        println("The total points you would receive is $points")
    }
}

fun main() {
    val day2 = Day2()
    day2.calculateScoreForRound1(day2.data)
    day2.calculateScoreForRound2(day2.data)
}