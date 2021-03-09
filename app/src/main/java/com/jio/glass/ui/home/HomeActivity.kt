package com.jio.glass.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jio.glass.databinding.ActivityHomeBinding
import com.jio.glass.domain.entity.flowerList
import com.jio.glass.utils.dpToPx

/**
 * Home Screen
 */
class HomeActivity: AppCompatActivity() {

    private val homeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val duration = 350L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeBinding.root)
        setupUI()
    }

    private fun setupUI(){
        homeBinding.rvProducts.adapter = ProductAdapter(flowerList(resources))
        startAnimation()
    }

    private fun startAnimation(){
        var profileSize = dpToPx(66)
        profileSize = -profileSize
        homeBinding.civProfile.alpha = 0.5f
        homeBinding.atvLets.alpha = 0.5f
        homeBinding.civProfile.translationY = profileSize.toFloat()
        homeBinding.atvLets.translationY = profileSize.toFloat()

        var searchSize = dpToPx(130)
        searchSize = -searchSize
        homeBinding.mcvSearch.alpha = 0.5f
        homeBinding.mcvSearch.translationY = searchSize.toFloat()

        val rvSize = dpToPx(100)
        homeBinding.rvProducts.alpha = 0.5f
        homeBinding.rvProducts.translationX = rvSize.toFloat()

        homeBinding.civProfile.animate()
            .translationY(0.0f)
            .alpha(1.0f)
            .setDuration(duration)
            .start()

        homeBinding.atvLets.animate()
            .translationY(0.0f)
            .alpha(1.0f)
            .setDuration(duration)
            .start()

        homeBinding.mcvSearch.animate()
            .translationY(0.0f)
            .alpha(1.0f)
            .setDuration(duration)
            .start()

        homeBinding.rvProducts.animate()
            .translationX(0.0f)
            .alpha(1.0f)
            .setDuration(duration)
            .start()
    }

}