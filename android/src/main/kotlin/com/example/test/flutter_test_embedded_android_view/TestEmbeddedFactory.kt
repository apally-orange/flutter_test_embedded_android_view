package com.example.test.flutter_test_embedded_android_view

import android.content.Context
import android.util.Log
import android.view.View
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class TestEmbeddedFactory(private val containerView: View?) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, id: Int, args: Any?): PlatformView? {
        Log.e("aaron", "TestEmbeddedFactory create")
        return TestEmbeddedView(context, containerView)
    }
}
