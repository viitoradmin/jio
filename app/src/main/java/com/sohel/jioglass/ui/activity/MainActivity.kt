package com.sohel.jioglass.ui.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
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
        setTransitionListener()
    }

    // initilization
    fun init() {
        binding.ivBubble.setOnClickListener(this)
    }


    //
    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.ivBubble -> {
                bubbleClickToggle()
            }

        }
    }


    // transition listener for Transition End Detect purpose
    fun setTransitionListener(){
        binding.mLayout.setTransitionListener(object : MotionLayout.TransitionListener{

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                if(p1==R.id.stepTwo){
                    binding.mLayout.fireTransition(R.id.stepTwo, R.id.stepThree)
                }else if(p1==R.id.stepFour){
                    binding.mLayout.fireTransition(R.id.stepFour, R.id.stepOne)
                }


            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }


            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }


            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })
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
            binding.mLayout.fireTransition(R.id.stepThree, R.id.stepFour)
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