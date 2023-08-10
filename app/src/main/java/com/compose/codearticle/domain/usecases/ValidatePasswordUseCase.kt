package com.compose.codearticle.domain.usecases

import com.compose.codearticle.data.utilities.Constants.VALID_PASSWORD_REGEX
import com.compose.codearticle.domain.models.ValidateResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password:String):ValidateResult{
        if (password.isBlank())
            return ValidateResult(error = "Please enter password")
        if (!password.matches(VALID_PASSWORD_REGEX))
            return ValidateResult(error = "Please enter valid password")
        return ValidateResult()
    }
}