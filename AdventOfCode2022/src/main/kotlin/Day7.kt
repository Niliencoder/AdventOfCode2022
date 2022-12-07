import java.nio.file.Files
import kotlin.io.path.Path

val leafs: MutableList<Directory> = mutableListOf()
var sum = 0
var filesystemSize = 70000000
val minFreeSize = 30000000
val freeSpace: MutableList<Int> = mutableListOf()

class Directory(val name: String, val parent: Directory?){
    val files: HashMap<String, Int> = hashMapOf()
    val dirs: HashSet<Directory> = hashSetOf()
    var size: Int = 0

}

fun main() {
    val input: List<String> = Files.readAllLines(Path("src/main/resources/input7.txt"))

    val dir = Directory("/", null)
    var currentDir: Directory? = dir
    var currentCommand = ""

    for (i in input) {
        val splitted = i.split(" ")

        if (i.contains("\$ cd")) {
            currentCommand = "cd"
            if (splitted[2] == "..") {
                currentDir = currentDir!!.parent
                continue
            }
            if (getDirFromName(currentDir!!, splitted[2]) != null) {
                currentDir = getDirFromName(currentDir, splitted[2])
            }

            continue
        }
        if (i.contains("\$ ls")) {
            currentCommand = "ls"
            continue
        }

        if (currentCommand == "ls") {
            if (splitted[0] != "dir") {
                currentDir!!.files.set(splitted[1], splitted[0].toInt())
            } else {
                if (getDirFromName(currentDir!!, splitted[1]) == null) {
                    currentDir.dirs.add(Directory(splitted[1], currentDir))
                }

            }

        }
    }

    traverse(dir)

    for (leaf in leafs) {
        traverseBack(leaf, 0)
    }

    traverseCount(dir)
    println(sum)

    //Part 2
    filesystemSize -= dir.size
    findPerfectFit(dir)
    freeSpace.sort()
    println(freeSpace[0])

}

fun getDirFromName(dir: Directory, name: String): Directory?{
    for(d in dir.dirs){
        if(d.name == name){
            return d
        }
    }

    return null
}

fun traverse(dir: Directory){
    if (dir.dirs.isEmpty()){
        leafs.add(dir)
    }

    for(d in dir.dirs){
        traverse(d)
    }
}

fun traverseCount(dir: Directory){
    if(dir.size <= 100000){
        sum += dir.size
    }
    for(d in dir.dirs){
        traverseCount(d)
    }
}

fun traverseBack(dir: Directory?, amount: Int): Int{
    var tmpAmount = amount

    if(dir!!.size == 0){
        for(file in dir!!.files){
            tmpAmount += file.value
        }
        dir.size = tmpAmount
    }else{
        dir.size += tmpAmount
    }

    if(dir.parent == null){
        return tmpAmount
    }

    return traverseBack(dir.parent, tmpAmount)
}

fun findPerfectFit(dir: Directory){
    if((filesystemSize + dir.size) >= minFreeSize){
        freeSpace.add(dir.size)
        for(d in dir.dirs){
            findPerfectFit(d)
        }
    }
}