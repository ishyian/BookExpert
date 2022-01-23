package codeninjas.musicakinator.other.custom.extensions

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View

fun Spannable.color(color: String, start: Int, end: Int): Spannable {
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), start, end, 0)
    return this
}

fun Spannable.color(color: String): Spannable {
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), 0, this.length, 0)
    return this
}

fun Spannable.color(color: String, word: String): Spannable {
    val firstIndex = this.indexOf(word)
    val endIndex = firstIndex + word.length
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), firstIndex, endIndex, 0)
    return this
}

fun Spannable.bold(start: Int, end: Int): Spannable {
    this.setSpan(StyleSpan(Typeface.BOLD), start, end, 0)
    return this
}

fun Spannable.bold(): Spannable {
    this.setSpan(StyleSpan(Typeface.BOLD), 0, this.lastIndex, 0)
    return this
}

fun Spannable.bold(word: String): Spannable {
    val firstIndex = this.indexOf(word)
    val endIndex = firstIndex + word.length
    this.setSpan(StyleSpan(Typeface.BOLD), firstIndex, endIndex, 0)
    return this
}

fun Spannable.clickable(word: String, action: (() -> Unit)): Spannable {
    val firstIndex = this.indexOf(word)
    val endIndex = firstIndex + word.length
    val clickable = object : ClickableSpan() {
        override fun onClick(p0: View) {
            action.invoke()
        }
    }
    this.setSpan(clickable, firstIndex, endIndex, 0)
    return this
}

fun Spannable.underline(start: Int, end: Int): Spannable {
    this.setSpan(UnderlineSpan(), start, end, 0)
    return this
}

fun Spannable.underline(word: String): Spannable {
    val firstIndex = this.indexOf(word)
    val endIndex = firstIndex + word.length
    this.setSpan(UnderlineSpan(), firstIndex, endIndex, 0)
    return this
}

fun Spannable.italic(start: Int, end: Int): Spannable {
    this.setSpan(StyleSpan(Typeface.ITALIC), start, end, 0)
    return this
}

fun Spannable.strike(start: Int, end: Int): Spannable {
    this.setSpan(StrikethroughSpan(), start, end, 0)
    return this
}
