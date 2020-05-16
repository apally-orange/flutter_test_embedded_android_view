package com.example.test.flutter_test_embedded_android_view

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import io.flutter.plugin.platform.PlatformView
import io.flutter.view.FlutterView

class TestEmbeddedView(context: Context, private val containerView: View?) : PlatformView {
    class MyLayout(context: Context, var containerView: View?) : LinearLayout(context) {
        private var proxy: ThreadedInputConnectionProxyAdapterView? = null

        init {
            setFocusable(true)
            setFocusableInTouchMode(true)
            setClickable(true)
        }

        override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
            Log.e("TestEmbeddedView", "onFocusChaned")
            super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
        }

        override fun checkInputConnectionProxy(view: View): Boolean {
            proxy = ThreadedInputConnectionProxyAdapterView(containerView, view, view.getHandler())
            setInputConnectionTarget(proxy!!)
            return super.checkInputConnectionProxy(view)
        }

        fun setInputConnectionTarget(targetView: View) {
            targetView.requestFocus()
            containerView!!.post(
                object : Runnable {
                  override fun run() {
                    val imm: InputMethodManager = getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    // This is a hack to make InputMethodManager believe that the target view now has focus.
                    // As a result, InputMethodManager will think that targetView is focused, and will call
                    // getHandler() of the view when creating input connection.

                    // Step 1: Set targetView as InputMethodManager#mNextServedView. This does not affect
                    // the real window focus.
                    targetView.onWindowFocusChanged(true)

                    // Step 2: Have InputMethodManager focus in on targetView. As a result, IMM will call
                    // onCreateInputConnection() on targetView on the same thread as
                    // targetView.getHandler(). It will also call subsequent InputConnection methods on this
                    // thread. This is the IME thread in cases where targetView is our proxyAdapterView.
                    imm.isActive(containerView)
                  }
                })
          }
    }

    private val layout = MyLayout(context, containerView)

    override fun getView(): View {
        return layout
    }

    override fun onFlutterViewAttached(view : View) {
        layout.containerView = view;
    }

    override fun dispose() {
    }

    init {
        Log.e("aaron", "TestEmbeddedView::init")
        layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layout.orientation = LinearLayout.VERTICAL
        val tv1 = TextView(context)
        val tv2 = EditText(context)
        val tv3 = TextView(context)
        val tv4 = EditText(context)
        tv1.text = "Test Embedded View"
        tv3.text = "Test Embedded View 2"
        layout.addView(tv1)
        layout.addView(tv2)
        layout.addView(tv3)
        layout.addView(tv4)
    }
}
