package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2_075.repository.RepoSup

class InsertSupViewModel (private val repoSup: RepoSup) : ViewModel(

){

}



data class SupplierEvent(
    val id_sup: String = "",
    val namaSup: String = "",
    val kontakSup: String = "",
    val alamatSup: String = ""
)
