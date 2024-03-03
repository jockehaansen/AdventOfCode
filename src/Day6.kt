import java.io.File

class Day6 {
    //läsa in input
    val data:String = File("src/resources/AOC_6_INPUT").readText()
    private val length = data.length

    //hitta 4 chars i följd som inte liknar varandra och ta ut index av den första av dessa fyra chars
    //skapa en substring och kolla om index 1 finns i denna substring?
    //gör om till set för att ta bort ev dubbletter

    //PART 1
    fun getIndexForStartOfPacket(data:String) {
        var ss: String
        var set:Set<Char>

        for (i in 0..<length) {
            if (i + 3 < length) {
                ss = data.substring(i, i + 4)
                set = ss.toList().toSet()

                if (ss.length == set.size) {
                    println("Packet marker $set at index ${i+4}")
                    break
                }
            }
        }
    }

    //PART 2
    fun getIndexForStartOfMessage(data: String){
        var ss: String
        var set:Set<Char>

        for (i in 0..<length) {
            if (i + 13 < length) {
                ss = data.substring(i, i + 14)
                set = ss.toList().toSet()

                if (ss.length == set.size) {
                    println("Message marker $set at index ${i+14}")
                    break
                }
            }
        }
    }

    /*
    Inspired by: https://github.com/avan1235/advent-of-code-2022/blob/master/src/main/kotlin/Day6.kt
     */

    fun inspired(data:String, puzzlePart:Int){
        val dataAsChar = data.toCharArray().toList()

        val partOne = dataAsChar.asSequence().windowed(4, 1).mapIndexedNotNull{index, char -> if (char.toSet().size == 4) index + 4 else
        null }.firstOrNull()

        val partTwo = dataAsChar.asSequence().windowed(14, 1).mapIndexedNotNull{index, char -> if (char.toSet().size == 14) index + 14 else
            null }.firstOrNull()

        when(puzzlePart){
            1 -> println("Packet marker at index (INSP) $partOne")
            2 -> println("Message marker at index (INSP) $partTwo")
        }
    }
}

fun main() {
    val day6 = Day6()
    day6.getIndexForStartOfPacket(day6.data)
    day6.getIndexForStartOfMessage(day6.data)
    day6.inspired(day6.data,1)
    day6.inspired(day6.data,2)
}