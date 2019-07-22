package com.fioalpha.dogshows.data.repository

import com.fioalpha.dogshows.data.repository.localdatasource.LocalDatasource
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private val localDatasource: LocalDatasource = mockk()

    private val remoteDataSource: RemoteDataSource = mockk()

    private lateinit var repository: Repository

    @Before
    fun setup() {
        repository = RepositoryImpl(
            localDatasource,
            remoteDataSource
        )
    }
}