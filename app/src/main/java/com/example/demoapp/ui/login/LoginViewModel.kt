package com.example.demoapp.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.R
import com.example.demoapp.di.repository.LoginRepository
import com.example.demoapp.ui.login.model.LoginFormState
import com.example.demoapp.ui.login.model.Result
import com.example.demoapp.ui.login.model.User
import com.example.demoapp.utils.isNotEmptyAndNull
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.logging.Handler
import javax.inject.Inject


/*LoginViewModel is responsible for interacting and updating UI and validations.*/

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    private val _loginForm = MutableLiveData<LoginFormState>()
    private var user = User("", "")

    val loginForm: LiveData<LoginFormState> = _loginForm
    val loginResult: LiveData<Result<User>> = _loginResult
    var bottomSheetState: Int? = BottomSheetBehavior.STATE_COLLAPSED
    var isLoading: Boolean = false

    fun login(username: String, password: String) {

        if (isDataValid()) {
            isLoading = true
            android.os.Handler().postDelayed({
                var result = loginRepository.login(User(username, password));
                _loginResult.value = result
            }, 1000)
        }
        isLoading = false
    }

    fun userDataChanged(emailId: String, password: String) {
        user.emailId = emailId
        user.password = password
    }

    fun setBottomSheetState(state: Int) {
        bottomSheetState = state
    }

    fun isDataValid(): Boolean {
        var loginFormState = LoginFormState(R.string.invalid_email, R.string.invalid_password)
        if (user.emailId.isNotEmptyAndNull() && Patterns.EMAIL_ADDRESS.matcher(user.emailId)
                .matches()
        ) {
            loginFormState.emailIdError = null
        }

        if (user.password.isNotEmptyAndNull() && user.password!!.length > 6) {
            loginFormState.passwordError = null
        }

        _loginForm.value = LoginFormState(loginFormState.emailIdError, loginFormState.passwordError)
        return (loginFormState.emailIdError == null && loginFormState.passwordError == null)
    }
}