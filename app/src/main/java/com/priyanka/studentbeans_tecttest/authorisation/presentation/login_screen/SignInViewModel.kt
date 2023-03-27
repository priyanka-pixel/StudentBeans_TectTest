package com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanka.studentbeans_tecttest.authorisation.data.repository.AuthRepoImpl
import com.priyanka.studentbeans_tecttest.authorisation.presentation.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepoImpl
) : ViewModel() {

    val currentUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()
    var loginState by mutableStateOf(LogInState())
    private set
    fun onUserNameChange(userName: String){
        loginState = loginState.copy(userName = userName)
    }
    fun onPasswordNameChange(password: String){
        loginState = loginState.copy(password = password)
    }
    fun onUserNameChangeSignUp(userName: String){
        loginState = loginState.copy(userNameSignUp = userName)
    }
    fun onPasswordChangeSignUp(password: String){
        loginState = loginState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String){
        loginState = loginState.copy(confirmPasswordSignUp = password)
    }
    private fun validateLoginForm()=
        loginState.userName.isBlank() &&
                loginState.password.isNotBlank()

    private fun validateSignUpForm()=
        loginState.userName.isBlank() &&
                loginState.passwordSignUp.isNotBlank() &&
                loginState.confirmPasswordSignUp.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try{
            if (validateSignUpForm()){
                throw IllegalArgumentException("Email and Password cannot empty")
            }
            loginState = loginState.copy(isLoading = true)
            if(loginState.passwordSignUp !=
                    loginState.confirmPasswordSignUp){
                throw IllegalArgumentException("password do not match")
            }
            loginState = loginState.copy(signUpError = null)
            repository.createUser(
                loginState.userNameSignUp,
                loginState.passwordSignUp
            ){isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    loginState = loginState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                    loginState = loginState.copy(isSuccessLogin = false)
                }
            }
        }catch (e:java.lang.Exception){
            loginState = loginState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()

        }finally{
            loginState = loginState.copy(isLoading = false)
        }
    }

    fun logInUser(context: Context) = viewModelScope.launch {
        try{
            if (!validateLoginForm()){
                throw IllegalArgumentException("Email and Password cannot empty")
            }
            loginState = loginState.copy(isLoading = true)
            if(loginState.passwordSignUp !=
                loginState.confirmPasswordSignUp){
                throw IllegalArgumentException("password do not match")
            }
            loginState = loginState.copy(loginError = null)
            repository.loginUser(
                loginState.userName,
                loginState.password
            ){isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    loginState = loginState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                    loginState = loginState.copy(isSuccessLogin = false)
                }
            }
        }catch (e:java.lang.Exception){
            loginState = loginState.copy(loginError = e.localizedMessage)
            e.printStackTrace()

        }finally{
            loginState = loginState.copy(isLoading = false)
        }
    }


}















