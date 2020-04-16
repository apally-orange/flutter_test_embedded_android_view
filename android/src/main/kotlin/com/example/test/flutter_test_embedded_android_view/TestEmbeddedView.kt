package com.example.test.flutter_test_embedded_android_view

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import io.flutter.plugin.platform.PlatformView

class TestEmbeddedView(private val context: Context) : PlatformView {
    private val layout = LinearLayout(context)

    override fun getView(): View {
        return layout
    }

    override fun dispose() {

    }

    init {
        layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layout.orientation = LinearLayout.VERTICAL
        val tv1 = TextView(context)
        val tv2 = EditText(context)
        tv1.text = "Test Embedded View"
        layout.isFocusable = true;
        layout.isFocusableInTouchMode = true;
        layout.addView(tv1)
        layout.addView(tv2)
    }
}