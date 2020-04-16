package com.example.test.flutter_test_embedded_android_view

import android.app.Activity
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterTestEmbeddedAndroidViewPlugin */
public class FlutterTestEmbeddedAndroidViewPlugin : FlutterPlugin, ActivityAware {
    private lateinit var flutterPluginBinding: FlutterPlugin.FlutterPluginBinding

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        this.flutterPluginBinding = flutterPluginBinding
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            if (registrar.activity() != null) {
                registrar.platformViewRegistry()
                        .registerViewFactory(
                                "test_channel",
                                TestEmbeddedFactory())
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        flutterPluginBinding
                .platformViewRegistry
                .registerViewFactory(
                        "test_channel",
                        TestEmbeddedFactory())
    }

    override fun onDetachedFromActivity() {
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }
}
