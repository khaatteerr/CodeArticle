package com.compose.codearticle.domain.usecases

import com.compose.codearticle.domain.models.ValidateResult
import javax.inject.Inject

class ValidateConfirmPasswordUseCase @Inject constructor() {
    operator fun invoke(password:String,confirmPassword:String):ValidateResult{
        if (password != confirmPassword)
            return ValidateResult(error = "Password not match")
        return ValidateResult()
    }
}