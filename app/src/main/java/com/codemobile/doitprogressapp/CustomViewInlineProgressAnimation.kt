package com.codemobile.doitprogressapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_view_inline_progress_animation.view.*
import kotlinx.android.synthetic.main.custom_view_progress_animation_full.view.*

class CustomViewInlineProgressAnimation(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var animatorSet = AnimatorSet()
    private var durationAnimation = 1000L
    private var view: View

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_view_inline_progress_animation, this, true)
        onProgressLoading()
    }

    fun onProgressLoading() {
        animatorSet.cancel()
        animatorSet = AnimatorSet().apply {
            val pointAlphaAnimation_1 = createAlphaAnimation(view.progress_inline_circle01, durationAnimation)
            val pointAlphaAnimation_2 = createAlphaAnimation(view.progress_inline_circle02, durationAnimation)
            val pointAlphaAnimation_3 = createAlphaAnimation(view.progress_inline_circle03, durationAnimation)
            pointAlphaAnimation_2.startDelay = durationAnimation/4
            pointAlphaAnimation_3.startDelay = durationAnimation/2
            playTogether(
                pointAlphaAnimation_1,
                pointAlphaAnimation_2,
                pointAlphaAnimation_3
            )
        }
        animatorSet.start()
    }

    fun onProgressStop() {
        animatorSet.cancel()
    }

    @SuppressLint("WrongConstant")
    private fun createAlphaAnimation(target: View, durationAlpha: Long): Animator {
        return ObjectAnimator.ofFloat(target, View.ALPHA, 1.0f, 0.1f).apply {
            duration = durationAlpha
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }
    }
}

