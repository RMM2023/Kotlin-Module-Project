class Archive(val name: String) {
    val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun viewNotes() {
        if (notes.isEmpty()) {
            println("Нет доступных заметок в архиве $name.")
        } else {
            notes.forEachIndexed { index, note ->
                println("$index. ${note.name}")
            }

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
}