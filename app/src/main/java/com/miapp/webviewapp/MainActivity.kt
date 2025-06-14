package com.miapp.webviewapp
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val FILE_CHOOSER_REQUEST_CODE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://comparte.vercel.app")
    }
}

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true

        webView.webChromeClient = object : WebChromeClient() {
            // Soporte para input type="file"
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                this@MainActivity.filePathCallback?.onReceiveValue(null)
                this@MainActivity.filePathCallback = filePathCallback

                val intent = fileChooserParams.createIntent()
                try {
                    startActivityForResult(intent, FILE_CHOOSER_REQUEST_CODE)
                } catch (e: Exception) {
                    this@MainActivity.filePathCallback = null
                    return false
                }
                return true
            }
        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://comparte.vercel.app")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == FILE_CHOOSER_REQUEST_CODE) {
            val result = if (resultCode == Activity.RESULT_OK && intent != null) {
                WebChromeClient.FileChooserParams.parseResult(resultCode, intent)
            } else null
            filePathCallback?.onReceiveValue(result)
            filePathCallback = null
        }
    }
}