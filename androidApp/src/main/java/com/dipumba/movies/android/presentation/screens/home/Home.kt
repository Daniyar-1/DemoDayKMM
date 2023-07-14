package com.dipumba.movies.android.presentation.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dipumba.movies.android.R
import com.dipumba.movies.android.TextFieldColorPlaceHolder
import com.dipumba.movies.android.network.result.Resource
import com.dipumba.movies.android.remote.model.SignUpDto
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(
    context: Context,
    navigationVerified: () -> Unit
){
    val vm: HomeVM = koinViewModel()
    var nameText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var againPassword by remember { mutableStateOf("") }

    fun initViewModel() {
        val signUpDto = SignUpDto(username = nameText, password = password)

        if (password == againPassword && password.length in 8..20) {
            vm.signUp(signUpDto).observeForever {
                when(it.status) {
                    Resource.Status.SUCCESS -> {
                        navigationVerified.invoke()
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(context, "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(context, "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .width(325.dp)
                .height(450.dp)
                .background(color = Color.LightGray.copy(alpha = 0.85f))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .background(color = Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Регистрация", fontSize = 30.sp, modifier = Modifier.padding(top = 20.dp), color = Color.Black)
                TextField(
                    value = nameText,
                    onValueChange = { nameText = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Имя",
                            color = TextFieldColorPlaceHolder,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                        .background(Color.White)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Пароль",
                            color = TextFieldColorPlaceHolder
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(Color.White)
                )
                TextField(
                    value = againPassword,
                    onValueChange = { againPassword = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Введите пароль еще раз",
                            color = TextFieldColorPlaceHolder
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(Color.White)
                    ,
                )

                Button(
                    onClick = { initViewModel() },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.padding(top = 25.dp),
                    shape = RoundedCornerShape(1.dp)
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}