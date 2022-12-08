import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.math.max

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input8.txt"))
    val trees = Array(input.size){IntArray(input[0].length){0} }

    for(i in trees.indices){
        for (j in trees[0].indices){
            trees[i][j] = input[i][j].toString().toInt()
        }
    }

    var sumVisible = trees.size*2 + trees[0].size*2 - 4
    var scenicScore = 0

    for(i in 1 until trees.size-1){
        for (j in 1 until trees[0].size-1){
            if(isVisible(trees, i, j)){
                sumVisible++
                scenicScore = max(scenicScore, calcScenicScore(trees, i, j))
            }

        }
    }

    println(sumVisible)
    println(scenicScore)
}

fun isVisible(trees: Array<IntArray>, x: Int, y: Int): Boolean{
    var visible = true

    for(i in x+1 until trees.size){
        if (trees[i][y] >= trees[x][y]) visible = false
    }

    if(visible){
        return true
    }
    visible = true
    for(i in x-1 downTo 0){
        if (trees[i][y] >= trees[x][y]) visible = false
    }

    if(visible){
        return true
    }
    visible = true
    for(i in y+1 until trees[0].size){
        if (trees[x][i] >= trees[x][y]) visible = false
    }

    if(visible){
        return true
    }
    visible = true
    for(i in y-1 downTo 0){
        if (trees[x][i] >= trees[x][y]) visible = false
    }

    return visible
}

fun calcScenicScore(trees: Array<IntArray>, x: Int, y: Int): Int{
    var scenicScore = 1
    var counter = 0
    for(i in x+1 until trees.size){
        counter++
        if (trees[i][y] >= trees[x][y]){
            break
        }
    }
    scenicScore *= counter

    counter = 0
    for(i in x-1 downTo 0){
        counter++
        if (trees[i][y] >= trees[x][y]){
            break
        }
    }
    scenicScore *= counter

    counter = 0
    for(i in y+1 until trees[0].size){
        counter++
        if (trees[x][i] >= trees[x][y]) {
            break
        }
    }
    scenicScore *= counter

    counter = 0
    for(i in y-1 downTo 0){
        counter++
        if (trees[x][i] >= trees[x][y]){
            break
        }
    }
    scenicScore *= counter

    return scenicScore
}