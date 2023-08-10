package com.compose.codearticle.domain.usecases

import android.util.Patterns
import com.compose.codearticle.domain.models.ValidateResult
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    operator fun invoke(email: String): ValidateResult {
        if (email.isBlank())
            return ValidateResult(error = "Please enter email")
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidateResult(error = "Please enter valid email")
        return ValidateResult()
    }
}