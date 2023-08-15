package com.compose.codearticle.domain.usecases

import com.compose.codearticle.data.utilities.Constants.MINIMUM_USER_NAME_LENGTH
import com.compose.codearticle.domain.models.ValidateResult
import javax.inject.Inject

class ValidateUserNameUseCase @Inject constructor() {
    operator fun invoke(name: String): ValidateResult {
        if (name.isBlank())
            return ValidateResult(error = "Please enter name")
        if (name.length < MINIMUM_USER_NAME_LENGTH)
            return ValidateResult(error = "Please enter valid name")
        return ValidateResult()
    }
}