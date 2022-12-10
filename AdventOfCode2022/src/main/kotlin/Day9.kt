import java.awt.Point
import java.nio.file.Files
import kotlin.io.path.Path


fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/test.txt"))
    part1(input)
    part2(input)

}

fun part1(input: List<String>){
    var currentPosTail = Point()
    var currentPosHead = Point()
    val map: HashSet<Point> = hashSetOf(currentPosTail)

    for(i in input){
        val splitted = i.split(" ")
        val direction = splitted[0]
        val steps = splitted[1].toInt()

        for(step in 0 until steps) {
            currentPosTail = Point(currentPosTail)
            currentPosHead = Point(currentPosHead)
            val previousPosHead = Point(currentPosHead)
            when (direction) {
                "U" -> {
                    currentPosHead.y += 1
                }

                "D" -> {
                    currentPosHead.y -= 1
                }

                "R" -> {
                    currentPosHead.x += 1
                }

                "L" -> {
                    currentPosHead.x -= 1
                }

                else -> println("Unknown instruction!")
            }

            val diag = isDiagonal(currentPosHead, currentPosTail)
            val dist = currentPosTail.distance(currentPosHead)
            //println("$dist $diag $direction $currentPosTail")
            if(dist >= 2){
                when (direction) {
                    "U" -> {
                        if(diag){
                            currentPosTail = previousPosHead
                        }else {
                            currentPosTail.y += 1
                        }
                    }

                    "D" -> {
                        if(diag){
                            currentPosTail = previousPosHead
                        }else {
                            currentPosTail.y -= 1
                        }
                    }

                    "R" -> {
                        if(diag){
                            currentPosTail = previousPosHead
                        }else {
                            currentPosTail.x += 1
                        }
                    }

                    "L" -> {
                        if(diag){
                            currentPosTail = previousPosHead
                        }else {
                            currentPosTail.x -= 1
                        }
                    }

                    else -> println("Unknown instruction!")
                }
            }

            map.add(currentPosTail)
        }
    }

    println(map.size)
}

fun part2(input: List<String>){
    var currentPosTail: Array<Point> = Array<Point>(9){Point()}
    var currentPosHead = Point()
    val map: HashSet<Point> = hashSetOf(currentPosHead)

    for(i in input){
        val splitted = i.split(" ")
        val direction = splitted[0]
        val steps = splitted[1].toInt()

        for(step in 0 until steps) {
            currentPosHead = Point(currentPosHead)
            //val previousPosHead = Point(currentPosHead)
            when (direction) {
                "U" -> {
                    currentPosHead.y += 1
                }

                "D" -> {
                    currentPosHead.y -= 1
                }

                "R" -> {
                    currentPosHead.x += 1
                }

                "L" -> {
                    currentPosHead.x -= 1
                }

                else -> println("Unknown instruction!")
            }
            for(p in 0 until currentPosTail.size){
                var previousPosHead = Point(currentPosHead)
                var dist = currentPosTail[p].distance(currentPosHead)
                var diag = isDiagonal(currentPosHead, currentPosHead)
                if(p != 0){
                    previousPosHead = Point(currentPosTail[p-1])
                    dist = currentPosTail[p].distance(currentPosTail[p-1])
                    diag = isDiagonal(currentPosTail[p-1], currentPosTail[p])
                }


                //println("$dist $diag $direction $currentPosTail")
                if(dist >= 2){
                    when (direction) {
                        "U" -> {
                            if(diag){
                                currentPosTail[p] = previousPosHead
                            }else {
                                currentPosTail[p].y += 1
                            }
                        }

                        "D" -> {
                            if(diag){
                                currentPosTail[p] = previousPosHead
                            }else {
                                currentPosTail[p].y -= 1
                            }
                        }

                        "R" -> {
                            if(diag){
                                currentPosTail[p] = previousPosHead
                            }else {
                                currentPosTail[p].x += 1
                            }
                        }

                        "L" -> {
                            if(diag){
                                currentPosTail[p] = previousPosHead
                            }else {
                                currentPosTail[p].x -= 1
                            }
                        }

                        else -> println("Unknown instruction!")
                    }
                }

            }

            map.add(currentPosTail.last())
        }
    }

    println(map.size)
}

fun isDiagonal(head: Point, tail: Point): Boolean{
    return !(tail.x == head.x || tail.y == head.y)
}