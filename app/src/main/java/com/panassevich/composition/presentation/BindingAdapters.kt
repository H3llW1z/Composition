package com.panassevich.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.panassevich.composition.R
import com.panassevich.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}


@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_score), count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
                textView.context.getString(R.string.required_answers_percentage),
                percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.your_answers_percentage),
        calculatePercentOfRightAnswers(gameResult.countOfRightAnswers, gameResult.countOfQuestions)
    )
}

private fun calculatePercentOfRightAnswers(
    countOfRightAnswers: Int,
    countOfQuestions: Int
): Int {
    if (countOfQuestions == 0) {
        return 0
    }
    return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
}

@BindingAdapter("emojiImage")
fun bindEmoji(imageView: ImageView, isWinner: Boolean) {
    val imageResId = if (isWinner) {
        R.drawable.winner
    } else {
        R.drawable.not_winner
    }
    imageView.setImageResource(imageResId)
}

@BindingAdapter("percentOfRightAnswers")
fun bindPercentOfRightAnswers(progressBar: ProgressBar, percent: Int) {
    progressBar.setProgress(percent, true)
}

@BindingAdapter("progressBarColor")
fun bindProgressBarColor(progressBar: ProgressBar, isEnough: Boolean) {

    val color = getColorByState(isEnough, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("progressAnswersColor")
fun bindProgressAnswersColor(textView: TextView, isEnough: Boolean) {
    textView.setTextColor(getColorByState(isEnough, textView.context))
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener { clickListener.onOptionClick(textView.text.toString().toInt()) }
}