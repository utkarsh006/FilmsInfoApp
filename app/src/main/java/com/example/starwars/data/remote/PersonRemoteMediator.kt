package com.example.starwars.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwars.data.db.PersonDB
import com.example.starwars.data.db.PersonEntity
import com.example.starwars.data.mappers.toPersonEntity
import retrofit2.HttpException
import java.io.IOException

/* This class puts our loaded items from the API into our local DB and then juts forwards the page
that we want to load */

@OptIn(ExperimentalPagingApi::class)
class PersonRemoteMediator(
    private val personDB: PersonDB, // local data source
    private val personApi: PersonApi // remote data source
) : RemoteMediator<Int, PersonEntity>() {

    companion object {
        private const val DEFAULT_PAGE = "${PersonApi.BASE_URL}people/"
    }
    // load function is called when there is loading in pagination
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> DEFAULT_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        DEFAULT_PAGE
                    } else {
                        lastItem.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }
            }
            
            // make API Call
            val persons = personApi.getPersons(
                url = loadKey
            )

            // take the list of persons and insert it in our localDB
            personDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    // clear the whole cache
                    personDB.dao.clearAll()
                }
                val personEntities = persons.results.map { it.toPersonEntity(next = persons.next) }
                personDB.dao.upsertAll(personEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = persons.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
