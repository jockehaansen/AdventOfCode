import java.io.File

class Day3 {
    //Split the lines into two equal size strings
    //Find the common character between these two strings
    //Lowercase item types a through z have priorities 1 through 26.
    //Uppercase item types A through Z have priorities 27 through 52
    //summera alla shared items

    val data: List<String> = File("src/resources/AOC_3_INPUT").readLines()

    //PART 1
    fun splitLines(data: List<String>): List<List<String>> {
        return data.map { string -> string.chunked(string.length / 2) }
    }
    fun findCommonChar(data: List<List<String>>): List<List<Char>> {
        return data.map { list ->
            list.map { string -> string.toSet() }.reduce { acc, set -> acc.intersect(set) }.toList()
        }
    }
    fun flattenList(data: List<List<Char>>):List<Char>{
        return data.fold(emptyList()){acc, list -> acc + list}
    }
    fun calculatePrioValue(data:List<Char>):Int{
        var priorityValue:Int = 0
        for (char in data){
            when(char){
                in 'a'..'z' -> priorityValue += char-'a'+ 1
                in 'A'..'Z' -> priorityValue += char-'A'+ 27
            }
        }
        return priorityValue
    }


    //PART 2
    //gruppera de 3 första strängarna till en lista
    //hitta en gemensam char

    fun groupRugsacks(data: List<String>):List<List<String>>{
        return data.chunked(3)
    }

    fun findCommonBagde(data: List<List<String>>):List<List<Char>>{
        return data.map { list ->
            list.map { string -> string.toSet() }.reduce { acc, set -> acc.intersect(set)}.toList()}
    }
}


    fun main() {
        val day3 = Day3()
        println("The total priority value is: " + day3.calculatePrioValue(day3.flattenList(day3.findCommonChar(day3.splitLines(day3.data)))))
        println("The total badge priority value is: " + day3.calculatePrioValue(day3.flattenList(day3.findCommonBagde(day3.groupRugsacks(day3.data)))))
    }
