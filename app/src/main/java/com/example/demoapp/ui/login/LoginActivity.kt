package com.example.demoapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityLoginBinding
import com.example.demoapp.ui.home.HomeActivity
import com.example.demoapp.ui.login.model.Result
import com.example.demoapp.utils.afterTextChanged
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this
        setUpUI()
        setUpObservers()
    }

    /*All interaction with UI are defined in this method*/
    private fun setUpUI() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.signInBottomSheet.mainLayout)

        binding.signInBtn.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.signInBottomSheet.signInBtn.setOnClickListener {
            binding.progressBar.progressBar.visibility = View.VISIBLE
            loginViewModel.login(
                binding.signInBottomSheet.emailIdEditTxt.text.toString(),
                binding.signInBottomSheet.passwordEditTxt.text.toString()
            )
        }

        binding.signInBottomSheet.emailIdEditTxt.afterTextChanged {
            loginViewModel.userDataChanged(
                binding.signInBottomSheet.emailIdEditTxt.text.toString(),
                binding.signInBottomSheet.passwordEditTxt.text.toString()
            )
        }

        binding.signInBottomSheet.passwordEditTxt.afterTextChanged {
            loginViewModel.userDataChanged(
                binding.signInBottomSheet.emailIdEditTxt.text.toString(),
                binding.signInBottomSheet.passwordEditTxt.text.toString()
            )
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Do nothing here.
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                loginViewModel.setBottomSheetState(newState)
            }
        })

        if (loginViewModel.bottomSheetState == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        if (loginViewModel.isLoading) {
            binding.progressBar.progressBar.visibility = View.VISIBLE

        }
    }

    /*All observers are defined in this method*/
    private fun setUpObservers() {

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            binding.progressBar.progressBar.visibility = View.GONE

//            binding.signInBottomSheet.progressCircular.visibility = View.GONE
            when (it) {
                is Result.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is Result.Error -> {
                    binding.signInBottomSheet.emailIdTxtInputLayout.error =
                        getString(R.string.incorrect_email)
                    binding.signInBottomSheet.passwordTxtInputLayout.error =
                        getString(R.string.incorrect_password)
                }
            }
        })

        loginViewModel.loginForm.observe(this@LoginActivity, Observer {

            if (it.emailIdError != null) {
                binding.signInBottomSheet.emailIdTxtInputLayout.error = getString(it.emailIdError!!)
                binding.progressBar.progressBar.visibility = View.GONE
            } else
                binding.signInBottomSheet.emailIdTxtInputLayout.error = null

            if (it.passwordError != null) {
                binding.signInBottomSheet.passwordTxtInputLayout.error =
                    getString(it.passwordError!!)
                binding.progressBar.progressBar.visibility = View.GONE
            } else
                binding.signInBottomSheet.passwordTxtInputLayout.error = null

//            binding.signInBottomSheet.progressCircular.visibility = View.GONE


        })

    }
}