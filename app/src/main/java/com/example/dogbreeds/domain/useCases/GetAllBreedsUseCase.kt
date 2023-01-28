package com.example.dogbreeds.domain.useCases

import android.app.Application
import com.example.dogbreeds.data.common.Resource
import com.example.dogbreeds.domain.model.Breed
import com.example.dogbreeds.domain.repository.BreedsRepository
import com.example.dogbreeds.utils.Utils.checkNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * Get the breeds list either from api or from cash(room database)
 */
class GetAllBreedsUseCase @Inject constructor(
    private val repository: BreedsRepository,
    private val context: Application
) {

    operator fun invoke(): Flow<Resource<List<Breed>>> = flow {
        var breeds = repository.getAllBreeds()
        if (breeds.isEmpty()) {
            emit(Resource.Loading())
            try {
                val isNetworkConnected = context.checkNetwork
                if (isNetworkConnected) {
                    emit(Resource.Loading())
                    repository.refreshList()
                    breeds = repository.getAllBreeds()
                    if (breeds.isNotEmpty()) {
                        emit(Resource.Success(breeds))
                    }
                }

            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error!"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }

        }else {
            emit(Resource.Success(breeds))
        }

    }
}