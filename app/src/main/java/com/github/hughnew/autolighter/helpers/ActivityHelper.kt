package com.github.hughnew.autolighter.helpers

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.transStatusBar() {
    if (Build.VERSION.SDK_INT in 26..29) {
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            == Configuration.UI_MODE_NIGHT_NO
        ) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    } else if (Build.VERSION.SDK_INT >= 30) {
        if (!resources.configuration.isNightModeActive) {
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                true
            WindowCompat.setDecorFitsSystemWindows(window, false) // 高度调整
        }
    }
}