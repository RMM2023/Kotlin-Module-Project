import java.util.Scanner

class ArchiveMenu(private val mainMenu: MainMenu) {

    private val scanner = Scanner(System.`in`)

    private val menu = Menu(
            listOf(
                    "Создать Архив" to ::createArchive,
                    "Просмотреть Архивы" to ::viewArchives,
                    "Назад" to ::exit
            )
    )

    public val archives = mutableListOf<Archive>()

    fun start() {
        while (true) {
            menu.display()
            val choice = menu.getUserChoice(scanner)
            menu.executeChoice(choice)
        }
    }

    private fun createArchive() {
        print("Введите имя архива: ")
        val name = readLine() ?: ""

        if (name.isEmpty()) {
            println("Имя архива не может быть пустым!")
        } else {
            val archive = Archive(name)
            archives.add(archive)
            println("Архив $name создан")
        }
    }

    private fun viewArchives() {
        if (archives.isEmpty()) {
            println("Архивы не созданы")
        } else {
            archives.forEachIndexed { index, archive ->
                println("$index. ${archive.name}")
            }

            print("Выберите архив для просмотра: ")
            val index = readLine()?.toIntOrNull() ?: return
            val archive = archives.getOrElse(index) {
                println("Неверный индекс архива")
                return
            }

            archive.viewNotes()
        }
    }

    private fun exit() {
        println("Выход...")
        mainMenu.start(scanner)
    }

}