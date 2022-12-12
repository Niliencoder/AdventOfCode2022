import java.nio.file.Files
import kotlin.io.path.Path

private var sumOfSignals = 0

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input10.txt"))
    var cycle = 0
    var x = 1
    val crt: Array<CharArray> = Array(6){CharArray(40){'.'}}

    for(i in input){
        val splitted = i.split(" ")

        if (splitted.size == 1){
            drawCrt(crt, cycle, x)
            cycle++
            checkCycle(cycle, x)
        }else{
            drawCrt(crt, cycle, x)
            cycle++
            checkCycle(cycle, x)
            drawCrt(crt, cycle, x)
            cycle++
            checkCycle(cycle, x)
            x += splitted[1].toInt()
        }
    }

    println(sumOfSignals)
    println()
    printCrt(crt)
}

fun checkCycle(cycle: Int, x: Int){
    if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220){
        sumOfSignals += cycle*x
    }
}

fun drawCrt(crt: Array<CharArray>, cycle: Int, x: Int){
    if(cycle >= 240) return

    val row = cycle/40
    val col = cycle%40
    if (col == x || col == x-1 || col == x+1){
        crt[row][col] = '#'
    }
}

fun printCrt(crt: Array<CharArray>){
    for (y in crt.indices){
        for(x in crt[y]){
            print(x)
        }
        println()
    }
}