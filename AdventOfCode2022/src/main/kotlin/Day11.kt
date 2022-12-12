import java.nio.file.Files
import kotlin.io.path.Path

private class Monkey(val items: MutableList<Long>, val operation: String, val divisible: Long, val forTrue: Int, val forFalse: Int){

    var numOfInspected = 0L

    fun doOperation(){
        var supermodulo = 0L
        for(item in items){
            supermodulo += item
        }

        var splitted = operation.split(" ")
        val tmpItems = items.toList()
        for(item in tmpItems){
            var value: Long
            if(splitted[2] != "old"){
                if(splitted[1] == "+"){
                    value = item + splitted[2].toLong()
                }else{
                    value = item * splitted[2].toLong()
                }
            }else{
                if(splitted[1] == "+"){
                    value = item + item
                }else{
                    value = item * item
                }
            }

            if(isPart1){
                value = value / 3L
            }else{
                //Do math stuff
            }

            if(value%divisible == 0L){
                monkeys[forTrue].items.add(value)
            }else{
                monkeys[forFalse].items.add(value)
            }
            items.remove(item)
            numOfInspected++
        }
    }
}

private var monkeys: MutableList<Monkey> = mutableListOf()
private var isPart1 = true

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input11.txt"))

    for(i in input.indices step 7){
        var splitted = input[i+1].split(":")
        splitted = splitted[1].split(",").map { it.trim() }
        //println(input[i])
        var items: MutableList<Long>  = splitted.map { it.toLong() } as MutableList<Long>
        //println(items)

        splitted = input[i+2].split(":")
        splitted = splitted[1].split("=")
        var operation: String = splitted[1].trim()
        //println(operation)

        splitted = input[i+3].split(":")
        splitted = splitted[1].split(" ")
        var divisible: Long = splitted[3].trim().toLong()
        //println(divisible)

        splitted = input[i+4].split(":")
        splitted = splitted[1].split(" ")
        var forTrue: Int = splitted[4].trim().toInt()
        //println(forTrue)

        splitted = input[i+5].split(":")
        splitted = splitted[1].split(" ")
        var forFalse: Int = splitted[4].trim().toInt()
        //println(forFalse)

        monkeys.add(Monkey(items, operation, divisible, forTrue, forFalse))
    }

    for(round in 0 until 20){
        for (monkey in monkeys){
            monkey.doOperation()
        }
    }

    var tmp = monkeys.sortedByDescending{ it.numOfInspected }
    println(tmp[0].numOfInspected * tmp[1].numOfInspected)

    isPart1 = false

    for(monkey in monkeys){
        monkey.numOfInspected = 0L
    }

    for(round in 0 until 10000){
        for (monkey in monkeys){
            monkey.doOperation()
        }
    }
    tmp = monkeys.sortedByDescending{ it.numOfInspected }
    println(tmp[0].numOfInspected * tmp[1].numOfInspected)
}