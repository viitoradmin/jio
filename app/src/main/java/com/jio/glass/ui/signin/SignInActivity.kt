package com.jio.glass.ui.signin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.jio.glass.R
import com.jio.glass.databinding.ActivitySignInBinding
import com.jio.glass.ui.home.HomeActivity
import com.jio.glass.utils.Status
import com.jio.glass.utils.hideKeyboard
import com.jio.glass.utils.isKeyboardOpen
import dagger.hilt.android.AndroidEntryPoint

/**
 * SignIn Screen for Authentication
 */
@AndroidEntryPoint
class SignInActivity: AppCompatActivity() {

    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
    private val signInVM by viewModels<SignInVM>()

    // inset's Top
    private var posTop = 0

    // inset's Bottom
    private var posBottom = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupUserObserver()
    }

    /**
     * setup UI elements
     */
    private fun setupUI(){

        WindowCompat.setDecorFitsSystemWindows(window, Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            setUiWindowInsets()
            animateKeyboardDisplay()
        }

        binding.tieEmailId.addTextChangedListener(TextFieldValidation())
        binding.tiePassword.addTextChangedListener(TextFieldValidation())

        binding.mbSignIn.setOnClickListener {
            signInVM.signIn(binding.tieEmailId.text.toString(), binding.tiePassword.text.toString())
        }
    }

    /**
     * set up the observer for User's value
     */
    private fun setupUserObserver(){
        signInVM.getUser().observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    goToHomeScreen()
                }
                Status.LOADING -> {
                    binding.tilEmailID.isErrorEnabled = false
                    binding.tilPassword.isErrorEnabled = false
                    hideShowProgressBar(true)
                }
                Status.ERROR -> {
                    goToHomeScreen()
                }
                Status.EMAIL_ERROR -> {
                    binding.tilEmailID.isErrorEnabled = true
                    binding.tilEmailID.error = getString(R.string.incorrect_mail_id)
                }
                Status.PASSWORD_ERROR -> {
                    binding.tilPassword.isErrorEnabled = true
                    binding.tilPassword.error = getString(R.string.incorrect_password)
                }
            }
        })
    }

    /**
     * Hide Progressbar, hide keyboard and go to home screen
     */
    private fun goToHomeScreen(){
        hideShowProgressBar(false)
        if (isKeyboardOpen()){
            binding.root.hideKeyboard()
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun hideShowProgressBar(isLoading: Boolean){
        TransitionManager.beginDelayedTransition(
            binding.root,
            AutoTransition()
                .addTarget(binding.progressBar)
                .addTarget(binding.mbSignIn)
                .setDuration(200)
        )
        binding.progressBar.isInvisible = !isLoading
        binding.mbSignIn.isInvisible = isLoading
    }

    private fun setUiWindowInsets() {

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->

            if (posBottom == 0) {
                posTop = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
                posBottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            }

            binding.root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(
                    top = posTop,
                    bottom = posBottom)
            }

            insets
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun animateKeyboardDisplay() {

        val cb = object : WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {

            override fun onProgress(insets: WindowInsets, animations: MutableList<WindowInsetsAnimation>): WindowInsets {

                posBottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom +
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom

                binding.root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    updateMargins(
                        top = posTop,
                        bottom = posBottom)
                }

                return insets
            }
        }
        binding.root.setWindowInsetsAnimationCallback(cb)
    }

    /**
     * applying text watcher on each text field
     */
    inner class TextFieldValidation : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.mbSignIn.isEnabled = binding.tieEmailId.text.toString().trim().isNotEmpty() &&
                    binding.tiePassword.text.toString().trim().isNotEmpty()
        }
    }

}