package com.compose.codearticle.domain.usecases

import com.compose.codearticle.domain.repositories.UserRepository
import javax.inject.Inject

class IsUserSplashUseCase @Inject constructor(private val userRepository: UserRepository) {
      suspend operator fun invoke(): Boolean {
        return userRepository.getUserToken().isNullOrBlank()
    }
}