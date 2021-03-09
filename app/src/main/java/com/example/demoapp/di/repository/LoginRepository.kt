package com.example.demoapp.di.repository

import android.content.Context
import android.util.Log
import com.example.demoapp.ui.login.model.Result
import com.example.demoapp.ui.login.model.User
import com.example.demoapp.utils.Constants
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginRepository @Inject constructor(){

    //Make login API call/verify hardcoded credentials.
    fun login(user: User) : Result<User>{

        if(user.emailId!!.contentEquals(Constants.emailId) && user.password!!.contentEquals(Constants.password)){
            Log.e("Tag","@@@ login success")
            return Result.Success(user)
        }else{
            return Result.Error("Error logging in")
        }
    }

}