package com.krasouski.domain

import kotlinx.coroutines.flow.Flow

interface ColorRepository {
    fun getSavedColor(): Flow<ColorType>
    suspend fun saveColor(color: ColorType)
}

class GetSavedColorUseCase(
    private val repository: ColorRepository
) {
    operator fun invoke(): Flow<ColorType> = repository.getSavedColor()
}