package com.panassevich.composition.domain.usecases

import com.panassevich.composition.domain.entity.GameSettings
import com.panassevich.composition.domain.entity.Level
import com.panassevich.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}