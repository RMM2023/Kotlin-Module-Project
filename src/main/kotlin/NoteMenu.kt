import java.io.Console
import java.util.Scanner

class NoteMenu(
        private val mainMenu: MainMenu,
        private val archives: List<Archive>
){

    val scanner = Scanner(System.`in`)

    private val menu = Menu(
            listOf(
                    "Создать заметку" to ::createNote,
                    "Просмотреть заметки" to ::viewNotes,
                    "Сохранить заметку в архив" to ::saveNoteToArchive,
                    "Назад" to ::exit
            )
    )

    private val notes = mutableListOf<Note>()

    fun start() {
        while (true) {
            menu.display()
            val choice = menu.getUserChoice(scanner)
            menu.executeChoice(choice)
        }
    }

    private fun createNote() {
        print("Введите название заметки: ")
        val name = readLine() ?: ""

        if (name.isBlank()) {
            println("Ошибка: Название заметки не может быть пустым.")
            return
        }

        print("Введите текст заметки: ")
        val text = readLine() ?: ""

        if (text.isBlank()) {
            println("Ошибка: Содержание заметки не может быть пустым.")
            return
        }

        val newNote = Note(name, text)
        notes.add(newNote)
        println("Заметка создана: $name")
    }

    private fun viewNotes() {
        if (notes.isEmpty()) {
            println("Нет доступных заметок.")
        } else {
            listNotes()

            print("Выберите заметку для просмотра: ")
            val choice = readLine()?.toIntOrNull()

            if (choice != null && choice >= 0 && choice < notes.size) {
                println("Просмотр заметки:")
                println(notes[choice])
            } else {
                println("Ошибка: Некорректный выбор заметки.")
            }
        }
    }

    private fun saveNoteToArchive() {
        if(notes.isEmpty()){
            println("Нет доступных заметок.")
            return
        }
        if(archives.isEmpty()){
            println("Нет доступных архивов")
            return
        }

        listNotes()
        print("Выберите заметку для сохранения: ")

        val noteIndex = readLine()?.toIntOrNull() ?: -1

        if(noteIndex < 0 || noteIndex > notes.size){
            println("Ошибка: Некорректный выбор заметки.")
            return
        }

        listArchives()
        print("Выберите архив для сохранения: ")
        val archiveIndex = readLine()?.toIntOrNull() ?: -1

        if(archiveIndex < 0 || archiveIndex > archives.size){
            println("Ошибка: Некорректный выбор заметки.")
            return
        }

        val note = notes[noteIndex]
        val archive = archives[archiveIndex]

        archive.addNote(note)

        println("Заметка ${note.name} сохранена в архив ${archive.name}")
    }

    private fun listNotes(){
        notes.forEachIndexed { index, note ->
            println("$index. ${note.name}")
        }
    }

    private fun listArchives(){
        archives.forEachIndexed { index, note ->
            println("$index. ${note.name}")
        }
    }

    private fun exit() {
        println("Выход...")
        mainMenu.start(scanner)
    }
}