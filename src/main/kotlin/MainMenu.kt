import java.util.Scanner

class MainMenu {
    val scanner = Scanner(System.`in`)
    val archiveMenu = ArchiveMenu(this)
    val noteMenu = NoteMenu(this, archiveMenu.archives)

    val menu = Menu(
            listOf(
                    "Меню Архивов" to { archiveMenu.start() },
                    "Меню Заметок" to { noteMenu.start() },
                    "Выход" to { exit() }
            )
    )

    fun start(scanner: Scanner) {
        while (true) {
            menu.display()
            val choice = menu.getUserChoice(scanner)
            menu.executeChoice(choice)
        }
    }

    fun exit() {
        println("Выход...")
        System.exit(0)
    }
}