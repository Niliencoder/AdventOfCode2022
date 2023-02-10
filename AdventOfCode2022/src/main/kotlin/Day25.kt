import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.math.pow

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input25.txt"))
    var sum: Long = 0L
    for(i in input){
        val rev = i.reversed()
        var snafu = 0L
        for(j in rev.indices){
            if (rev[j].isDigit()){
                snafu += rev[j].digitToInt() * (5.0.pow(j.toDouble())).toLong()
            }else if(rev[j] == '-'){
                snafu -= (5.0.pow(j.toDouble())).toLong()
            }else{
                snafu -= 2*(5.0.pow(j.toDouble())).toLong()
            }
        }

        println(snafu)
        sum += snafu
    }

    println(sum)

    var snafu = ""
    while (sum > 0){
        snafu += sum%5
        sum /= 5
    }

    val rev = snafu.reversed()
    println(rev)
    var tmpSnafu = ""
    var hasCarry = false
    for (i in snafu){
        var j = i
        if (hasCarry) j++
        if (j == '3'){
            tmpSnafu += "="
            hasCarry = true
        }else if (j == '4'){
            tmpSnafu += "-"
            hasCarry = true
        }else{
            if (j == '5'){
                tmpSnafu += 0
                hasCarry = true
            }else{
                tmpSnafu += j
                hasCarry = false
            }

        }
    }
    println("Answer")
    println(tmpSnafu.reversed())
}