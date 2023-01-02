import java.awt.Point
import java.nio.file.Files
import kotlin.io.path.Path

private val map: HashMap<Point, Char> = HashMap()
private var map2: HashMap<Point, Char> = HashMap()

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input14.txt"))

    for(i in input){
        val splitted = i.split(" -> ")
        var lastX = Int.MIN_VALUE
        var lastY = Int.MIN_VALUE

        for(s in splitted){
            val split = s.split(",")
            val x = split[0].toInt()
            val y = split[1].toInt()

            if (lastX == Int.MIN_VALUE && lastY == Int.MIN_VALUE){
                lastX = x
                lastY = y
                continue
            }

            fillMap(lastX, lastY, x ,y )
            lastX = x
            lastY = y
        }
    }
    map2 = map.toMap() as HashMap<Point, Char>

    var canPlaceSandUnit = true
    while (canPlaceSandUnit){
        canPlaceSandUnit = placeSandUnit()
    }

    println(map.count { it.value == 'o' })

    //Part 2
    val floorY = map2.maxOf { it.key.y} + 2
    canPlaceSandUnit = true
    while (canPlaceSandUnit){
        canPlaceSandUnit = placeSandUnitWithFloor(floorY)
    }
    println(map2.count { it.value == 'o' })
}

private fun placeSandUnit(): Boolean{
    var nextPoint = Point(500, 0)
    var counter = 0
    while (counter < 1000){
        if (!map.contains(Point(nextPoint.x, nextPoint.y+1))){
            nextPoint.y++
        }else{
            if (!map.contains(Point(nextPoint.x-1, nextPoint.y+1))){
                nextPoint = Point(nextPoint.x-1, nextPoint.y+1)
            }else if (!map.contains(Point(nextPoint.x+1, nextPoint.y+1))){
                nextPoint = Point(nextPoint.x+1, nextPoint.y+1)
            }else{
                map[nextPoint] = 'o'
                return true
            }
        }
        counter++
    }

    return false
}

private fun placeSandUnitWithFloor(floor: Int): Boolean{
    var nextPoint = Point(500, 0)
    var counter = 0
    while (counter < 10000){
        if(map2.contains(Point(500,0))) return false
        if(nextPoint.y >= floor){
            nextPoint.y = floor-1
            map2[nextPoint] = 'o'
            return true
        }

        if (!map2.contains(Point(nextPoint.x, nextPoint.y+1))){
            nextPoint.y++
        }else{
            if (!map2.contains(Point(nextPoint.x-1, nextPoint.y+1))){
                nextPoint = Point(nextPoint.x-1, nextPoint.y+1)
            }else if (!map2.contains(Point(nextPoint.x+1, nextPoint.y+1))){
                nextPoint = Point(nextPoint.x+1, nextPoint.y+1)
            }else{
                map2[nextPoint] = 'o'
                return true
            }
        }
        counter++
    }

    return false
}

private fun fillMap(lastX: Int, lastY: Int, x: Int, y: Int) {
    val diffX = x - lastX
    val diffY = y - lastY

    if(diffX != 0){
        if(diffX > 0){
            for (i in lastX..x){
                map[Point(i,y)] = '#'
            }
        }else{
            for (i in lastX downTo x){
                map[Point(i,y)] = '#'
            }
        }
    }

    if(diffY != 0){
        if(diffY > 0){
            for (i in lastY..y){
                map[Point(x,i)] = '#'
            }
        }else{
            for (i in lastY downTo y){
                map[Point(x,i)] = '#'
            }
        }
    }
}
