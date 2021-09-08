package com.anime.dl.interfaces

import android.app.Activity
import android.view.View
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView

interface Tutorial {

     fun tutorial(
        activity: Activity,
        view: View,
        title: Int,
        desc: Int
    ) {
        TapTargetView.showFor(
            activity,
            TapTarget.forView(
                view,
                activity.getString(title),
                activity.getString(desc)
            ),
            object : TapTargetView.Listener() {
                override fun onTargetClick(view: TapTargetView?) {
                    super.onTargetClick(view)
                    view?.performClick()
                }
            }
        )
    }
}
