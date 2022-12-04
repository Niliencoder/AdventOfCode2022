import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input4.txt"))

    var sum1 = 0
    var sum2 = 0

    for (i in input){
        val splitted = i.split(",")
        val lower1 = splitted[0].split("-")[0].toInt()
        val upper1 = splitted[0].split("-")[1].toInt()

        val lower2 = splitted[1].split("-")[0].toInt()
        val upper2 = splitted[1].split("-")[1].toInt()

        if(lower1 <= lower2 && upper1 >= upper2 || lower1 >= lower2 && upper1 <= upper2){
            sum1++
        }

        val assignments1: List<Int> = (lower1..upper1).toList()
        val assignments2: List<Int> = (lower2..upper2).toList()
        val intersections = assignments1.intersect(assignments2.toSet())
        if(intersections.isNotEmpty()){
            sum2++
        }

    }

    println(sum1)
    println(sum2)
}