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

val regex = Regex("[a-h][1-8][a-h][1-8]")
var move = ""
var y1 = 0
var x1 = 0
var y2 = 0
var x2 = 0
var selectedPiece = "W"


fun startGame() {
    println("Pawns-Only Chess")
    println("First Player's name:")
    playerOne = readLine()!!.toString()
    println("Second Player's name:")
    playerTwo = readLine()!!.toString()
}

/**
 * Converts the column letter into a number so data can be pulled about the board matrix.
 */
fun convertLetterToNum(letter: Char): Int {
    return when (letter) {
        'a' -> 1
        'b' -> 2
        'c' -> 3
        'd' -> 4
        'e' -> 5
        'f' -> 6
        'g' -> 7
        'h' -> 8
        else -> 0
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

/**
 * Alternates turns until end of game
 */
fun turn() {
    var gameOn = true

    playersTurn = playerOne
    // Alternates turns until end of game.
    while (gameOn) {
        println("$playersTurn's turn: ")
        move = readLine()!!.toString()


        if (move.matches(regex)) {
            y1 = convertLetterToNum(move[0])
            x1 = move[1].toString().toInt()
            y2 = convertLetterToNum(move[2])
            x2 = move[3].toString().toInt()
            selectedPiece = board[x1][y1]

            if (playersTurn == playerOne) whiteTurn() else blackTurn()

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

fun whiteTurn() {
    if (selectedPiece != "W") return noPawn()

    if (y1 == y2 && (x1 + 1 == x2 || (x1 == 2 && x2 == 4))) {
        if (board[x2][y2] == "B") {
            println("Invalid input")
        } else {
            updatePieceLocation("W")
            playersTurn = playerTwo
        }
    } else println("Invalid input")
}

fun blackTurn() {
    if (selectedPiece != "B") return noPawn()

    if (y1 == y2 && (x1 - 1 == x2 || (x1 == 7 && x2 == 5))) {
        if (board[x2][y2] == "W") {
            println("Invalid input")
        } else {
            updatePieceLocation("B")
            playersTurn = playerOne
        }
    } else println("Invalid Input")
}

fun updatePieceLocation(piece: String) {
    board[x2][y2] = piece
    board[x1][y1] = " "
    displayBoard()
}

fun noPawn() {
    println(
        "No " + if (playersTurn == playerOne) {
            "white pawn"
        } else {
            "black pawn"
        } + " at ${move[0]}$x1"
    )
}

fun main() {
    startGame()
    displayBoard()
    turn()
}

