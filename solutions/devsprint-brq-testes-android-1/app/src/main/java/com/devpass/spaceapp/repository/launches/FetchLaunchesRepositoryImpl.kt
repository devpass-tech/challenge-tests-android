package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.OptionsRequest
import com.devpass.spaceapp.data.api.response.QueryParams
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.utils.NetworkResult

class FetchLaunchesRepositoryImpl(
    val api: SpaceXAPIService,
    val mapper: LaunchModelMapper,
) : FetchLaunchesRepository {

    override suspend fun fetchLaunches(): NetworkResult<List<Launch>> {
        return try {
            val response = api.fetchNextLaunches(QueryParams(OptionsRequest(20))).docs
            val launchList = response.map {
                mapper.transformToLaunchModel(it)
            }
            NetworkResult.Success(data = launchList)
        } catch (e: Exception) {
            NetworkResult.Error<Nothing>(exception = e)
            throw RuntimeException(e)
        }
    }
}
