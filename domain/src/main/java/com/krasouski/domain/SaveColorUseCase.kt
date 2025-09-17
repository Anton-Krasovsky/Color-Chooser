package com.krasouski.domain

class SaveColorUseCase(
    private val repository: ColorRepository
) {
    suspend operator fun invoke(color: ColorType) = repository.saveColor(color)
}