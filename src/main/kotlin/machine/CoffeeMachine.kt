package machine

class CoffeeMachine {
    var water: Int = 400
    var milk: Int = 540
    var coffeeBeans: Int = 120
    var disposableCups: Int = 9
    var money: Int = 550

    enum class STATUS {
        WAITING, BUY, FILL, TAKE, REMAINING, EXIT
    }

    var machineStatus: STATUS = STATUS.WAITING

    fun getInput() = readln()
}

fun main() {
    // Declare a coffee machine
    val coffeeMachine = CoffeeMachine()

    // Loop
    while (coffeeMachine.machineStatus != CoffeeMachine.STATUS.EXIT) {
        // Get action to do on coffee machine
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        when (coffeeMachine.getInput()) {
            // Buy a ticket
            "buy" -> {
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.BUY
                buyCoffee(coffeeMachine)
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.WAITING
            }

            // Fill the machine
            "fill" -> {
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.FILL
                fillMachine(coffeeMachine)
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.WAITING
            }

            // Take cash from machine
            "take" -> {
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.TAKE
                takeMoney(coffeeMachine)
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.WAITING
            }

            // Display the content of coffee machine
            "remaining" -> {
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.REMAINING
                showCoffeeMachineStatus(coffeeMachine)
                coffeeMachine.machineStatus = CoffeeMachine.STATUS.WAITING
            }

            // Exit
            "exit" -> coffeeMachine.machineStatus = CoffeeMachine.STATUS.EXIT
        }
    }
}

fun takeMoney(coffeeMachine: CoffeeMachine) {
    // grab the money
    println("\nI gave you \$${coffeeMachine.money}")

    coffeeMachine.money = 0
}

fun fillMachine(coffeeMachine: CoffeeMachine) {
    print("\nWrite how many ml of water do you want to add: ")
    coffeeMachine.water += coffeeMachine.getInput().toInt()
    print("Write how many ml of milk do you want to add: ")
    coffeeMachine.milk += coffeeMachine.getInput().toInt()
    print("Write how many grams of coffee beans do you want to add: ")
    coffeeMachine.coffeeBeans += coffeeMachine.getInput().toInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    coffeeMachine.disposableCups += coffeeMachine.getInput().toInt()
}

fun buyCoffee(coffeeMachine: CoffeeMachine) {
    print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    when (coffeeMachine.getInput()) {
        // Espresso
        "1" -> prepareCoffee(coffeeMachine, 250, 0, 16, 1, 4)

        // Latte
        "2" -> prepareCoffee(coffeeMachine, 350, 75, 20, 1, 7)

        // Cappuccino
        "3" -> prepareCoffee(coffeeMachine, 200, 100, 12, 1, 6)

        // Return to main menu
        "exit" -> return
    }
}

fun prepareCoffee(
    coffeeMachine: CoffeeMachine,
    water: Int,
    milk: Int,
    coffeeBeans: Int,
    disposableCups: Int,
    money: Int
) {
    // Check if enough water
    if (coffeeMachine.water < water) {
        println("Sorry, not enough water!")
        return
    }

    // Check if enough milk
    if (coffeeMachine.milk < milk) {
        println("Sorry, not enough milk!")
        return
    }

    // Check if enough coffee beans
    if (coffeeMachine.coffeeBeans < coffeeBeans) {
        println("Sorry, not enough coffee beans!")
        return
    }

    // Check if enough disposable cups
    if (coffeeMachine.disposableCups < disposableCups) {
        println("Sorry, not enough disposable cups!")
        return
    }

    // All checks are ok, prepare a coffee
    coffeeMachine.water -= water
    coffeeMachine.milk -= milk
    coffeeMachine.coffeeBeans -= coffeeBeans
    coffeeMachine.disposableCups--
    coffeeMachine.money += money

    println("I have enough resources, making you a coffee!")
}

fun showCoffeeMachineStatus(coffeeMachine: CoffeeMachine) {
    println("\nThe coffee machine has:")
    println("${coffeeMachine.water} ml of water")
    println("${coffeeMachine.milk} ml of milk")
    println("${coffeeMachine.coffeeBeans} g of coffee beans")
    println("${coffeeMachine.disposableCups} disposable cups")
    println("\$${coffeeMachine.money} of money")
}