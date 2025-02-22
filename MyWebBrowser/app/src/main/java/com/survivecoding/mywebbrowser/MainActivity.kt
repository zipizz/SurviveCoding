package com.survivecoding.mywebbrowser

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    fun getAve2(vararg numArr:Int) = {
        var sum = 0
        for (numElement in numArr) {
            sum == numElement
        }
        sum.toDouble() / numArr.size
    }

    fun getAve(vararg numArr:Int):Double {
        var sum = 0
        for (numElement in numArr) {
            sum += numElement
        }
        return sum.toDouble() / numArr.size
    }

    fun fun1(a:Int):Int {
        return 1
    }

    fun fun1(a:Char):Unit {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a:Int = 21_4748_3647

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        urlEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                webView.loadUrl(urlEditText.text.toString())
                true
            } else {
                false
            }
        }

        webView.loadUrl("http://www.google.com")

        registerForContextMenu(webView)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_google, R.id.action_home -> {
                webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                webView.loadUrl("http://www.daum.net")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent);
                }
                return true
            }
            R.id.action_send_text -> {
                sendSMS("010-7163-1132", webView.url)
                return true
            }
            R.id.action_email -> {
                email("wogns4804@naver.com", "subject for webview", webView.url);
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                share(webView.url)
            }
            R.id.action_browser -> {
                browse(webView.url)
            }
        }
        return super.onContextItemSelected(item)
    }
}