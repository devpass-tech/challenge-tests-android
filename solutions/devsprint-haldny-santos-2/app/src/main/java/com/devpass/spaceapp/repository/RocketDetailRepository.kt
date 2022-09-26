package com.devpass.spaceapp.repository

import com.devpass.spaceapp.model.RocketDetail

interface RocketDetailRepository {
    suspend fun fetchRocketDetail(id: String): RocketDetail
}
