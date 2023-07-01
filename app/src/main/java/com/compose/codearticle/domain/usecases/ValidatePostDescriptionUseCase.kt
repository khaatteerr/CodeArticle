package com.compose.codearticle.domain.usecases

import com.compose.codearticle.domain.models.ValidateResult
import com.compose.codearticle.domain.utilities.Constants.MINIMUM_TITLE_LENGTH
import javax.inject.Inject

class ValidatePostDescriptionUseCase @Inject constructor() {
    operator fun invoke(title: String): ValidateResult {
        if (title.isBlank())
            return ValidateResult(error = "Please enter title")
        if (title.length < MINIMUM_TITLE_LENGTH)
            return ValidateResult(error = "Title is so short")
        return ValidateResult()
    }
}