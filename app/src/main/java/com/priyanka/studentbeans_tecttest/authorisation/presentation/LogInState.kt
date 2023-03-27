package com.priyanka.studentbeans_tecttest.authorisation.presentation

data class LogInState(
    val userName: String = "",
    val password: String = "",
    val userNameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val signUpError: String? = null,
   val loginError: String? = null,
    val isSuccessLogin: Boolean = false,
    val isLoading: Boolean = false
)
