import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input3.txt"))
    var sumDuplicates = 0
    var sumBadges = 0
    var list: MutableList<HashSet<Char>> = mutableListOf()

    for(i in input){
        val firstHalf = i.substring(0, i.length/2).toHashSet()
        val secondHalf = i.substring(i.length/2, i.length).toHashSet()
        sumDuplicates += calcPriority(firstHalf.intersect(secondHalf).first())

        list.add(i.toHashSet())
        if(list.size == 3){
            sumBadges += calcPriority(list[0].intersect(list[1].intersect(list[2])).first())
            list = mutableListOf()
        }
    }

    println(sumDuplicates)
    println(sumBadges)
}

fun calcPriority(c: Char): Int{
    return if(c.isUpperCase()){
        c.code - 'A'.code + 27
    }else{
        c.code - 'a'.code + 1
    }
}