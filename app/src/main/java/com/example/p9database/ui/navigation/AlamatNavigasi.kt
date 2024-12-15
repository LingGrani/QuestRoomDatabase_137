package com.example.p9database.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route: String = "home"
}

object DestinasiDetail: AlamatNavigasi {
    override val route: String = "detail"
    const val nimArg = "nim"
    val routeWithArgs = "$route/{$nimArg}"
}

object DestinasiUpdate: AlamatNavigasi {
    override val route: String = "update"
    const val nimArg = "nim"
    val routeWithArgs = "$route/{$nimArg}"
}

object DestinasiInsert: AlamatNavigasi {
    override val route: String = "Insert"
    const val nimArg = "nim"
    val routeWithArgs = "$route/{$nimArg}"
}