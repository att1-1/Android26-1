package chart

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.data.category.DefaultCategoryDataset
import java.io.File

object ChartCreator {

    fun createTopTeamsChart(players: ArrayList<Player>) {
        val teams = ArrayList<String>()

        for (player in players) {
            val teamName = player.team.name

            if (!teams.contains(teamName)) {
                teams.add(teamName)
            }
        }

        val teamCosts = ArrayList<Pair<String, Long>>()

        for (team in teams) {
            var sum = 0L

            for (player in players) {
                if (player.team.name == team) {
                    sum += player.transferCost
                }
            }

            teamCosts.add(Pair(team, sum))
        }

        for (i in 0 until teamCosts.size - 1) {
            for (j in 0 until teamCosts.size - i - 1) {
                if (teamCosts[j].second < teamCosts[j + 1].second) {
                    val temp = teamCosts[j]
                    teamCosts[j] = teamCosts[j + 1]
                    teamCosts[j + 1] = temp
                }
            }
        }

        val dataset = DefaultCategoryDataset()

        var count = 0
        for (teamCost in teamCosts) {
            if (count < 10) {
                val costInMillions = teamCost.second / 1000000
                dataset.addValue(costInMillions, "Transfer cost", teamCost.first)
                count++
            }
        }

        val chart = ChartFactory.createBarChart(
            "Топ 10 команд по трансферной стоимости",
            "Команда",
            "Стоимость трансфера, в миллонах",
            dataset
        )

        val plot = chart.categoryPlot
        val domainAxis = plot.domainAxis
        domainAxis.categoryLabelPositions = CategoryLabelPositions.UP_45

        val file = File("Топ-10 команд с наивысшей суммарной трансферной стоимостью.png")
        ChartUtils.saveChartAsPNG(file, chart, 1400, 700)
    }
}