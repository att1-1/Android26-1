package resolver

import model.Player
import model.Team

class Resolver(
    var players: ArrayList<Player>
) : IResolver {

    override fun getCountWithoutAgency(): Int {
        var playersWithoutAgency = 0

        for (player in players) {
            if (player.agency == "") {
                playersWithoutAgency++
            }
        }

        return playersWithoutAgency
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        var defenderName = ""
        var maxGoals = -1

        for (player in players) {
            if (player.position == "DEFENDER") {
                if (player.goals > maxGoals) {
                    maxGoals = player.goals
                    defenderName = player.name
                }
            }
        }

        return Pair(defenderName, maxGoals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        var germanPlayerPosition = ""
        var maxTransferCost = -1L

        for (player in players) {
            if (player.nationality == "Germany") {
                if (player.transferCost > maxTransferCost) {
                    maxTransferCost = player.transferCost
                    germanPlayerPosition = player.position
                }
            }
        }

        if (germanPlayerPosition == "GOALKEEPER") {
            return "вратарь"
        }

        if (germanPlayerPosition == "DEFENDER") {
            return "защитник"
        }

        if (germanPlayerPosition == "MIDFIELD") {
            return "полузащитник"
        }

        if (germanPlayerPosition == "FORWARD") {
            return "нападающий"
        }

        return germanPlayerPosition
    }

    override fun getTheRudestTeam(): Team {
        val teams = ArrayList<Team>()

        for (player in players) {
            var teamAlreadyAdded = false

            for (team in teams) {
                if (team.name == player.team.name && team.city == player.team.city) {
                    teamAlreadyAdded = true
                }
            }

            if (!teamAlreadyAdded) {
                teams.add(player.team)
            }
        }

        var rudestTeam = teams[0]
        var maxAverageRedCards = -1.0

        for (team in teams) {
            var redCards = 0
            var playersCount = 0

            for (player in players) {
                if (player.team.name == team.name && player.team.city == team.city) {
                    redCards += player.redCards
                    playersCount++
                }
            }

            val averageRedCards = redCards.toDouble() / playersCount.toDouble()

            if (averageRedCards > maxAverageRedCards) {
                maxAverageRedCards = averageRedCards
                rudestTeam = team
            }
        }

        return rudestTeam
    }
}