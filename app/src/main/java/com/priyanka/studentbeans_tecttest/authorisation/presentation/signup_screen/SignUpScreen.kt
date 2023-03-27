package com.priyanka.studentbeans_tecttest.authorisation.presentation.signup_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priyanka.studentbeans_tecttest.R
import com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen.SignInViewModel
import com.priyanka.studentbeans_tecttest.presentation.MainScreen
import com.priyanka.studentbeans_tecttest.ui.theme.StudentBeans_TectTestTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    loginviewModel: SignInViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToLogInPage: () -> Unit,
) {
    val context = LocalContext.current
    val state = loginviewModel?.loginState
    val isError = state?.signUpError != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "SignUp to your Student Bean account",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black,
            fontFamily = FontFamily.Default
        )
        Spacer(modifier = Modifier.height(50.dp))
        if (isError) {
            Text(text = state?.signUpError ?: "Unknown error", color = Color.Red)
        }
        TextField(value = state?.userNameSignUp?:"", onValueChange ={loginviewModel?.onUserNameChangeSignUp(it)},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = null,)},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Blue, unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            ), shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = { Text(text = "Email") },
            isError = isError
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(value = state?.passwordSignUp?:"", onValueChange ={
            loginviewModel?.onPasswordChangeSignUp(it)},
            leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = null,)},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Blue, unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            ), shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = { Text(text = "Password") },
            isError = isError,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = state?.confirmPasswordSignUp?:"", onValueChange ={
            loginviewModel?.onConfirmPasswordChange(it)},
            leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = null,)},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Blue, unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            ), shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = { Text(text = "Confirm Password") }, isError = isError,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                loginviewModel?.createUser(context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White, modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? Sign In",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily.Default
            )
            TextButton(onClick = {onNavToLogInPage.invoke()}) {
                Text(text = "SignIn",
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontFamily = FontFamily.Default)
            }

        }
        if (state?.isLoading == true){
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginviewModel?.hasUser){
            if (loginviewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }}

    @Preview
    @Composable
    fun PrevSignUpScreen() {
        StudentBeans_TectTestTheme {
            SignUpScreen(onNavToHomePage = { /*TODO*/ }) {

            }
        }
    }

