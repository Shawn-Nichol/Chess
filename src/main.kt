package chess

var playerOne = ""
var playerTwo = ""
var playersTurn = playerOne
val board = mutableListOf(
    mutableListOf(" ", "a", "b", "c", "d", "e", "f", "g", "h"),
    mutableListOf("1", " ", " ", " ", " ", " ", " ", " ", " "),
    mutableListOf("2", "W", "W", "W", "W", "W", "W", "W", "W"),
    mutableListOf("3", " ", " ", " ", " ", " ", " ", " ", " "),
    mutableListOf("4", " ", " ", " ", " ", " ", " ", " ", " "),
    mutableListOf("5", " ", " ", " ", " ", " ", " ", " ", " "),
    mutableListOf("6", " ", " ", " ", " ", " ", " ", " ", " "),
    mutableListOf("7", "B", "B", "B", "B", "B", "B", "B", "B"),
    mutableListOf("8", " ", " ", " ", " ", " ", " ", " ", " "),
)

fun startGame() {
    println("Pawns-Only Chess")
    println("First Player's name:")
    playerOne = readLine()!!.toString()
    println("Second Player's name:")
    playerTwo = readLine()!!.toString()
}

fun convertLetterToNum(letter: Char): Int {
    return when (letter) {
        'a' -> {
            1
        }
        'b' -> {
            2
        }
        'c' -> {
            3
        }
        'd' -> {
            4
        }
        'e' -> {
            5
        }
        'f' -> {
            6
        }
        'g' -> {
            7
        }
        'h' -> {
            8
        }
        else -> {
            0
        }
    }
}

fun displayBoard() {
    for (i in board.reversed()) {
        print("  ")
        repeat(8) { print("+---") }
        println("+")
        println(
            if (i != board.first()) {
                i.joinToString(" | ") + " |"
            } else {
                i.joinToString("   ")
            }
        )
    }
}

fun turn() {
    var gameOn = true

    playersTurn = playerOne
    // Alternates turns until end of game.
    while (gameOn) {
        println("$playersTurn's turn: ")
        val move = readLine()!!.toString().toLowerCase()
        val regex = Regex("[a-h][1-8][a-h][1-8]")

        if (move.matches(regex)) {
            val y1 = convertLetterToNum(move[0])
            val x1 = move[1].toString().toInt()
            val y2 = convertLetterToNum(move[2])
            val x2 = move[3].toString().toInt()
            val piece = board[x1][y1]

            if (playersTurn == playerOne && piece == "W") {

                if (y1 == y2 && (x1 + 1 == x2 || (x1 == 2 && x2 == 4))) {
                    if (board[x2][y2] == "B") {
                        println("Invalid Input")
                    } else {
                        board[x2][y2] = "W"
                        board[x1][y1] = " "
                        displayBoard()
                        playersTurn = playerTwo
                    }
                } else println("Invalid Input")
            } else if (playersTurn == playerTwo && piece == "B") {
                if (y1 == y2 && (x1 - 1 == x2 || (x1 == 7 && x2 == 5))) {
                    if (board[x2][y2] == "W") {
                        println("Invalid input")
                    } else {
                        board[x2][y2] = "B"
                        board[x1][y1] = " "
                        displayBoard()
                        playersTurn = playerOne
                    }
                } else println("Invalid Input")
            } else {
                println(
                    "No " + if (playersTurn == playerOne) {
                        "white pawn"
                    } else {
                        "black pawn"
                    } + " at ${move[0]}$x1"
                )
            }
        } else {
            if (move.toLowerCase() == "exit") {
                println("Bye!")
                gameOn = false
            } else {
                println("Invalid Input")
            }
        }
    }
}

fun main() {
    startGame()
    displayBoard()
    turn()
}


