package com.devpass.spaceapp.domain

import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import com.devpass.spaceapp.utils.NetworkResult

internal interface LaunchpadDetailUseCase {

    suspend operator fun invoke(id: String): NetworkResult<LaunchpadDetail>
}

internal class LaunchpadDetailUseCaseImpl(
    private val launchpadDetailRepository: LaunchpadDetailRepository
) : LaunchpadDetailUseCase{

    override suspend fun invoke(id: String): NetworkResult<LaunchpadDetail> {
        return if (id.isNotEmpty()){
            launchpadDetailRepository.fetchLaunchpad(id)
        } else{
            NetworkResult.Error(IllegalArgumentException("Launchpad id should not be empty"))
        }
    }

}