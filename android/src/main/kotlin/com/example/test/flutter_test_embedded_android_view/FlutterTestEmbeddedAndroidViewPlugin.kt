package com.example.test.flutter_test_embedded_android_view

import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterTestEmbeddedAndroidViewPlugin */
public class FlutterTestEmbeddedAndroidViewPlugin : FlutterPlugin, ActivityAware {
    private lateinit var flutterPluginBinding: FlutterPlugin.FlutterPluginBinding

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        this.flutterPluginBinding = flutterPluginBinding
    }

    companion object {
        var containerView: View? = null

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            Log.e("aaron", "registerWith")
            containerView = registrar.view()
            if (registrar.activity() != null) {
                registrar.platformViewRegistry()
                        .registerViewFactory(
                                "test_channel",
                                TestEmbeddedFactory(containerView))
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        Log.e("aaron", "onAttachedToActivity")
        flutterPluginBinding
                .platformViewRegistry
                .registerViewFactory(
                        "test_channel",
                        TestEmbeddedFactory(containerView))
    }

    override fun onDetachedFromActivity() {
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }
}
