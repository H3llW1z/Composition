package com.panassevich.composition.domain.repository

import com.panassevich.composition.domain.entity.GameSettings
import com.panassevich.composition.domain.entity.Level
import com.panassevich.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}