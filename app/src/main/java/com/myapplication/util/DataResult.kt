package com.myapplication.util

import com.myapplication.network.model.RestApiResponse


/**
 * Handles api data and based on api executions returns classes such as Success, Error,
 * Loading, Empty.
 */

sealed class DataResult {
    class Success(val data: RestApiResponse) : DataResult()
    class Error(val message: String) : DataResult()
    object Empty : DataResult()
    object Loading : DataResult()
}