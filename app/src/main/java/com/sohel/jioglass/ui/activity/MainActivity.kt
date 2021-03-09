package com.sohel.jioglass.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.sohel.jioglass.R
import com.sohel.jioglass.databinding.ActivityMainBinding
import com.sohel.jioglass.utility.AppConstant
import com.sohel.jioglass.utility.fadeInToVisible
import com.sohel.jioglass.utility.fadeOutToGone
import com.sohel.jioglass.utility.fireTransition

//Main Activity class of the application
class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    var isOpen = false
    lateinit var context: Context
    var oldSpecX = 0f
    var oldSpecY = 0f
    var firstAnimation =  false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = this@MainActivity
        init()


    }

    fun init() {
        binding.ivBubble.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.ivBubble -> {
                bubbleClickToggle()
            }

        }

    }


    //toggle click for bubble
    fun bubbleClickToggle() {
        isOpen = if (!isOpen) {
            animateSpecToClose()
            binding.mLayout.fireTransition(R.id.stepOne, R.id.stepTwo)
            binding.clTop.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.blackTransparent
                )
            )
            true
        } else {
            animationCloseToSpeck()
            binding.mLayout.fireTransition(R.id.stepTwo, R.id.stepOne)
            binding.clTop.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            false
        }
    }


    //animation Jio Glass to Close button
    fun animateSpecToClose() {

        if(!firstAnimation){
            oldSpecX = binding.clSpec.x
            oldSpecY = binding.clSpec.y
            firstAnimation =  true
        }

        binding.clDevices.fadeInToVisible(binding.mLayout)
        binding.tvDeviceStatus.fadeInToVisible(binding.mLayout)

        binding.ivBlueDot.fadeOutToGone(binding.mLayout)
        binding.ivRedDot.fadeOutToGone(binding.mLayout)
        binding.clSpec.fadeOutToGone(binding.mLayout)

        binding.clSpec.animate()
            .x(binding.clController.x)
            .y(binding.clController.y)
            .setDuration(AppConstant.ANIMATION_DURATION)
            .withEndAction {
                binding.ivController.setImageResource(R.drawable.cross)

            }
            .start()
    }


    //animation Close button to Jio Glass
    fun animationCloseToSpeck() {

        binding.clSpec.fadeInToVisible(binding.mLayout)
        binding.ivBlueDot.fadeInToVisible(binding.mLayout)
        binding.ivRedDot.fadeInToVisible(binding.mLayout)
        binding.tvDeviceStatus.fadeOutToGone(binding.mLayout)
        binding.clDevices.fadeOutToGone(binding.mLayout)



        binding.clSpec.animate()
            .x(oldSpecX)
            .y(oldSpecY)
            .setDuration(AppConstant.ANIMATION_DURATION)
            .withEndAction {
                binding.ivController.setImageResource(R.drawable.device_controller)

            }
            .start()

    }


}