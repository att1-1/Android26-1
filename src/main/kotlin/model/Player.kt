package model

class Player(
    var name: String,
    var team: Team,
    var position: String,
    var nationality: String,
    var agency: String,
    var transferCost: Long,
    var participations: Int,
    var goals: Int,
    var assists: Int,
    var yellowCards: Int,
    var redCards: Int
)