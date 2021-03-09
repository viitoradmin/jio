package com.jio.glass.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jio.glass.data.remote.ApiUser
import com.jio.glass.domain.usecase.SignInUseCase
import com.jio.glass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(private val signInUseCase: SignInUseCase): ViewModel() {

    private val user = MutableLiveData<Resource<ApiUser>>()

    fun signIn(email: String, password: String) {

        if(email != "JioGlass@tesseract.com"){
            user.postValue(Resource.emailError())
            return
        }

        if(password != "JioGlass@123"){
            user.postValue(Resource.passwordError())
            return
        }

        viewModelScope.launch {
            user.postValue(Resource.loading(null))
            signInUseCase.signIn(email, password)
                .catch { e ->
                    user.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    user.postValue(Resource.success(it))
                }
        }
    }

    fun getUser(): LiveData<Resource<ApiUser>> {
        return user
    }

}