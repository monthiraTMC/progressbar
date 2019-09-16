package com.codemobile.doitprogressapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.custom_view_progress_animation.view.*


class CustomViewProgressAnimation(context:Context,attrs: AttributeSet) :FrameLayout(context,attrs) {
    private var animatorSet = AnimatorSet()
    private var durationAnimation = 700L
    private var view: View

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_view_progress_animation, this, true)
        onProgressLoading()
    }

    fun onProgressLoading(){
        //progressbar rotate
        animatorSet.cancel()
        animatorSet = AnimatorSet().apply {
            val rotationAnimator = createRotationAnimator()
            playTogether(
                rotationAnimator
            )
        }
        animatorSet.start()
    }

    fun onProgressStop(){
        animatorSet.cancel()
    }

    @SuppressLint("WrongConstant")
    private fun createRotationAnimator(): Animator? {
        return ObjectAnimator.ofFloat(view.progressBar, ROTATION, 360f, 0f).apply {
            duration = durationAnimation
            repeatCount = Animation.INFINITE
            repeatMode = Animation.INFINITE
        }
    }
}