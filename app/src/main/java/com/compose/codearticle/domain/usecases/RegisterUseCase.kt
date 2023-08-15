package com.compose.codearticle.domain.usecases

import com.compose.codearticle.data.utilities.isFieldDataValid
import com.compose.codearticle.domain.models.UserModel
import com.compose.codearticle.domain.models.ValidateResult
import com.compose.codearticle.domain.repositories.UserRepository
import com.compose.codearticle.domain.utilities.InvalidInputTextException
import com.compose.codearticle.domain.utilities.UserLoggedInException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): UserModel {
        if (!userRepository.getUserToken().isNullOrBlank())
            throw UserLoggedInException()
        validateFields(username, email, password, confirmPassword)
        return userRepository.register(
            userName = username,
            email = email,
            password = password,
            passwordConfirmation = confirmPassword
        )
    }

    private fun validateFields(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        validateUserName(username)
        validateEmail(email)
        validatePassword(password)
        validateConfirmPassword(password , confirmPassword)
    }


    private fun validateUserName(username: String) {
        val validateUserNameResult = validateUserNameUseCase(username)
        if (!validateUserNameResult.isFieldDataValid()) throw InvalidInputTextException(
            validateUserNameResult.error ?: ""
        )
    }

    private fun validateEmail(email: String) {
        val validateEmailResult = validateEmailUseCase(email)
        if (!validateEmailResult.isFieldDataValid()) throw InvalidInputTextException(
            validateEmailResult.error ?: ""
        )
    }

    private fun validatePassword(password: String) {
        val validatePasswordResult = validatePasswordUseCase(password)
        if (!validatePasswordResult.isFieldDataValid()) throw InvalidInputTextException(
            validatePasswordResult.error ?: ""
        )
    }

    private fun validateConfirmPassword(password: String ,confirmPassword: String) {
        val validateConfirmPasswordResult = validateConfirmPasswordUseCase(password,confirmPassword)
        if(!validateConfirmPasswordResult.isFieldDataValid()) throw InvalidInputTextException(
            validateConfirmPasswordResult.error?:""
        )
    }


}