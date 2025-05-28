import java.util.Scanner

class NoteCommandMenu {

    companion object {
        fun start(archiveName: String) {
            do {
                println()
                println("Раздел: архив \"$archiveName\"")
                println("Доступные команды")
                println("0. Вернуться назад")
                println("1. Просмотреть ранее созданные заметки")
                println("2. Создать новую заметку")
                val commandNumber = ReusableCode.getCommandNumberForCommandMenu()
                when (commandNumber) {
                    null -> continue
                    0 -> {
                        println("Вы вышли из архива \"$archiveName\"")
                        break
                    }
                    2 -> {
                        println("Введите название новой заметки")
                        val name = Scanner(System.`in`).nextLine().trim()
                        if (name.isEmpty()) {
                            println("Название заметки не может быть пустым")
                            continue
                        }
                        // Мы бы не попали в текущую функцию, если бы archiveName был null
                        if ((content.get(archiveName))!!.containsKey(name)) {
                            println("Заметка с таким именем уже существует")
                            continue
                        }
                        println("Введите текст заметки одной строкой")
                        val text = Scanner(System.`in`).nextLine().trim()
                        if (text.isEmpty()) {
                            println("Заметка не может быть пустой")
                            continue
                        }
                        // Мы бы не попали в текущую функцию, если бы archiveName был null
                        content.get(archiveName)!!.put(name, text)
                        println("Создана новая заметка с именем \"$name\"")
                    }
                    // 1 -> NoteContentMenu.start(archiveName)
                    1 -> ReusableCode.startContentMenu(
                        printMenu = {
                            println("Раздел: просмотр ранее созданных заметок")
                            println("Доступные команды")
                            println("0. Вернуться назад")
                            if (content.get(archiveName)!!.size == 0) {
                                println("Здесь пока что нет ни одной заметки")
                            } else {
                                var i = 1
                                for ((key, value) in content.get(archiveName)!!) {
                                    println("$i. Показать заметку \"$key\"")
                                    i++
                                }
                            }
                        },
                        map = content.get(archiveName)!!,
                        printContent =  { targetKey ->
                            println()
                            println("Заметка")
                            println("Название: $targetKey")
                            println("Содержание: ${content.get(archiveName)!![targetKey]}")
                            println("Если вы хотите дополнить заметку, введите текст ниже")
                            val text = Scanner(System.`in`).nextLine().trim()
                            if (!text.isEmpty()) {
                                content.get(archiveName)!![targetKey] += " " + text
                                println("Текст добавлен, изменения сохранены")
                            }
                        }
                    )
                }
            } while (true)
        }
    }
}
