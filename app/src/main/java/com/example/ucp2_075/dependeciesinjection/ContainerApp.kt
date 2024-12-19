package com.example.ucp2_075.dependeciesinjection

import android.content.Context
import com.example.ucp2_075.data.database.TokoDatabase

import com.example.ucp2_075.repository.LocalRepoBrg
import com.example.ucp2_075.repository.LocalRepoSup
import com.example.ucp2_075.repository.RepoBrg
import com.example.ucp2_075.repository.RepoSup


interface InterfaceContainerApp{
    val repositoryBrg: RepoBrg
    val repositorySup: RepoSup
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryBrg: RepoBrg by lazy {
        LocalRepoBrg(TokoDatabase.getDatabase(context).barangDao())
    }
    override val repositorySup: RepoSup by lazy{
        LocalRepoSup(TokoDatabase.getDatabase(context).supplierDao())
    }

}