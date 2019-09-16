package com.codemobile.doitprogressapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_view_progress_animation_full.view.*

class CustomViewProgressAnimationFull(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var animatorSet = AnimatorSet()
    private var durationAnimation = 700L
    private var view: View

    companion object {
        private const val START_ANGLE = 2f
        private const val END_ANGLE = 90f
        private const val TRANSLATION_Y = "translationY"
        private const val TRANSLATION_VALUE = 10f
        private const val TRANSLATION_X = "translationX"
        private const val ROTATION = "rotation"
    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_view_progress_animation_full, this, true)
        playAnimation()
    }

    fun playAnimation() {
        animatorSet.cancel()
        animatorSet = AnimatorSet().apply {
            val rotationAnimator = createRotationAnimator()
            val translateAnimator01 =
                createTranslationAnimator(view.progress_circle01, TRANSLATION_Y, TRANSLATION_VALUE)
            val translateAnimator02 =
                createTranslationAnimator(view.progress_circle02, TRANSLATION_X, TRANSLATION_VALUE)
            val translateAnimator03 =
                createTranslationAnimator(view.progress_circle03, TRANSLATION_X, -TRANSLATION_VALUE)
            val translateAnimator04 =
                createTranslationAnimator(view.progress_circle04, TRANSLATION_Y, -TRANSLATION_VALUE)
            playTogether(
                rotationAnimator,
                translateAnimator01,
                translateAnimator02,
                translateAnimator03,
                translateAnimator04
            )
            interpolator = AccelerateDecelerateInterpolator()
        }
        animatorSet.start()
    }

    @SuppressLint("WrongConstant")
    private fun createTranslationAnimator(
        imageView: ImageView?,
        translationType: String,
        traslationValue: Float
    ): Animator? {
        return ObjectAnimator.ofFloat(imageView, translationType, traslationValue).apply {
            duration = durationAnimation / 2
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
        }
    }

    @SuppressLint("WrongConstant")
    private fun createRotationAnimator(): Animator? {
        return ObjectAnimator.ofFloat(view, ROTATION, START_ANGLE, END_ANGLE).apply {
            duration = durationAnimation
            repeatCount = Animation.INFINITE
            repeatMode = Animation.INFINITE
        }
    }
}

