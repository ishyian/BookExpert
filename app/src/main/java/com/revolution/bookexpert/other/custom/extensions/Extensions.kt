package com.revolution.bookexpert.other.custom.extensions

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.revolution.bookexpert.R
import com.revolution.bookexpert.ui.MainActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import retrofit2.HttpException
import top.defaults.drawabletoolbox.DrawableBuilder
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showErrorMessage(throwable: Throwable, action: (() -> Unit?)? = null) {
    when (throwable) {
        is HttpException -> showThrowableMessageWithRetry("Server error", action)
        is SocketTimeoutException -> showThrowableMessageWithRetry("Network error", action)
        is UnknownHostException -> showThrowableMessageWithRetry("Network error", action)
        is ConnectException -> showThrowableMessageWithRetry("Failed to connect", action)
    }
}

fun Fragment.showThrowableMessageWithRetry(message: String, action: (() -> Unit?)? = null) {
    val positiveText = if (action != null) "Retry" else "Ok"
    val dialog = AlertDialog.Builder(requireContext())
        .setMessage(message)
        .setPositiveButton(positiveText) { dialogInterface: DialogInterface, i: Int ->
            action?.invoke()

        }
        .setCancelable(false)
        .create()
    dialog.show()
}

fun Fragment.showAlertMessage(message: String?) {
    AlertDialog.Builder(requireContext())
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        .show()
}

fun AppCompatActivity.showAlertMessage(message: String?) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        .show()
}

fun Fragment.showAlertMessage(title: String, message: String?, positiveAction: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialogInterface, i ->
            positiveAction.invoke()
            dialogInterface.dismiss()
        }

        .show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun AppCompatEditText.onTextChanged(): Observable<String> {
    return Observable.create {
        val listener = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                it.onNext(charSequence.toString())
            }
        }
        this.addTextChangedListener(listener)

        it.setDisposable(Disposables.fromAction { this.removeTextChangedListener(listener) })
    }
}

fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun AppCompatButton.setRoundedBtnBackground(borderRadius: Int, color: Int) {
    this.background = DrawableBuilder()
        .rectangle()
        .cornerRadius(borderRadius.px)
        .solidColor(ContextCompat.getColor(this.context!!, color))
        .build()
}

fun AppCompatButton.setRoundedBtnBackground(borderRadius: Int, color: Int, disabledColor: Int) {
    this.background = DrawableBuilder()
        .rectangle()
        .cornerRadius(borderRadius.px)
        .solidColor(ContextCompat.getColor(this.context!!, color))
        .solidColorDisabled(ContextCompat.getColor(this.context!!, disabledColor))
        .build()
}

fun View.setRoundedBorderBackground(borderRadius: Int, strokeColor: Int, backColor: Int) {
    this.background = DrawableBuilder()
        .rectangle()
        .hairlineBordered()
        .cornerRadius(borderRadius.px)
        .strokeColor(ContextCompat.getColor(context!!, strokeColor))
        .solidColor(ContextCompat.getColor(context!!, backColor))
        .ripple()
        .build()
}

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.hideKeyboard() {
    val activity = this.activity as MainActivity

    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null)
        view = View(activity)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.drawSimpleSelectorDialog(
    title: String,
    items: List<String>,
    listener: (DialogInterface, Int) -> Unit
) {
    val dialog = AlertDialog.Builder(context!!)
    dialog.setTitle(title)
    dialog.setItems(items.toTypedArray(), listener)
    dialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
        dialogInterface.dismiss()
    }
    dialog.show()
}

inline val FragmentManager.currentFragment: Fragment?
    get() = this.findFragmentById(R.id.container)



