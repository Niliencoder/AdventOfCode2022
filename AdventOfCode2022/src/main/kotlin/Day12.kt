import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/test.txt"))
    val map: Array<CharArray> = Array(input.size){CharArray(input[0].length)}
    val mapVisited: Array<BooleanArray> = Array(input.size){BooleanArray(input[0].length) {false} }
    var startX = -1
    var startY = -1
    var goalX = -1
    var goalY = -1

    for(y in input.indices){
        for(x in input[y].indices){
            if(input[y][x] == 'S'){
                startX = x
                startY = y
            }
            if(input[y][x] == 'E'){
                goalY = y
                goalX = x
            }
            map[y][x] = input[y][x]
        }
    }

    println("$startX $startY")
    println("$goalX $goalY")
    printMap(map)

    findWay(map, mapVisited, startX, startY, goalX, goalY, 0)
}

fun findWay(map: Array<CharArray>, mapVisited: Array<BooleanArray>, positionX: Int, positionY: Int, goalX: Int, goalY: Int, steps: Int){
    if (mapVisited[positionY][positionX]) return
    mapVisited[positionY][positionX] = true
    var c = map[positionY][positionX]
    println("$c $positionY $positionX")
    if(c == 'E'){
        println(steps)
        return
    }

    if(c == 'S') c = '|'
    for(i in -1..1){
        for(j in -1..1){
            if(i == 0 && j == 0) continue
            if(positionY+i >= map.size || positionY+i < 0) continue
            if(positionX+j >= map[0].size || positionX+j < 0) continue

            if((map[positionY+i][positionX+j].code - c.code <= 1) && !mapVisited[positionY+i][positionX+j]){
                findWay(map, mapVisited,positionY+i, positionX+j, goalX, goalY, steps+1)
            }
        }
    }
}

fun printMap(map: Array<CharArray>){
    for(y in map.indices){
        for(x in map[y].indices){
            print(map[y][x])
        }
        println()
    }
}