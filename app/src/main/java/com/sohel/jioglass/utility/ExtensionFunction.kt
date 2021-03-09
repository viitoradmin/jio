package com.sohel.jioglass.utility


import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.constraintlayout.motion.widget.MotionLayout

//Set of Extension Function which are helpful for development

//motion layout transition
fun MotionLayout.fireTransition(startId: Int, endId: Int) {
    this.setTransition(startId, endId)
    this.transitionToEnd()
}

//fade out to gone view
fun View.fadeOutToGone(parent: ViewGroup) {
    val anim = AlphaAnimation(1.0f, 0.0f)
    anim.duration = AppConstant.ANIMATION_DURATION
    this.startAnimation(anim)
    anim.setAnimationListener(object :Animation.AnimationListener{
        override fun onAnimationEnd(p0: Animation?) {
            this@fadeOutToGone.visibility =  View.GONE

        }

        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationStart(p0: Animation?) {

        }
    })


}

//fade in to visible
fun View.fadeInToVisible(parent: ViewGroup) {
    this.visibility =  View.VISIBLE
    val anim = AlphaAnimation(0.0f, 1.0f)
    anim.duration = AppConstant.ANIMATION_DURATION
    this.startAnimation(anim)

}