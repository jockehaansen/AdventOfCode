import java.io.File
import java.lang.System.exit
import kotlin.system.exitProcess

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

    /*
    Inspirerad lösning: https://github.com/avan1235/advent-of-code-2022/blob/master/src/main/kotlin/Day2.kt
    *Enum inspired solution
    *More versatile and easier to build out
     */

    //Basic enums med ett score value på vilket move man använde
    enum class Move(val score:Int){
        ROCK(1),
        PAPER(2),
        SCISSORS(3)
    }

    enum class Strategy{
        WIN,DRAW,LOOSE
    }

    //Konvertera input chars till respektive move / strategi
    fun Char.toMove(): Move = when(this){
        'A' -> Move.ROCK
        'B' -> Move.PAPER
        'C' -> Move.SCISSORS
        'X' -> Move.ROCK
        'Y' -> Move.PAPER
        'Z' -> Move.SCISSORS
        else -> exitProcess(0)
    }

    fun Char.toStrategy(): Strategy = when(this){
        'X' -> Strategy.LOOSE
        'Y' -> Strategy.DRAW
        'Z' -> Strategy.WIN
        else -> exitProcess(0)
    }


    //Returnerar det move som skulle förlora mot det move som man har passat in (this)
    //Vi skickar in det move vi gör, och får tillbaka det som förlorar mot det
    private val Move.loosingMove: Move
        get() = when (this) {
            Move.ROCK -> Move.SCISSORS
            Move.PAPER -> Move.ROCK
            Move.SCISSORS -> Move.PAPER
        }

    //Returnerar det move som skulle vinna mot det move som man har passat in (this)
    //vi skickar in det move vi gör och returnerar det som vinner mot det
    private val Move.winningMove: Move
        get() = when (this) {
            Move.ROCK -> Move.PAPER
            Move.PAPER -> Move.SCISSORS
            Move.SCISSORS -> Move.ROCK
        }

    //Räkna ut poängen baserat på två moves som passas in
    //Returnerar en poäng som baseras på min move's score + poängen för utkomsten av rundan
    //Är movesen lika returneras en poäng av 3
    //Om motståndarens move är starkare har vi förlorat och returnerar en poäng av 0
    //Annars har vi vunnit, och returnerar en poäng av 6

    /*
        private fun test(opponent: Move, me:Move): Int = me.score + when(me){
        opponent -> 3
        opponent.loosingMove -> 0
        else -> 6
    }
     */

    private infix fun Move.vs(me: Move): Int = me.score + when (me) {
        this -> 3
        this.loosingMove -> 0
        else -> 6
    }

    private infix fun Move.by(strategy: Strategy): Int = when (strategy) {
        Strategy.LOOSE -> this.loosingMove
        Strategy.DRAW -> this
        Strategy.WIN -> this.winningMove
    }.let { strat -> this vs strat }

    /*
        Här summerar vi då poängen av opponent och me där opponents char körs i toMove för att konverteras till ett move
        Sedan kastar vi in det move som vi fått tillbaka i vs tillsammans med min char konverterad i toMove
        Samma sak för strategy
     */
    fun playInspiredRuns(data:List<String>){
        val rounds = data.map { it.split(" ") }

        println(rounds.sumOf { (opponent, me) -> opponent[0].toMove() vs me[0].toMove() })
        println(rounds.sumOf { (opponent, me) -> opponent[0].toMove() by me[0].toStrategy() })
        //println(rounds.sumOf { (opponent, me) -> test(opponent.first().toMove(), me.first().toMove())})

    }
}

fun main() {
    val day2 = Day2()
    day2.calculateScoreForRound1(day2.data)
    day2.calculateScoreForRound2(day2.data)
    day2.playInspiredRuns(day2.data)
}