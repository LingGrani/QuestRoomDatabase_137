package com.example.p9database.depedenciesinjection

import android.content.Context
import com.example.p9database.data.database.KrsDatabase
import com.example.p9database.repository.LocalRepositoryMhs
import com.example.p9database.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp (private val context: Context): InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}