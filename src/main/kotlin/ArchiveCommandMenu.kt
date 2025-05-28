import java.util.Scanner

class ArchiveCommandMenu {

    companion object {
        fun start() {
            do {
                println()
                println("Раздел: архивы заметок")
                println("Доступные команды")
                println("0. Выйти из приложения")
                println("1. Просмотреть ранее созданные архивы")
                println("2. Создать новый архив")
                val commandNumber = ReusableCode.getCommandNumberForCommandMenu()
                when (commandNumber) {
                    null -> continue
                    0 -> {
                        println("Вы вышли из приложения")
                        break
                    }
                    2 -> {
                        println("Введите название нового архива")
                        val name = Scanner(System.`in`).nextLine().trim()
                        if (name.isEmpty()) {
                            println("Название архива не может быть пустым")
                            continue
                        }
                        if (content.containsKey(name)) {
                            println("Архив с таким именем уже существует")
                            continue
                        }
                        content.put(name, LinkedHashMap())
                        println("Создан новый архив с именем \"$name\"")

                    }
                    // 1 -> ArchiveContentMenu.start()
                    1 -> ReusableCode.startContentMenu(
                        printMenu = {
                            println("Раздел: просмотр ранее созданных архивов")
                            println("Доступные команды")
                            println("0. Вернуться назад")
                            if (content.size == 0) {
                                println("Здесь пока что нет ни одного архива")
                            } else {
                                var i = 1
                                for ((key, value) in content) {
                                    println("$i. Открыть архив \"$key\"")
                                    i++
                                }
                            }
                        },
                        map = content,
                        printContent =  { targetKey -> NoteCommandMenu.start(targetKey) }
                    )
                }
            } while (true)
        }
    }
}
