import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input3.txt"))
    var sumDuplicates = 0
    var sumBadges = 0
    var list: MutableList<String> = mutableListOf()

    for(i in input){
        val firstHalf = i.substring(0, i.length/2)
        val secondHalf = i.substring(i.length/2, i.length)
        sumDuplicates += findDuplicate(firstHalf, secondHalf)

        list.add(i)
        if(list.size == 3){
            sumBadges += findBadge(list)
            list = mutableListOf()
        }
    }

    println(sumDuplicates)
    println(sumBadges)
}

fun findDuplicate(firstHalf: String, secondHalf: String): Int{
    var priority = 0

    for(c in firstHalf){
        if(secondHalf.contains(c)){
            priority = getPriority(c)
        }
    }

    return priority
}

fun findBadge(rucksacks: List<String>): Int{
    var priority = 0

    for(c in rucksacks[0]){
        if(rucksacks[1].contains(c) && rucksacks[2].contains(c)){
            priority = getPriority(c)
        }
    }

    return priority
}

fun getPriority(c: Char): Int{
    return if(c.isUpperCase()){
        c.code - 'A'.code + 27
    }else{
        c.code - 'a'.code + 1
    }
}