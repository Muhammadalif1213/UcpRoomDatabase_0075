package com.example.ucp2_075.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

object DestinasiInsertSup : AlamatNavigasi{
    override val route: String = "insert_sup"
}