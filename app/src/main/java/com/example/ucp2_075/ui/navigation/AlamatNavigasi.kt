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

object DestinasiListSup: AlamatNavigasi{
    override val route: String = "list_sup"
}

object DestinasiInsertBrg : AlamatNavigasi{
    override val route: String = "insert_brg"
}

object DestinasiListBrg: AlamatNavigasi{
    override val route: String = "list_brg"
}
