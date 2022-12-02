import java.nio.file.Files
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input2.txt"))
    var score = 0
    var score2 = 0
    for(i in input){
        val splitted = i.split(" ")
        if(splitted[1] == "X"){
            score += 1
            if(splitted[0] == "A"){
                score += 3
                score2 += 3
            }else if (splitted[0] == "C"){
                score += 6
                score2 += 2
            }else{
                score2 += 1
            }
        }else if(splitted[1] == "Y"){
            score += 2
            score2 += 3
            if(splitted[0] == "B"){
                score += 3
                score2 += 2
            }else if (splitted[0] == "A"){
                score += 6
                score2 += 1
            }else{
                score2 += 3
            }
        }else{
            score += 3
            score2 += 6
            if(splitted[0] == "C"){
                score += 3
                score2 += 1
            }else if (splitted[0] == "B"){
                score += 6
                score2 += 3
            }else{
                score2 += 2
            }
        }
    }

    println(score)
    println(score2)
}