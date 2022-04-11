package com.myapplication.domain

import com.myapplication.network.ApiService
import com.myapplication.network.model.RestApiResponse
import com.myapplication.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Connects to RestApi and provides data for ViewModel
 */

class AppRepository  @Inject constructor (private val api: ApiService) {

    suspend fun getItems(): Flow<DataResult> = flow {
        emit(DataResult.Loading)
        try {
            val result = api.getList()
            if (result.isSuccessful) {
                val resultBody = result.body()
                if (resultBody != null && resultBody.isNotEmpty()) {
                    emit(DataResult.Success(resultBody))
                } else {
                    emit(DataResult.Empty)
                }
            } else {
                emit(DataResult.Error(generateErrorMessage(result)))
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e.message ?: "An unknown error occurred..."))
        }
    }

    companion object {
        fun generateErrorMessage(result: Response<RestApiResponse>) =
            "Code: ${result.code()} - Error: ${result.message()} - ${result.errorBody()}"
    }
}