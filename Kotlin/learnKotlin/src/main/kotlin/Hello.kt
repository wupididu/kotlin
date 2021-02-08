fun main(args: Array<String>){
    val hero : Person = Person()
    val magicMirror: MagicMirror = MagicMirror()
    val tower: Tower = Tower()

    val faction  = when (hero.race){
        "dwarf" -> "Keepers of the Mines"
        "gnome" -> "Keepers of the Mines"
        "orc" -> "Free People of the Rolling Hills"
        else -> "Don't Find"
    }

    val healthStatus = when (hero.healthPoints){
        100 -> "is in excellent condition"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if(hero.isBlessed) {
            "has some minor wounds but is healing quite quickly"
        }else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition"
    }

    val numLetters = healthStatus.count { letter -> letter == 't' }

    val greetingFunction: (String, Int) -> String = { name, year ->
        val currentYear = 2018
        "welcome to SimVillage, $name! (copyright ${currentYear+year})"
    }

    val stupidFunction: (Int) -> Int = { year ->
        val currentYear: Int = 2018
        currentYear + year
    }

    runSimulation("Guyal", ::printConstructionCost){playerName, numBuildings ->
        val  currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copoyright $currentYear)"
    }
 }

fun printConstructionCost(numBuildings: Int){
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}

inline fun runSimulation(playerName: String,
                         costPrinter: (Int)->Unit,
                         greetingFunction: (String, Int) -> String){
    val numBuildings = (1..3).shuffled().last()
    costPrinter(numBuildings)
    println(greetingFunction(playerName,numBuildings))
}

class Person{
    val isBlessed: Boolean = true
    val race = "gnome"
    val healthPoints = 45
    val hasSteed: Boolean = false
    val name: String = "Estragon"
    val countOfCoins: Int = 20
}

class Tower{
    val name: String = "Unicorn Horn"
    val menu: Map<String, Int> =  mapOf("coff" to 4,
        "bear" to 4)
}

class MagicMirror{
    fun reverseName(name:String): String{
        return name.reversed()
    }
}