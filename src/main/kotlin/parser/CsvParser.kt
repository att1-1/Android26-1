package parser

import model.Player
import model.Team
import java.io.File

object CsvParser {

    fun readPlayers(): ArrayList<Player> {
        val players = ArrayList<Player>()

        val lines = File("src/main/resources/fakePlayers.csv").readLines()

        for (i in 1 until lines.size) {
            val line = lines[i]
            val parts = line.split(";")

            val team = Team(parts[1], parts[2])

            val player = Player(
                parts[0],
                team,
                parts[3],
                parts[4],
                parts[5],
                parts[6].toLong(),
                parts[7].toInt(),
                parts[8].toInt(),
                parts[9].toInt(),
                parts[10].toInt(),
                parts[11].toInt()
            )

            players.add(player)
        }

        return players
    }
}