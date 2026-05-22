import chart.ChartCreator
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.readPlayers()
    val resolver = Resolver(players)

    println("Всего игроков: ${players.size}")
    println()

    println("1. Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")

    val defender = resolver.getBestScorerDefender()
    println("2. Лучший защитник по голам: ${defender.first}, голов: ${defender.second}")

    println("3. Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")

    val team = resolver.getTheRudestTeam()
    println("4. Команда с наибольшим числом удалений на одного игрока: ${team.name}, город: ${team.city}")

    ChartCreator.createTopTeamsChart(players)
}