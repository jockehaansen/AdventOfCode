import java.io.File

class Day6 {
    //läsa in input
    val data:String = File("src/resources/AOC_6_INPUT").readText()
    private val length = data.length

    //hitta 4 chars i följd som inte liknar varandra och ta ut index av den första av dessa fyra chars
    //skapa en substring och kolla om index 1 finns i denna substring?
    //gör om till set för att ta bort ev dubbletter
    fun getIndexForStartOfPacket(data:String) {
        var ss: String
        var set:Set<Char>

        for (i in 0..<length) {
            if (i + 3 < length) {
                ss = data.substring(i, i + 4)
                set = ss.toList().toSet()

                if (ss.length == set.size) {
                    println("packet marker $set at index ${i+4}")
                    break
                }
            }
        }
    }

    fun getIndexForStartOfMessage(data: String){
        var ss: String
        var set:Set<Char>

        for (i in 0..<length) {
            if (i + 13 < length) {
                ss = data.substring(i, i + 14)
                set = ss.toList().toSet()

                if (ss.length == set.size) {
                    println("message marker $set at index ${i+14}")
                    break
                }
            }
        }
    }
}

fun main() {
    val day6 = Day6()
    day6.getIndexForStartOfPacket(day6.data)
    day6.getIndexForStartOfMessage(day6.data)
}