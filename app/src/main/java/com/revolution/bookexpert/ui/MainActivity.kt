package com.revolution.bookexpert.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import com.revolution.bookexpert.R
import com.revolution.bookexpert.other.base.BaseActivity
import com.revolution.bookexpert.other.base.OnSpeechRecognizeListener
import com.revolution.bookexpert.other.custom.annotations.GlobalNavigatorHolderQualifier
import com.revolution.bookexpert.other.custom.annotations.GlobalRouterQualifier
import com.revolution.bookexpert.other.custom.extensions.currentFragment
import com.revolution.bookexpert.ui.chat.BookExpertChatFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    @GlobalNavigatorHolderQualifier
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router

    override fun renderView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            router.newRootScreen(BookExpertChatFragment.toScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_RECOGNIZER && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                val currentFragment = supportFragmentManager.currentFragment
                if (currentFragment is OnSpeechRecognizeListener) {
                    currentFragment.onSpeechRecognize(it)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SPEECH_RECOGNIZER = 600
    }

}
