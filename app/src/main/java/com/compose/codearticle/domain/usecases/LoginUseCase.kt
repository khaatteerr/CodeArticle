package com.compose.codearticle.domain.usecases

import com.compose.codearticle.data.utilities.isFieldDataValid
import com.compose.codearticle.domain.models.UserModel
import com.compose.codearticle.domain.repositories.UserRepository
import com.compose.codearticle.domain.utilities.InvalidInputTextException
import com.compose.codearticle.domain.utilities.UserLoggedInException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {
    suspend operator fun invoke(email:String,password:String):UserModel{
        if (!userRepository.getUserToken().isNullOrBlank())
            throw UserLoggedInException()
        validateFields(email, password)
        return userRepository.login(email, password)
    }

    private fun validateFields(email: String, password: String) {
        validateEmail(email)
        validatePassword(password)
    }
    private fun validateEmail(email: String){
        val validateEmailResult = validateEmailUseCase(email = email)
        if (!validateEmailResult.isFieldDataValid()) throw InvalidInputTextException(
            validateEmailResult.error ?: ""
        )
    }

    private fun validatePassword(password: String){
        val validatePasswordResult = validatePasswordUseCase(password = password)
        if (!validatePasswordResult.isFieldDataValid()) throw InvalidInputTextException(
            validatePasswordResult.error ?: ""
        )
    }

}