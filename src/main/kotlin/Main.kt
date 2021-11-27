import java.util.*

fun main() {
    println("Enter the number of rows:")
    val row = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readLine()!!.toInt()
    val cinema = MutableList(row) { MutableList(seatsPerRow) {'S'} }
    val numOfSeats = row * seatsPerRow
    var soldTickets = 0
    var currentIncome = 0
    var totalIncome = 0
    val firstClass = ((row - 1) / 2) * seatsPerRow
    val secondClass = firstClass + seatsPerRow
    if (numOfSeats <= 60) {
        totalIncome = 10 * numOfSeats
    } else if (numOfSeats > 60) {
        totalIncome = if (row % 2 == 0) {
            ((row / 2 * seatsPerRow) * 8) + ((row / 2 * seatsPerRow) * 10)
        } else {
            (firstClass * 10) + (secondClass * 8)
        }
    }
    println()

    do {
        println(
            """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent()
        )

        val menuSelector = readLine()!!.toInt()
        when (menuSelector) {
            1 -> {println("Cinema:")
                print("  ")
                for (i in 1..seatsPerRow) {
                    print("$i ")
                }
                println()

                for ((i, value) in cinema.withIndex()) {
                    val num = i + 1
                    println("$num " + value.joinToString().replace(",",""))
                }
                println()}
            2 -> {
                println("Enter a row number:")
                var x = readLine()!!.toInt()
                println("Enter a seat number in that row:")
                var y = readLine()!!.toInt()
                println()
                while (x > row || y > seatsPerRow || x <= 0 || y <= 0) {
                    println("Wrong input!")
                    println()
                    println("Enter a row number:")
                    x = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    y = readLine()!!.toInt()

                }
                while (cinema[x-1][y-1] == 'B') {
                    println("That ticket has already been purchased!")
                    println("Enter a row number:")
                    x = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    y = readLine()!!.toInt()
                }

                println()
                cinema[x-1][y-1] = 'B'
                soldTickets ++

                if (numOfSeats <= 60) {
                    println("Ticket price: $10")
                    currentIncome += 10
                } else if (numOfSeats > 60) {
                    currentIncome += if (row % 2 != 0 && x > row / 2) {
                        println("Ticket price: $8")
                        8
                    } else {
                        println("Ticket price: $10")
                        10
                    }
                }}
            3 -> {
                val percentage = 100.0 * soldTickets / (seatsPerRow * row)
                println()
                println("Number of purchased tickets: $soldTickets")
                println("Percentage: " + "%.2f".format(Locale("en", "US"),percentage) + "%")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome")

                println()
            }
        }
    } while (menuSelector != 0)
}