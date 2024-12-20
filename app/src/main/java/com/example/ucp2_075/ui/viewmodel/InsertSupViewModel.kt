package com.example.ucp2_075.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2_075.data.entity.Supplier
import com.example.ucp2_075.repository.RepoSup

class InsertSupViewModel (private val repoSup: RepoSup) : ViewModel(

){

}

data class SupUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val id_sup: String? = null,
    val namaSup: String? = null,
    val kontakSup: String? = null,
    val alamatSup: String? = null
)


fun SupplierEvent.toSupplierEntity(): Supplier = Supplier(
    id_sup = id_sup,
    namaSup = namaSup,
    kontakSup = kontakSup,
    alamatSup = alamatSup
)

data class SupplierEvent(
    val id_sup: String = "",
    val namaSup: String = "",
    val kontakSup: String = "",
    val alamatSup: String = ""
)
