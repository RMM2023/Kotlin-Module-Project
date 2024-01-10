import java.util.Scanner

class Menu(private val items: List<Pair<String, () -> Unit>>) {

    fun display() {
        println("--------------------------------------------")
        items.forEachIndexed { index, item ->
            println("$index. ${item.first}")
        }
    }

    fun getUserChoice(scanner: Scanner): Int {
        var choice: Int
        while (true) {
            try {
                print("Выберите число: ")
                choice = scanner.nextInt()
                if (choice < 0 || choice >= items.size) {
                    println("Некорректный ввод. Введите доступное число.")
                } else {
                    break
                }
            } catch (e: java.util.InputMismatchException) {
                println("Некорректный ввод. Введите число.")
                scanner.nextLine()
            }
        }
        return choice
    }

    fun executeChoice(choice: Int) {
        items[choice].second.invoke()
    }

    fun clear(){
        repeat(20) {
            println("\n")
        }
    }
}