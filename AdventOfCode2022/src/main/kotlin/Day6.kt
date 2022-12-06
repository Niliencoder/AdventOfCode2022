import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val input: String = Files.readAllLines(Path("src/main/resources/input6.txt")).first()
    findMarker(input, 4)
    findMarker(input, 14)
}

fun findMarker(input: String, size: Int){
    for(i in 0 until input.length-size){
        val sub = input.substring(i, i+size)
        if(isUnique(sub)){
            println("$sub: ${i+size}")
            return
        }
    }
}

fun isUnique(sub: String): Boolean{
    for(c in sub){
        if(sub.count { it == c } > 1){
            return false
        }
    }

    return true
}