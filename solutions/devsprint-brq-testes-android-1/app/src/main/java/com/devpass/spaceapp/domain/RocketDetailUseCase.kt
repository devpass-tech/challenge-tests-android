package com.devpass.spaceapp.domain

import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.utils.NetworkResult

internal interface RocketDetailUseCase {

    suspend operator fun invoke(id: String): NetworkResult<RocketDetail>
}

internal class RocketDetailUseCaseImpl(
    private val rocketDetailRepository: RocketDetailRepository
) : RocketDetailUseCase {

    override suspend fun invoke(id: String): NetworkResult<RocketDetail> {
        return if (id.isNotEmpty()) {
            rocketDetailRepository.fetchRocketDetail(id)
        } else {
            NetworkResult.Error(IllegalArgumentException("Rocket id should not be empty"))
        }
    }
}