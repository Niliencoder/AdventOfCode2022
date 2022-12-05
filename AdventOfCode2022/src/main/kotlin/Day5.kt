import java.nio.file.Files
import java.util.Stack
import kotlin.io.path.Path

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input5.txt"))
    var stacks = getInput(false)

    for(i in 10 until input.size){
        val splitted = input[i].split(" ")
        val moved = splitted[1].toInt()
        val from = splitted[3].toInt()-1
        val to = splitted[5].toInt()-1

        for(j in 0 until moved){
            stacks[to].push(stacks[from].pop())
        }
    }

    for (s in stacks){
        print(s.peek())
    }
    println()

    stacks = getInput(false)

    for(i in 10 until input.size){
        val splitted = input[i].split(" ")
        val moved = splitted[1].toInt()
        val from = splitted[3].toInt()-1
        val to = splitted[5].toInt()-1

        val list: MutableList<Char> = mutableListOf()

        for(j in 0 until moved){
            list.add(stacks[from].pop())
        }
        for(j in list.reversed()){
            stacks[to].push(j)
        }
    }

    for (s in stacks){
        print(s.peek())
    }
}

fun getInput(isTest: Boolean): List<Stack<Char>>{
    val stacks: MutableList<Stack<Char>> = mutableListOf()
    var stack: Stack<Char> = Stack()

    if(isTest){
        //Test input
        stack.push('Z')
        stack.push('N')
        stacks.add(stack)

        stack = Stack()
        stack.push('M')
        stack.push('C')
        stack.push('D')
        stacks.add(stack)

        stack = Stack()
        stack.push('P')
        stacks.add(stack)

        return stacks;
    }

    //Real input
    stack.push('B')
    stack.push('Q')
    stack.push('C')
    stacks.add(stack)

    stack = Stack()
    stack.push('R')
    stack.push('Q')
    stack.push('W')
    stack.push('Z')
    stacks.add(stack)

    stack = Stack()
    stack.push('B')
    stack.push('M')
    stack.push('R')
    stack.push('L')
    stack.push('V')
    stacks.add(stack)

    stack = Stack()
    stack.push('C')
    stack.push('Z')
    stack.push('H')
    stack.push('V')
    stack.push('T')
    stack.push('W')
    stacks.add(stack)

    stack = Stack()
    stack.push('D')
    stack.push('Z')
    stack.push('H')
    stack.push('B')
    stack.push('N')
    stack.push('V')
    stack.push('G')
    stacks.add(stack)

    stack = Stack()
    stack.push('H')
    stack.push('N')
    stack.push('P')
    stack.push('C')
    stack.push('J')
    stack.push('F')
    stack.push('V')
    stack.push('Q')
    stacks.add(stack)

    stack = Stack()
    stack.push('D')
    stack.push('G')
    stack.push('T')
    stack.push('R')
    stack.push('W')
    stack.push('Z')
    stack.push('S')
    stacks.add(stack)

    stack = Stack()
    stack.push('C')
    stack.push('G')
    stack.push('M')
    stack.push('N')
    stack.push('B')
    stack.push('W')
    stack.push('Z')
    stack.push('P')
    stacks.add(stack)

    stack = Stack()
    stack.push('N')
    stack.push('J')
    stack.push('B')
    stack.push('M')
    stack.push('W')
    stack.push('Q')
    stack.push('F')
    stack.push('P')
    stacks.add(stack)

    return stacks
}