// Simple Kotlin Console-Based Library Management System

data class Book(
    val id: Int,
    var title: String,
    var author: String,
    var isIssued: Boolean = false
)

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
        println("Book added successfully!")
    }

    fun viewBooks() {
        if (books.isEmpty()) {
            println("No books available.")
            return
        }

        println("\nLibrary Books:")
        books.forEach {
            println("ID: ${it.id}, Title: ${it.title}, Author: ${it.author}, Issued: ${it.isIssued}")
        }
    }

    fun issueBook(id: Int) {
        val book = books.find { it.id == id }
        if (book != null && !book.isIssued) {
            book.isIssued = true
            println("Book issued successfully!")
        } else {
            println("Book not available for issuing.")
        }
    }

    fun returnBook(id: Int) {
        val book = books.find { it.id == id }
        if (book != null && book.isIssued) {
            book.isIssued = false
            println("Book returned successfully!")
        } else {
            println("Invalid return operation.")
        }
    }

    fun removeBook(id: Int) {
        val removed = books.removeIf { it.id == id }
        if (removed) {
            println("Book removed successfully!")
        } else {
            println("Book not found.")
        }
    }
}

fun main() {
    val library = Library()

    while (true) {
        println("""
            \n===== Library Management System =====
            1. Add Book
            2. View Books
            3. Issue Book
            4. Return Book
            5. Remove Book
            6. Exit
        """.trimIndent())

        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Enter Book ID: ")
                val id = readLine()?.toIntOrNull() ?: return

                print("Enter Title: ")
                val title = readLine() ?: ""

                print("Enter Author: ")
                val author = readLine() ?: ""

                library.addBook(Book(id, title, author))
            }
            2 -> library.viewBooks()
            3 -> {
                print("Enter Book ID to issue: ")
                val id = readLine()?.toIntOrNull() ?: return
                library.issueBook(id)
            }
            4 -> {
                print("Enter Book ID to return: ")
                val id = readLine()?.toIntOrNull() ?: return
                library.returnBook(id)
            }
            5 -> {
                print("Enter Book ID to remove: ")
                val id = readLine()?.toIntOrNull() ?: return
                library.removeBook(id)
            }
            6 -> {
                println("Exiting...")
                break
            }
            else -> println("Invalid choice!")
        }
    }
}
