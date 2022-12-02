import java.nio.file.Files
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input1.txt"))

    val calories: MutableList<Int> = mutableListOf()
    var sum = 0
    for(i in input){
        if(i.isBlank()){
            calories.add(sum)
            sum = 0
            continue
        }

        sum += i.toInt()
    }

    calories.sortDescending()
    println(calories[0])
    println(calories[0]+calories[1]+calories[2])
}