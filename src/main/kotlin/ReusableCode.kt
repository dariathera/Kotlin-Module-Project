import java.util.Scanner

class ReusableCode {

    companion object {

        fun getCommandNumberForCommandMenu(): Int? {
            println("Для продолжения работы введите номер команды")

            val command = Scanner(System.`in`).nextLine()
            val commandNumber = command.toIntOrNull()
            if (commandNumber == null) {
                println("Вам следует ввести номер команды")
                return null
            }
            if (commandNumber !in 0..2) {
                println("Недопустимый номер команды.")
                return null
            }
            return commandNumber
        }

        fun startContentMenu(
            printMenu: () -> Unit,
            map: MutableMap<String, out Any>,
            printContent: (String) -> Unit
        ) {
            do {
                println()
                printMenu()
                val command = Scanner(System.`in`).nextLine()
                val commandNumber = command.toIntOrNull()
                if (commandNumber == null) {
                    println("Вам следует ввести номер команды")
                    continue
                }
                if (commandNumber !in 0..map.size) {
                    println("Недопустимый номер команды.")
                    continue
                }
                when (commandNumber) {
                    0 -> {
                        println("Вы вышли из меню просмотра")
                        break
                    }
                    else -> {
                        var targetKey: String = ""
                        var i = 1
                        for ((key, value) in map) {
                            if (i == commandNumber) {
                                targetKey = key
                                break
                            }
                            i++
                        }
                        printContent(targetKey)
                    }
                }
            } while (true)
        }
    }
}
